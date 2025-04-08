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

        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[0].length; c++) {
                if(tiles[r][c] == Tile.grass) {
                    if (Math.random() > 0.95) {
                        tiles[r][c] = Tile.water;
                        SpillWater(r,c,5);
                    }
                }
            }
        }
        return tiles;
    }

    public static void SpillWater(int r, int c,int depth) {
        if(depth<1)return;
        if(tiles[r][c] == Tile.water) {
            if(r-1 > 0 && c-1 > 0) {
                if (Math.random() > 0.5) {
                    tiles[r-1][c-1] = Tile.water;
                    SpillWater(r - 1, c - 1,depth-1);
                }
                else {
                    tiles[r-1][c-1] = Tile.sand;
                }
            }

            if(r+1 < tiles.length && c-1 > 0) {
                if (Math.random() > 0.5) {
                    tiles[r+1][c-1] = Tile.water;
                    SpillWater(r + 1, c - 1,depth-1);
                }
                else {
                    tiles[r+1][c-1] = Tile.sand;
                }
            }

            if(r+1 < tiles.length && c+1 < tiles.length) {
                if (Math.random() > 0.5) {
                    tiles[r+1][c+1] = Tile.water;
                    SpillWater(r + 1, c + 1,depth-1);
                }
                else {
                    tiles[r+1][c+1] = Tile.sand;
                }
            }

            if(r-1 > 0 && c+1 < tiles[0].length) {
                if (Math.random() > 0.5) {
                    tiles[r-1][c+1] = Tile.water;
                    SpillWater(r - 1, c + 1,depth-1);
                }
                else {
                    tiles[r-1][c+1] = Tile.sand;
                }
            }

            if(c-1 > 0) {
                if (Math.random() > 0.5) {
                    tiles[r][c-1] = Tile.water;
                    SpillWater(r, c - 1,depth-1);
                }
                else {
                    tiles[r][c-1] = Tile.sand;
                }
            }

            if(r-1 > 0) {
                if (Math.random() > 0.5) {
                    tiles[r-1][c] = Tile.water;
                    SpillWater(r - 1, c,depth-1);
                }
                else {
                    tiles[r-1][c] = Tile.sand;
                }
            }

            if(r + 1 > tiles.length) {
                if (Math.random() > 0.5) {
                    tiles[r+1][c] = Tile.water;
                    SpillWater(r + 1, c,depth-1);
                }
                else {
                    tiles[r+1][c] = Tile.sand;
                }
            }

            if(c+1 < tiles[0].length) {
                if (Math.random() > 0.5) {
                    tiles[r][c+1] = Tile.water;
                    SpillWater(r, c + 1,depth-1);
                }
                else {
                    tiles[r][c+1] = Tile.sand;
                }
            }
        }
    }

    public static String convertToString(Tile[][] map) {
        String result = "";
        for(Tile[] T : tiles)for(Tile t: T){
            result += t.toString() + ";";
        }
        return result;
    }

}
