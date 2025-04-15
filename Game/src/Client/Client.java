package Client;

import Map.Tile;
import javafx.application.Application;
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

import java.io.*;
import java.net.Socket;

import static Map.Tile.*;

public class Client extends Application {
    public static VBox root;
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
                        ready = true;


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
        stage.setScene(scene);
        stage.show();
    }

    private void displayMap() {
        for (int r = 0; r < Settings.MAP_HEIGHT; r++) {
            for (int c = 0; c < Settings.MAP_WIDTH; c++) {
                ImageView img = null;
                if(map[r][c]==grass){
                    img = new ImageView(Settings.MIDDLE_GRASS);
                    img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                    img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                    grid.add(img,r,c);
                }else if(map[r][c]==water){
                    img = new ImageView(Settings.WATER);
                    img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                    img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                    grid.add(img,r,c);
                }else if(map[r][c]==sand){
                    img = new ImageView(Settings.MIDDLE_SAND);
                    img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                    img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                    grid.add(img,r,c);
                }

            }
        }
    }
}



