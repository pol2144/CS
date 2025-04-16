package Map;

public enum Tile {
    water, grass, sand,
    ALL_EDGES_SAND, ALL_EDGES_GRASS,RIGHT_TOP_BOTTOM_SAND,RIGHT_TOP_BOTTOM_GRASS,
    MIDDLE_TOP_BOTTOM_SAND,MIDDLE_TOP_BOTTOM_GRASS,LEFT_TOP_BOTTOM_SAND, LEFT_TOP_BOTTOM_GRASS, BOTTOM_LEFT_RIGHT_GRASS,
    BOTTOM_LEFT_RIGHT_SAND, BOTTOM_RIGHT_SAND,BOTTOM_RIGHT_GRASS,BOTTOM_MIDDLE_SAND, BOTTOM_MIDDLE_GRASS,BOTTOM_LEFT_SAND,
    BOTTOM_LEFT_GRASS, MIDDLE_LEFT_RIGHT_SAND,MIDDLE_LEFT_RIGHT_GRASS,MIDDLE_RIGHT_SAND, MIDDLE_RIGHT_GRASS,
    MIDDLE_MIDDLE_SAND, MIDDLE_LEFT_SAND, TOP_LEFT_RIGHT_SAND, TOP_LEFT_RIGHT_GRASS, TOP_RIGHT_CORNER_SAND,
    TOP_RIGHT_CORNER_GRASS, TOP_MIDDLE_SAND, TOP_MIDDLE_GRASS, TOP_LEFT_CORNER_SAND, TOP_LEFT_CORNER_GRASS, MIDDLE_LEFT_GRASS;


    public static Tile toTile(String string) {
        if(string.equals("water"))return water;
        if(string.equals("grass"))return grass;
        if(string.equals("sand"))return sand;
        return null;
    }

    public String toString(){
        if(this == water)return "water";
        if(this == grass)return "grass";
        if(this == sand)return "sand";
        return "";
    }
}
