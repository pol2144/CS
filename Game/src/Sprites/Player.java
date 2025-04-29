package Sprites;

public class Player {
    private Type type;
    private int row;
    private int column;

    public Player(int row, int column, Type type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }
    public Type getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
