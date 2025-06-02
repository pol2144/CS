package Map;

import Sprites.PathFinder;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PathingTest extends Application {
    private static final int GRID_SIZE = 10;
    private static final int TILE_SIZE = 50;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        testPathFinder();
    }

    private void testPathFinder() {
        GridPane grid = new GridPane();
        Rectangle[][] tiles = new Rectangle[GRID_SIZE][GRID_SIZE];

        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);
                grid.add(rect, x, y);
                tiles[y][x] = rect;
            }
        }

        PathFinder pathFinder = new PathFinder();
        Set<PathFinder.Node> obstacles = new HashSet<>();

        // Add some obstacles (black tiles)
        for (int i = 0; i < GRID_SIZE; i++) {
            obstacles.add(new PathFinder.Node(3, i));
            tiles[i][6].setFill(Color.BLACK);
        }
        // Leave a gap in the wall
        tiles[6][3].setFill(Color.WHITE);
        obstacles.remove(new PathFinder.Node(3, 6));

        // Define start (green) and end (red) points
        PathFinder.Node start = new PathFinder.Node(1, 1);
        PathFinder.Node goal = new PathFinder.Node(7, 5);

        // Mark start and goal
        tiles[start.y][start.x].setFill(Color.GREEN);
        tiles[goal.y][goal.x].setFill(Color.RED);

        // Find the path
        List<PathFinder.Node> path = pathFinder.findPath(start, goal, obstacles);

        // Mark the path (blue)
        for (PathFinder.Node node : path) {
            if ((node.x != start.x || node.y != start.y) &&
                    (node.x != goal.x || node.y != goal.y)) {
                tiles[node.y][node.x].setFill(Color.BLUE);
            }
        }

        // Display the grid
        Scene scene = new Scene(grid, GRID_SIZE * TILE_SIZE, GRID_SIZE * TILE_SIZE);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("PathFinder Test");
        stage.show();

        // Print the path coordinates
        System.out.println("Path found with " + path.size() + " steps:");
        for (PathFinder.Node node : path) {
            System.out.println("(" + node.x + ", " + node.y + ")");
        }
    }
}

