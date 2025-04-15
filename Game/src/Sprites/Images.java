package Sprites;

public enum Images {
    PLAYER("images/Character 1.png", 64, 64, 6, new int[]{4, 4, 6, 6, 3, 4}),
    ;

    public final String filename;
    public final int width;
    public final int height;
    public final int animationNumber;
    public final int[] frameCounts;

    Images(String filename, int width, int height, int animationNumber, int[] frameCounts) {
        this.filename = filename;
        this.width = width;
        this.height = height;
        this.animationNumber = animationNumber;
        this.frameCounts = frameCounts;
    }
}

