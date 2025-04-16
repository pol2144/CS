package Map;

import Sprites.Images;
import Sprites.MultiAnimatedSprite;
import Sprites.Sprite;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Client.Settings;
import sun.reflect.generics.tree.Tree;

public class Test extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        Tile[][] tiles = Map.generateMap();

        for (int r = 0; r < Settings.MAP_HEIGHT ; r++) {
            for (int c = 0; c < Settings.MAP_WIDTH ; c++) {
                if(tiles[r][c] == Tile.grass ) {
                    ImageView imageview = new ImageView(Settings.MIDDLE_GRASS);
                    imageview.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                    imageview.setFitWidth(Settings.TILE_SIZE_WIDTH);
                    gridPane.add(imageview,c,r);
                    if (Math.random()*10 < 1) {
                        Sprite tree = new MultiAnimatedSprite(10, c, r, 5, Images.TREE);
                        gridPane.add(tree, c, r);
                        if (tree instanceof MultiAnimatedSprite) {
                            ((MultiAnimatedSprite) tree).changeAnimationTo(2);
                        }
                        if (tree instanceof MultiAnimatedSprite) {
                            ((MultiAnimatedSprite) tree).changeAnimationTo(0);
                        }
                    }
                }
            }
        }
        Scene scene = new Scene(gridPane,1920,1080);
        stage.setScene(scene);
        stage.show();
    }

}


