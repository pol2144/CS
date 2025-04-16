package Sprites;

public enum Images {
     TREE("Tiny Swords/Resources/Tree.png", 64, 64, 1, new int[]{1,2,3,4,5,6});

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

