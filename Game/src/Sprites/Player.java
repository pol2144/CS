package Sprites;

public class Player {
    private Type type;
    private int row;
    private int column;

    public Player(int row, int column, Sprite sprite, Type type) {
        this.row = row;
        this.column = column;
        this.type = type;
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
