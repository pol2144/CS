package Map;

public enum Tile {
    water, grass, sand;

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
