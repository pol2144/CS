package Sprites;


import Client.Settings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    private int number = 0;
    private int row;
    private int column;
    private int hp;
    private double width;
    private double height;

    private Type type;

    public Sprite(int hp, int row, int column, double width, double heigth) {
        this.column = column;
        this.row = row;
        this.hp = hp;
        this.width = width;
        this.height = heigth;
    }


    public Type getType() {return type;}

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNumber() {
        return number;
    }


}
