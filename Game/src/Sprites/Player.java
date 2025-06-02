package Sprites;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private Type type;
    private int row;
    private int column;
    public List<PathFinder.Node> currentPath;
    private int currentPathIndex;
    private float moveSpeed = 1.0f; // Tiles per second
    private float moveProgress = 0f;
    private boolean isMoving = false;

    public Player(int row, int column, Type type) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.currentPath = new ArrayList<>();
    }

    public Type getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public boolean moveTo(int targetRow, int targetCol, Set<PathFinder.Node> obstacles) {
        PathFinder pathFinder = new PathFinder();
        PathFinder.Node start = new PathFinder.Node(column, row);
        PathFinder.Node goal = new PathFinder.Node(targetCol, targetRow);

        Set<PathFinder.Node> tempObstacles = new HashSet<>(obstacles);
        tempObstacles.add(start);
        
        List<PathFinder.Node> path = pathFinder.findPath(start, goal, tempObstacles);
        
        if (!path.isEmpty()) {
            if (path.size() > 1) {
                path.remove(0);
                this.currentPath = path;
                this.currentPathIndex = 0;
                this.isMoving = true;
                this.moveProgress = 0f;
                return true;
            }
        }
        return false;
    }

    public void update(float deltaTime) {
        if (!isMoving || currentPath.isEmpty()) {
            return;
        }
        
        moveProgress += moveSpeed * deltaTime;
        
        if (moveProgress >= 1.0f) {
            PathFinder.Node nextNode = currentPath.get(currentPathIndex);
            this.column = nextNode.x;
            this.row = nextNode.y;
            currentPathIndex++;
            
            if (currentPathIndex >= currentPath.size()) {
                currentPath.clear();
                isMoving = false;
                moveProgress = 0f;
            } else {
                moveProgress = 0f;
            }
        }
    }

    public float[] getPrecisePosition() {
        if (!isMoving || currentPath.isEmpty() || currentPathIndex >= currentPath.size()) {
            return new float[]{column, row};
        }
        
        PathFinder.Node nextNode = currentPath.get(currentPathIndex);
        float x = column + (nextNode.x - column) * moveProgress;
        float y = row + (nextNode.y - row) * moveProgress;
        
        return new float[]{x, y};
    }
}
