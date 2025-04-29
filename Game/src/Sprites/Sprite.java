package Sprites;

import Server.GameServer;
import javafx.scene.image.ImageView;

import java.security.spec.PKCS8EncodedKeySpec;

public class Sprite extends ImageView {
    private int number = 0;
    private int y;
    private int x;
    private int hp;
    private double width;
    private double height;
    private Type type;
    Player player = new Player(1,1,Type.BLUE);

    public Sprite(int x, int y, int hp, double width, double height) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.width = width;
        this.height = height;
    }

    private void teleport(double dx, double dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }

    private void pathing(double dx, double dy) {
        for (int i = 0; i < dx; i++) {
            double currentX = Math.abs(player.getRow() - dx);
            double currentY = Math.abs(player.getColumn() - dy);
            double distance = Math.sqrt(currentX * currentX + currentY * currentY);

            if (dx == player.getRow() && dy == player.getColumn()) {
                return;
            }
        }
    }
}

