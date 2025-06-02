package Sprites;

public enum Images {
    TREE("Tiny Swords/Resources/Tree.png", 64, 64, 1, new int[]{1,2,3,4,5,6}),
    CASTLE_BLUE("Tiny Swords/Factions/Knights/Buildings/Castle/Castle_Blue.png" , 64, 64, 1, new int[]{}),
    CASTLE_YELLOW("Tiny Swords/Factions/Knights/Buildings/Castle/Castle_Yellow" , 64, 64, 1, new int[]{0}),
    CASTLE_PURPLE("Tiny Swords/Factions/Knights/Buildings/Castle/Castle_Purple.png" , 64, 64, 1, new int[]{0}),
    CASTLE_RED("Tiny Swords/Factions/Knights/Buildings/Castle/Castle_Red.png" , 64, 64, 1, new int[]{0}),
    PLAYER("Tiny Swords/Factions/Knights/Troops/Pawn/Blue/Pawn_Blue.png", 64, 64, 1, new int[]{0});

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

