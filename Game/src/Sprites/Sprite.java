package Sprites;

import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Sprite extends ImageView {
    private int number = 0;
    int y;
    int x;
    int hp;
    double width;
    double height;
    private Type type;
    Player player = new Player(1,1,Type.BLUE);
    private List<PathFinder.Node> currentPath = new ArrayList<>();
    private PathFinder pathfinder = new PathFinder();

    public Sprite(int x, int y, int hp, double width, double height) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.width = width;
        this.height = height;
    }

    public void moveTo(int targetX, int targetY, Set<PathFinder.Node> obstacles) {
        PathFinder.Node start = new PathFinder.Node(this.x, this.y);
        PathFinder.Node goal = new PathFinder.Node(targetX, targetY);
        currentPath = pathfinder.findPath(start, goal, obstacles);
    }

    public void updatePosition() {
        if (!currentPath.isEmpty()) {
            PathFinder.Node nextNode = currentPath.remove(0);
            this.x = nextNode.x; // Grid index
            this.y = nextNode.y;
            this.setX(this.x * width); // Convert grid index to pixels
            this.setY(this.y * height);
        }
    }
}

