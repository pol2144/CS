package Sprites;

public class Tree extends Sprite {

    public Tree(int x, int y, int hp, double width, double height) {
        super(x, y, hp, width, height);

    }

    @Override
    public String toString() {
        return "Tree" + "," + x + "," + y + "," + hp + "," + width + "," + height;
    }

    public static Tree toTree(String string) {
        String[] parts = string.split(",");
        return new Tree(
                Integer.parseInt(parts[1]),  // x
                Integer.parseInt(parts[2]),  // y
                Integer.parseInt(parts[3]),  // hp
                Double.parseDouble(parts[4]), // width
                Double.parseDouble(parts[5])  // height
        );
    }
}