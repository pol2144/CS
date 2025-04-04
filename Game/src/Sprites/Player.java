package Sprites;

public class Player {
    private int row;
    private int column;

    private Sprite sprite;

    public Player(int row, int column, Sprite sprite) {
        this.row = row;
        this.column = column;
        this.sprite = sprite;
    }

    public void move(int newRow, int newColumn) {

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
