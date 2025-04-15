package Sprites;

import javafx.scene.image.ImageView;

public class Knight extends Sprite{
    private Type type;

    public Knight( int row, int column, int hp, ImageView imageView, int width, int heigth, Type type) {
        super(row, column, hp, width, heigth);
        this.type = type;
    }

}
