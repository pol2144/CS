package Sprites;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Tree extends Sprite {
    private int number = 0;
    private int y;
    private int x;
    private int hp;
    private double width;
    private double height;
    Images Tree = Images.TREE;

    public Tree(int x, int y, int hp, double width, double height) {
        super(x, y, hp, width, height);
        MultiAnimatedSprite tree = new MultiAnimatedSprite(10, x, y, 5, Tree);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0 / 6), event -> tree.changeAnimationTo(number++ % Tree.animationNumber)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public String toString() {
        return "Tree" + "," + x + "," + y + "," + hp + "," + width + "," + height;
    }

    public static Tree toTree(String string) {
        return new Tree(Integer.parseInt(string.split(",")[1]),Integer.parseInt(string.split(",")[2]), Integer.parseInt(string.split(",")[3]), Double.parseDouble(string.split(",")[4]), Double.parseDouble(string.split(",")[5]));
    }

}
