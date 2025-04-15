package Sprites;


import Client.Settings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
    private int number = 0;
    private int row;
    private int column;
    private int hp;
    private int width;
    private int height;

//    ImageView knightBlue = new ImageView(Settings.KNIGHT_BLUE);
//    ImageView knightPurple = new ImageView(Settings.KNIGHT_PURPLE);
//    ImageView knightRed = new ImageView(Settings.KNIGHT_RED);
//    ImageView knightYellow = new ImageView(Settings.KNIGHT_YELLOW);

    private Type type;


    public Sprite(int hp, int row, int column, int width, int heigth) {
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
