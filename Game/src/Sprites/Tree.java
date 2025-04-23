package Sprites;


import javafx.scene.image.ImageView;

public class Tree extends ImageView {
    private int number = 0;
    private int y;
    private int x;
    private int hp;
    private double width;
    private double height;

    public Tree(int x, int y, int hp, double width, double height) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Tree" + "," + x + "," + y + "," + hp + "," + width + "," + height;
    }

    public static Tree toTree(String string) {
        return new Tree(Integer.parseInt(string.split(",")[1]),Integer.parseInt(string.split(",")[2]), Integer.parseInt(string.split(",")[3]), Double.parseDouble(string.split(",")[4]), Double.parseDouble(string.split(",")[5]));
    }

}
