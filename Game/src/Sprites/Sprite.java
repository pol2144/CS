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

    public Sprite(int hp, int x, int y, double width, double heigth) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.width = width;
        this.height = heigth;
    }


    public void move(double dx, double dy) {
        setLayoutX(getLayoutX() + dx);
        setLayoutY(getLayoutY() + dy);
        x += dx;
        y += dy;
    }


    public Type getType() {return type;}
    public int getNumber() {
        return number;
    }

}
