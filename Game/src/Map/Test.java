package Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import Client.Settings;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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
                    //gridPane.add(imageview,c,r);

                }
            }
        }
        Scene scene = new Scene(gridPane,1920,1080);
        stage.setScene(scene);
        stage.show();
    }

}


