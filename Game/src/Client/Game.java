package Client;

import Map.Tile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;



public class Game  extends Application {
    public static BufferedReader in;
    public static BufferedReader consoleIn;
    public static PrintWriter out;
    public static Socket socket;
    public static Scanner scanner = new Scanner(System.in);
    public static Tile[][] map = new Tile[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
    Stage stage;
    AnchorPane root;
    GridPane gridPane;
    public static int r;
    public static int c;

    public static void main(String[] args) {
        connectToServer();
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            consoleIn = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                if(in.ready()) {
                    String msg  = in.readLine();
                    switch(msg) {
                        case "start":
                            startGame(args);
                            break;
                        default:
                            System.out.println(msg);
                            break;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(consoleIn.ready()){
                    out.println(consoleIn.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startGame(String[] args) {
        System.out.println("game is starting");
        String input[];
        try {
            input = in.readLine().split(";");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int n = 0;
        for (int r = 0; r < Settings.MAP_HEIGHT; r++) {
            for (int c = 0; c < Settings.MAP_WIDTH; c++) {
                map[r][c] = Map.Tile.toTile(input[n]);
                n++;
            }
        }
        launch(args);
    }

    private static void connectToServer() {
        try {
            System.out.println("Please type in your servers address: ");
            String serverAddress = scanner.nextLine();
            socket =  new Socket(serverAddress,Settings.PORT);
            System.out.println("Connected to " + serverAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = new Stage();
        root = new AnchorPane();
        gridPane = new GridPane();
    }

    public void displayMap() {
        for (int r = 0; r < Settings.VISIBLE_TILES_ROWS; r++) {
            for (int c = 0; c < Settings.VISIBLE_TILES_COLUMNS; c++) {
                ImageView img = null;
                switch(map[r][c]) {
                    case grass:
                        img = new ImageView(Settings.MIDDLE_GRASS);
                        img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                        img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                        gridPane.add(img,r,c);
                        break;
                    case sand:
                        img = new ImageView(Settings.MIDDLE_SAND);
                        img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                        img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                        gridPane.add(img,r,c);
                        break;
                    case water:
                        img = new ImageView(Settings.WATER);
                        img.setFitWidth(Settings.TILE_SIZE_WIDTH);
                        img.setFitHeight(Settings.TILE_SIZE_HEIGHT);
                        gridPane.add(img,r,c);
                        break;
                }
            }
        }
        gridPane.setLayoutX(0);
        gridPane.setLayoutY(0);
        root.getChildren().add(gridPane);
        Scene scene = new Scene(root,1920,1080);
        stage.setScene(scene);
        stage.setTitle("Super Amazing Strategy Game");
        stage.setResizable(false);
        stage.show();
        new Thread(new ClientHandler()).start();
    }

    private class ClientHandler implements Runnable {
        public void run() {
            while(true){
                String[] msg = null;
                try {
                    msg = in.readLine().split(";");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] dir;
            switch(msg[0]){
                case "move":

                    //spriteID, goal
                    break;
                case "attack":

                    //spriteID attacker, spriteID attacked, demage
                    break;
                case "build":

                    //spriteID, x, y , buildingType
            }
        }
        }
    }
}
