package Server;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Map.Map;
import Map.Tile;

public class GameServer {
    public static ServerState state;
    public static ServerSocket serverSocket;
    public static Socket[] playerSockets = new Socket[4];
    public static BufferedReader[] playerInputs = new BufferedReader[4];
    public static PrintWriter[] playerOutputs = new PrintWriter[4];
    public static int playerCount = 0;
    public static Scanner scanner;
    public static Tile[][] map;
    public static void main(String[] args) {
        setup();
        connectPlayer();
        String msg="";
        while (true){
            state = ServerState.WaitingForCommand;
            for (int i = 0; i < playerCount; i++) {
                try {
                    if(playerInputs[i].ready()){
                        msg = playerInputs[i].readLine();
                        switch (msg) {
                            case "connect":
                                connectPlayer(i);
                                break;
                            case "start":
                                startGame(i);
                                break;
                            case "help":
                                System.out.println("List of available commands: connect, start, help, exit");
                                playerOutputs[i].println("List of available commands: connect, start, help, exit");
                            case "exit":
                                break;
                            default:
                                for (int n= 0; n < playerCount; n++) {
                                    if(n!=i) {
                                        playerOutputs[n].println("Player"+String.valueOf(i+1)+": "+msg);
                                    }
                                }
                                break;

                        }
                        if(msg.equals("exit")) break;

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(msg.equals("exit")) break;



        }
    }

    private static void sendEveryone(String msg) {
        for (int i = 0; i < playerCount; i++) {
            playerOutputs[i].println(msg);
        }
    }


    private static void startGame(int commandCaller) {
        state = ServerState.Playing;
        System.out.println("Starting game server...");
        playerOutputs[commandCaller].println("Starting game server...");
        sendEveryone("start");
        map = Map.generateMap();
        sendEveryone(Map.convertToString(map));
        //map generation and other needed things
    }

    private static void setup() {
        try {
            serverSocket = new ServerSocket(2144);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void connectPlayer() {
        state = ServerState.WaitingForPlayers;
        if(playerCount<playerSockets.length){
            try {
                System.out.println("the serves address is: "+ Inet4Address.getLocalHost());
                System.out.println("waiting for connection...");

                playerSockets[playerCount] = serverSocket.accept();
                playerInputs[playerCount] = new BufferedReader(new InputStreamReader(playerSockets[playerCount].getInputStream()));
                playerOutputs[playerCount] = new PrintWriter(playerSockets[playerCount].getOutputStream(), true);
                playerCount++;
                System.out.println("player "+playerCount+" connected");
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            state = ServerState.Error;
            System.out.println("full capacity reached");
        }
    }
    private static void connectPlayer(int commandCaller ) {
        state = ServerState.WaitingForPlayers;
        if(playerCount<playerSockets.length){
            try {
                System.out.println("the serves address is: "+ Inet4Address.getLocalHost());
                System.out.println("waiting for connection...");
                playerOutputs[commandCaller].println("the serves address is: "+ Inet4Address.getLocalHost());
                playerOutputs[commandCaller].println("waiting for connection...");
                playerSockets[playerCount] = serverSocket.accept();
                playerInputs[playerCount] = new BufferedReader(new InputStreamReader(playerSockets[playerCount].getInputStream()));
                playerOutputs[playerCount] = new PrintWriter(playerSockets[playerCount].getOutputStream(), true);
                playerCount++;
                System.out.println("player "+playerCount+" connected");
                playerOutputs[commandCaller].println("player "+playerCount+" connected");
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            state = ServerState.Error;
            System.out.println("full capacity reached");
        }
    }
}
