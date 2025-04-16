package Sprites;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SingleAnimatedSprite extends Sprite implements Drawable {
    private List<Image> images = new ArrayList<>();
    private int currentFrame;
    private int animationSpeed;
    private int cooldawn;
    private ImageView imageView;

    public SingleAnimatedSprite(int x, int y, int hp, double width, double height,
                                String folder, int animationSpeed, boolean fromRandomFrame) {
        super(x, y, hp, width, height);
        File[] allFilesAndFolders = new File(folder).listFiles();

        for (int i = 0; i < allFilesAndFolders.length; i++) {
            if (allFilesAndFolders[i].isFile() && allFilesAndFolders[i].getName().endsWith(".png")) {
                Image image = new Image("file:" + allFilesAndFolders[i].getAbsolutePath());
                images.add(image);
            }
        }
        this.animationSpeed = animationSpeed;
        if (fromRandomFrame) {
            currentFrame = (int) (Math.random() * images.size());
        }
        setImage(images.get(currentFrame));

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000.0 / animationSpeed), event -> {
            currentFrame = (currentFrame + 1) % images.size();
            setImage(images.get(currentFrame));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @Override
    public void draw() {}
}

