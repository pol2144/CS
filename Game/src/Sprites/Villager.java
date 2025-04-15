package Sprites;

import javafx.scene.image.ImageView;


public class Villager extends Sprite{
    private Type type;

    public Villager(int row, int column, ImageView imageView, Type type, int width, int heigth) {
        super(row, column, width, heigth);
        this.type = type;
    }
}
