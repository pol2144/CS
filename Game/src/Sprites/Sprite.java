package Sprites;


import javafx.scene.image.ImageView;

public class Sprite {
    private int number = 0;
    private int row;
    private int column;

    ImageView knightBlue = new ImageView("file:Tiny Swords/Factions/Knights/Troops/Warrior/Blue/Warrior_Blue.png");
    ImageView knightPurple = new ImageView("file:Tiny Swords/Factions/Knights/Troops/Warrior/Purple/Warrior_Purple.png");
    ImageView knightRed = new ImageView("file:Tiny Swords/Factions/Knights/Troops/Warrior/Red/Warrior_Red.png");
    ImageView knightYellow = new ImageView("file:Tiny Swords/Factions/Knights/Troops/Warrior/Yellow/Warrior_Yellow.png");
    
    public Sprite(int number, int row, int column, ImageView imageView) {
        this.column = column;
        this.row = row;
        this.number = number;
    }



    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNumber() {
        return number;
    }


}
