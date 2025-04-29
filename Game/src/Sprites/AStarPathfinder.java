package Sprites;
import java.util.*;

public class AStarPathfinder {
    class aStarPathfinder {
        private class Node {
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
        }

        public List<Node> findPath(Node start, Node goal) {
            List<Node> openSet = new ArrayList<>();
            Set<Node> closedSet = new HashSet<>();
            openSet.add(start);

            while (!openSet.isEmpty()) {
                Node current = getLowestFCostNode(openSet);
                if (current.equals(goal)) {
                    return reconstructPath(current);
                }

                openSet.remove(current);
                closedSet.add(current);

                for (Node neighbor : getNeighbors(current)) {
                    if (closedSet.contains(neighbor)) {
                        continue;
                    }
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
            return new ArrayList<>(); // Return empty path if no path found
        }

        private Node getLowestFCostNode(List<Node> openSet) {
            Node lowest = openSet.get(0);
            for (Node node : openSet) {
                if (node.getFCost() < lowest.getFCost()) {
                    lowest = node;
                }
            }
            return lowest;
        }

        private double getHeuristic(Node a, Node b) {
            return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // Manhattan distance
        }

        private double getDistance(Node a, Node b) {
            return 1; // Assuming uniform cost
        }

        private List<Node> getNeighbors(Node node) {
            // Implement logic to retrieve neighboring nodes based on your grid
            return new ArrayList<>();
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
}
