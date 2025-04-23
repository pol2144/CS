package Server;

import Map.Map;
import Map.Tile;
import Sprites.Sprite;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

import Client.Settings;



public class WindowedServer extends Application {
    public static Tile[][] map;
    public static Sprite[][] sprites = new Sprite[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
    public static Stage stage;
    public static Text connectingLabel;
    public static Text playerCounter;
    public static ServerState serverState;
    public static PlayerHandler[] playerHandlers = new PlayerHandler[Settings.PLAYER_LIMIT];
    public static int playerCount = 0;
    public static ServerSocket serverSocket;

    public static void main(String[] args) {
        socketSetup();
        launch(args);
    }

    private static void socketSetup() {
        try {
            serverSocket = new ServerSocket(Settings.PORT);
        } catch (IOException e) {
            e.printStackTrace();
     }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        serverState = ServerState.WaitingForCommand;
        stage = primaryStage;
        stage.setTitle("Game Server");
        stage.setResizable(false);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        Text adressText = new Text("Servers Adress:\n"+ Inet4Address.getLocalHost().getHostAddress());
        playerCounter = new Text( playerCount+" Players Connected");
        Button  connectButton = new Button("Connect Player");
        connectButton.setOnAction(e -> {
            connectingLabel.setVisible(true);
            connectPlayer();
            connectingLabel.setVisible(false);
        });
        connectingLabel = new Text("Waiting For Player...");
        connectingLabel.setVisible(false);
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            startGame();
        });
        vBox.getChildren().addAll(adressText,playerCounter, connectButton,connectingLabel,startButton);
        Scene scene = new Scene(vBox,400,400);
        stage.setScene(scene);
        stage.show();
    }

    private void connectPlayer() {
        if(playerCount<Settings.PLAYER_LIMIT) {
            serverState = ServerState.WaitingForPlayers;
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            playerHandlers[playerCount] = new PlayerHandler(socket);
            new Thread(playerHandlers[playerCount]).start();
            playerCount++;
            playerCounter.setText(playerCount+" Players Connected");
            connectingLabel.setVisible(false);
            serverState = ServerState.WaitingForCommand;
        }else{
            connectingLabel.setVisible(true);
            connectingLabel.setText("full capacity");
        }
    }

    private class PlayerHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        public PlayerHandler(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            out.println("connected");
            String[] msg = new String[0];
            while(true){
                try {
                    msg = in.readLine().split(";");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch(msg[0]){
                    case "connect":
                        connectPlayer();
                        break;
                    case "start":
                        startGame();
                        break;
                }
            }
        }
    }

    private void startGame() {
        serverState = ServerState.WorldGeneration;
        map = Map.generateMap();
        serverState = ServerState.Playing;
        sendEveryone("start");
        sendEveryone(Map.convertToString(map));
        sendEveryone(Map.convertToString(sprites));

    }

    private void sendEveryone(String msg) {
        for (int i = 0; i < playerCount; i++) {
            playerHandlers[i].out.println(msg);
        }
    }
}
