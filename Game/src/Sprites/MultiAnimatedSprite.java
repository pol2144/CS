package Sprites;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class MultiAnimatedSprite extends Sprite {
    int currentAnimationRow;
    private int currentAnimationFrame;

    public MultiAnimatedSprite(int hp, int x, int y, int animationSpeed, Images imageFromEnum) {
        super(x, y, hp, imageFromEnum.width, imageFromEnum.height);
        setImage(new Image("file:" + imageFromEnum.filename));

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0 / animationSpeed), event -> {
            currentAnimationFrame = (currentAnimationFrame + 1) % imageFromEnum.frameCounts[currentAnimationRow];
            setViewport(new Rectangle2D(currentAnimationFrame * imageFromEnum.width,
                    currentAnimationRow * imageFromEnum.height,
                    imageFromEnum.width,
                    imageFromEnum.height));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void changeAnimationTo(int row) {
        currentAnimationRow = row;
    }


}
