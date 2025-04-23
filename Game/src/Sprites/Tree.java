package Sprites;


import javafx.scene.image.ImageView;

public class Tree extends ImageView {
    private int number = 0;
    private int y;
    private int x;
    private int hp;
    private double width;
    private double height;

    public Tree(int x, int y, int hp, double width, double height) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.width = width;
        this.height = height;
    }

}
