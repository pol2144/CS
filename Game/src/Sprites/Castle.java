package Sprites;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Castle extends Sprite{
    private int number = 0;
    private int y;
    private int x;
    private int hp;
    private double width;
    private double height;
    Images CastleBlue = Images.CASTLE_BLUE;
    Images CastleRed = Images.CASTLE_RED;
    Images CastleYellow = Images.CASTLE_YELLOW;
    Images CastlePuprple = Images.CASTLE_PURPLE;
    Images Castle;
    Player player = new Player(0,0,Type.BLUE);

    public Castle(int x, int y, int hp, double width, double height) {
        super(x,y,hp,width,height);
        if(player.getType() == Type.BLUE){
            Castle = CastleBlue;
        }
        else if (player.getType() == Type.RED){
            Castle = CastleRed;
        }
        else if (player.getType() == Type.YELLOW){
            Castle = CastleYellow;
        }
        else if (player.getType() == Type.PURPLE){
            Castle = CastlePuprple;
        }
        MultiAnimatedSprite castle = new MultiAnimatedSprite(10, x, y, 5, Castle );
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0 / 6), event -> castle.changeAnimationTo(number++ % Castle.animationNumber)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public String toString() {
        return "Building" + "," + x + "," + y + "," + hp + "," + width + "," + height;
    }

    public static Castle toCastle(String string) {
        return new Castle(Integer.parseInt(string.split(",")[1]), Integer.parseInt(string.split(",")[2]), Integer.parseInt(string.split(",")[3]), Double.parseDouble(string.split(",")[4]), Double.parseDouble(string.split(",")[5]));
    }
}
