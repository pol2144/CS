package Sprites;

import Client.Settings;
import javafx.scene.image.ImageView;

public class Tree extends Sprite {
    Images tree = Images.valueOf("Tree");

    public Tree(int row, int column, int hp, ImageView imageView, int width, int heigth) {
        super(row, column, hp, width, heigth);
    }

    public void addTreesAuto(int row, int column) {
        for (int r = 0; r < 0; r++) {
            for (int c = 0; c < 0; c++) {
                if (Math.random() < 0.1) {
                    new Tree(r, c, 100, new ImageView(tree.toString()), Settings.TILE_SIZE_WIDTH, Settings.TILE_SIZE_HEIGHT);
                }
            }
        }
    }
}
