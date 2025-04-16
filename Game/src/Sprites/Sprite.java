package Sprites;

import javafx.scene.image.ImageView;

public class Sprite extends ImageView{
    private int number = 0;
    private int y;
    private int x;
    private int hp;
    private double width;
    private double height;

    private Type type;

    public Sprite(int x, int y, int hp, double width, double height) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.width = width;
        this.height = height;
    }

    public void move(double dx, double dy) {
        setLayoutX(getLayoutX() + dx);
        setLayoutY(getLayoutY() + dy);
        x += dx;
        y += dy;
    }
}
