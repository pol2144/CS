package Client;

import Map.Tile;
import Sprites.Sprite;
import Sprites.Tree;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;

import static Map.Tile.*;

public class Client extends Application {
    public static VBox root;
    public static Sprite[][] sprites;

    public static TextField adressField;
    public static Button connectButton;
    public static Text confirmation;
    public static Socket socket;
    public static ServerHandler serverHandler;
    public static Stage stage;
    public static String[] argss;
    public static Tile[][] map;
    public static GridPane grid;
    public static AnchorPane gameRoot;
    public static boolean ready = false;
    public static int row=0;
    public static int col=0;

    public static void main(String[] args) {
        argss = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        adressField = new TextField();
        adressField.setPromptText("Type In Servers IP");
        adressField.setAlignment(Pos.CENTER);
        adressField.setMaxWidth(200);
        connectButton = new Button("Connect");
        connectButton.setOnAction(e -> {
            connect(adressField.getText());
        });
        confirmation = new Text("connected");
        root.getChildren().addAll(adressField, connectButton);
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void connect(String IP) {
        try {
            socket = new Socket(IP, Settings.PORT);
            serverHandler = new ServerHandler(socket);
            new Thread(serverHandler).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().addAll(confirmation);
    }

    public class ServerHandler implements Runnable {
        public Socket socket;
        public BufferedReader in;
        public PrintWriter out;

        public ServerHandler(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                String[] msg = null;
                try {
                    msg = in.readLine().split(";");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (msg[0]) {
                    case "start":
                        map = new Tile[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
                        confirmation.setText("starting");
                        try {
                            msg = in.readLine().split(";");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        int n = 0;
                        for (int r = 0; r < Settings.MAP_HEIGHT; r++) {
                            for (int c = 0; c < Settings.MAP_WIDTH; c++) {
                                map[r][c] = Map.Tile.toTile(msg[n]);
                                n++;
                            }
                        }
                        sprites = new Sprite[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
                        try {
                            msg = in.readLine().split(";");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        n = 0;
                        for (int r = 0; r < Settings.MAP_HEIGHT; r++) {
                            for (int c = 0; c < Settings.MAP_WIDTH; c++) {
                                switch (msg[n].split(",")[0]){
                                    case "Tree":
                                        sprites[r][c] = Tree.toTree(msg[n]);
                                        n++;
                                }
                            }
                        }
                        Platform.runLater(() -> lauchGame());




                }
            }
        }
    }

    private void lauchGame() {
        gameRoot = new AnchorPane();
        grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setLayoutX(0);
        grid.setLayoutY(0);
        displayMap();
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 1920, 1080);

        scene.setOnMouseMoved(event -> {
            int c = (int)event.getX();
            int r = (int)event.getY();

            if(c < 10 && col > 0) {
                col --;
            }

            if(c > Settings.VISIBLE_TILES_COLUMNS * Settings.TILE_SIZE_WIDTH - 10 && col < Settings.MAP_WIDTH) {
                col ++;
            }

            if(r < 10 && row > 0) {
                row --;
            }

            if(r > Settings.VISIBLE_TILES_ROWS * Settings.TILE_SIZE_HEIGHT - 10 && row < Settings.MAP_HEIGHT) {
                row ++;
            }

            displayMap();
            displaySprites();
        });

        stage.setScene(scene);
        stage.show();
    }

    private void displayMap() {
        for (int r = row; r < row+Settings.VISIBLE_TILES_ROWS; r++) {
            for (int c = col; c < col+Settings.VISIBLE_TILES_COLUMNS; c++) {
                ImageView img = null;
                if(map[r][c]==grass) {
                    img = new ImageView(Settings.MIDDLE_GRASS);
                    img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                    img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                    grid.add(img,r,c);
                }
                else if(map[r][c]==water) {
                    img = new ImageView(Settings.WATER);
                    img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                    img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                    grid.add(img,r,c);
                }
                else if(map[r][c]==sand) {
                    img = new ImageView(Settings.MIDDLE_SAND);
                    img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                    img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                    grid.add(img,r,c);
                }

            }
        }
    }
    public static void displaySprites(){
        for (int r = 0; r < Settings.MAP_HEIGHT; r++) {}

    }
}



