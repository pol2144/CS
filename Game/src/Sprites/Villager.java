package Sprites;

import javafx.scene.image.ImageView;


public class Villager extends Sprite{
    private Type type;

    public Villager(int row, int column, ImageView imageView,int hp , Type type, double width, double heigth) {
        super(row, column,hp, width, heigth);
        this.type = type;
    }
}
