package Map;

import Client.Settings;

public class Map {
    private static Tile[][] tiles = new Tile[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
    //Sprite[][] sprites;

    public static Tile[][] generateMap() {
        for (int r = 0; r < tiles.length ; r++) {
            for (int c = 0; c < tiles[0].length ; c++) {
                tiles[r][c] = Tile.grass;
            }
        }
        return tiles;
    }

    public static String convertToString(Tile[][] map) {
        String result = "";
        for(Tile[] T : tiles)for(Tile t: T){
            result += t.toString() + ";";
        }
        return result;
    }
}
