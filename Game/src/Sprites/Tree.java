package Sprites;

import Client.Settings;
import javafx.scene.image.ImageView;

public class Tree extends Sprite {
    public static Images tree = Images.valueOf("Tree");
    public static SingleAnimatedSprite singleAnimatedSprite;

    public Tree(int row, int column, int hp, ImageView imageView, double width, double heigth) {
        super(row, column, hp, width, heigth);
        singleAnimatedSprite = new SingleAnimatedSprite(row, column, hp, width, heigth, tree.toString(), 5, true);
    }

    public static void addTrees(int row, int column) {
        for (int r = 0; r < Settings.MAP_HEIGHT; r++) {
            for (int c = 0; c < Settings.MAP_WIDTH; c++) {
                if (Math.random() < 0.1) {
                    new Tree(r, c, 10, new ImageView(tree.toString()), Settings.TILE_SIZE_WIDTH, Settings.TILE_SIZE_HEIGHT);
                }
            }
        }
    }
}
