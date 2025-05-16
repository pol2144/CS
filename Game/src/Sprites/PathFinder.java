package Sprites;

import java.util.*;

public class PathFinder {
    public class Node implements Comparable<Node> {
        int x, y;
        double gCost, hCost;
        Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getFCost() {
            return gCost + hCost;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.getFCost(), other.getFCost());
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public List<Node> findPath(Node start, Node goal, Set<Node> obstacles) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        start.gCost = 0;
        start.hCost = getHeuristic(start, goal);
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.equals(goal)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current, obstacles)) {
                if (closedSet.contains(neighbor)) continue;

                double tentativeGCost = current.gCost + getDistance(current, neighbor);
                if (tentativeGCost < neighbor.gCost || !openSet.contains(neighbor)) {
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = getHeuristic(neighbor, goal);
                    neighbor.parent = current;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return new ArrayList<>(); // No path found
    }

    private double getHeuristic(Node a, Node b) {
        // Manhattan distance for grid-based movement
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private double getDistance(Node a, Node b) {
        // Uniform cost for grid movement
        return 1;
    }

    private List<Node> getNeighbors(Node node, Set<Node> obstacles) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 4-directional movement

        for (int[] dir : directions) {
            int newX = node.x + dir[0];
            int newY = node.y + dir[1];
            Node neighbor = new Node(newX, newY);
            if (!obstacles.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }
}