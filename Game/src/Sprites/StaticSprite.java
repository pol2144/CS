package Sprites;


public class StaticSprite {
    private int number = 0;
    private int y;
    private int x;
    private int hp;
    private double width;
    private double height;

    public static final Images tree = Images.valueOf("Tree");
    public StaticSprite(int x, int y, int hp, double width, double height) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.width = width;
        this.height = height;
    }

}
