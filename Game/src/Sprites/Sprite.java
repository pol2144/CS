package Sprites;

import Server.GameServer;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {
    private int number = 0;
    private int y;
    private int x;
    private int hp;
    private double width;
    private double height;

    private Type type;

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
        if (dx == 0 && dy == 0) {
            return;
        }
        for (int i = 0; i < dx; i++) {
            if (this.x > dx) {
                this.setX(this.getX() - 1);
            } else if (this.x < dx) {
                this.setX(this.getX() + 1);
            }
        }
    }
}
//GameServer.map[(int) (this.getY() + dy)][(int) (this.getX() + dx)].isWalkable()

