package Map;

import Client.Settings;
import Sprites.Images;
import Sprites.MultiAnimatedSprite;
import Sprites.StaticSprite;
import sun.reflect.generics.tree.Tree;

public class Map {
    public static Tile[][] tiles = new Tile[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
    static MultiAnimatedSprite[][] sprites = new MultiAnimatedSprite[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
    public static final double spillChance = 0.8;
    public static final double sourcechance = 0.003;
    public static Tile[][] generateMap() {
        for (int r = 0; r < tiles.length ; r++) {
            for (int c = 0; c < tiles[0].length ; c++) {
                tiles[r][c] = Tile.grass;
            }
        }

        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[0].length; c++) {
                if(tiles[r][c] == Tile.grass) {
                    if (Math.random() < sourcechance) {
                        tiles[r][c] = Tile.water;
                        SpillWater(r,c, (int) (Math.random()*10));
                    }
                }
            }
        }
        return tiles;
    }

    public static void SpillWater(int r, int c,int depth) {
        if(depth<1) {
                surroundSand(r,c);
                return;
        }else if(depth<4) {
            if(r-1 >= 0 && c-1 >= 0) {
                    tiles[r-1][c-1] = Tile.water;
                    SpillWater(r - 1, c - 1,depth-1);
            }

            if(r+1 < tiles.length && c-1 >= 0) {
                    tiles[r+1][c-1] = Tile.water;
                    SpillWater(r + 1, c - 1,depth-1);

            }

            if(r+1 < tiles.length && c+1 < tiles.length) {
                    tiles[r+1][c+1] = Tile.water;
                    SpillWater(r + 1, c + 1,depth-1);
            }

            if(r-1 >= 0 && c+1 < tiles[0].length) {
                    tiles[r-1][c+1] = Tile.water;
                    SpillWater(r - 1, c + 1,depth-1);

            }

            if(c-1 >= 0) {
                    tiles[r][c-1] = Tile.water;
                    SpillWater(r, c - 1,depth-1);

            }

            if(r-1 >= 0) {
                    tiles[r-1][c] = Tile.water;
                    SpillWater(r - 1, c,depth-1);
            }

            if(r + 1 < tiles.length) {
                    tiles[r+1][c] = Tile.water;
                    SpillWater(r + 1, c,depth-1);
            }

            if(c+1 < tiles[0].length) {
                    tiles[r][c+1] = Tile.water;
                    SpillWater(r, c + 1,depth-1);
            }
        }else {
            if (r - 1 > 0 && c - 1 > 0) {
                if (Math.random() < spillChance) {
                    tiles[r - 1][c - 1] = Tile.water;
                    SpillWater(r - 1, c - 1, depth - 1);
                } else {
                    tiles[r - 1][c - 1] = Tile.sand;
                }
            }


            if (r + 1 < tiles.length && c - 1 > 0) {
                if (Math.random() > spillChance) {
                    tiles[r + 1][c - 1] = Tile.water;
                    SpillWater(r + 1, c - 1, depth - 1);
                } else {
                    tiles[r + 1][c - 1] = Tile.sand;
                }
            }

            if (r + 1 < tiles.length && c + 1 < tiles.length) {
                if (Math.random() > spillChance) {
                    tiles[r + 1][c + 1] = Tile.water;
                    SpillWater(r + 1, c + 1, depth - 1);
                } else {
                    tiles[r + 1][c + 1] = Tile.sand;
                }
            }

            if (r - 1 > 0 && c + 1 < tiles[0].length) {
                if (Math.random() > spillChance) {
                    tiles[r - 1][c + 1] = Tile.water;
                    SpillWater(r - 1, c + 1, depth - 1);
                } else {
                    tiles[r - 1][c + 1] = Tile.sand;
                }
            }

            if (c - 1 > 0) {
                if (Math.random() > spillChance) {
                    tiles[r][c - 1] = Tile.water;
                    SpillWater(r, c - 1, depth - 1);
                } else {
                    tiles[r][c - 1] = Tile.sand;
                }
            }

            if (r - 1 > 0) {
                if (Math.random() > spillChance) {
                    tiles[r - 1][c] = Tile.water;
                    SpillWater(r - 1, c, depth - 1);
                } else {
                    tiles[r - 1][c] = Tile.sand;
                }
            }

            if (r + 1 > tiles.length) {
                if (Math.random() > spillChance) {
                    tiles[r + 1][c] = Tile.water;
                    SpillWater(r + 1, c, depth - 1);
                } else {
                    tiles[r + 1][c] = Tile.sand;
                }
            }

            if (c + 1 < tiles[0].length) {
                if (Math.random() > spillChance) {
                    tiles[r][c + 1] = Tile.water;
                    SpillWater(r, c + 1, depth - 1);
                } else {
                    tiles[r][c + 1] = Tile.sand;
                }
            }
        }

    }

    private static void surroundSand(int r,int c) {

        if(r-1 >= 0 && c-1 >= 0) {
            if(tiles[r-1][c-1] != Tile.water) {
                tiles[r-1][c-1] = Tile.sand;
            }
        }

        if(r+1 < tiles.length && c-1 >= 0) {
            if(tiles[r+1][c-1] != Tile.water) {
                tiles[r+1][c-1] = Tile.sand;
            }
        }

        if(r+1 < tiles.length && c+1 < tiles[0].length) {
            if(tiles[r+1][c+1] != Tile.water) {
                tiles[r+1][c+1] = Tile.sand;
            }
        }

        if(r-1 >= 0 && c+1 < tiles[0].length) {
            if(tiles[r-1][c+1] != Tile.water) {
                tiles[r-1][c+1] = Tile.sand;
            }
        }

        if(c-1 >= 0) {
            if(tiles[r][c-1] != Tile.water) {
                tiles[r][c-1] = Tile.sand;
            }
        }

        if(r-1 >= 0) {
            if(tiles[r-1][c] != Tile.water) {
                tiles[r-1][c] = Tile.sand;
            }
        }

        if(r + 1 < tiles.length) {
            if(tiles[r+1][c] != Tile.water) {
                tiles[r+1][c] = Tile.sand;
            }
        }

        if(c+1 < tiles[0].length) {
            if(tiles[r][c+1] != Tile.water) {
                tiles[r][c+1] = Tile.sand;
            }
        }
    }

    public static void checkEdges(int r, int c) {
        boolean Left;
        boolean Right;
        boolean Up;
        boolean Down;

        if(tiles[r][c] == Tile.grass) {
            if(tiles[r-1][c] != Tile.grass){
               Up = true;
            }
            else {
                Up = false;
            }

            if(tiles[r+1][c] != Tile.grass) {
                Down = true;
            }
            else {
                Down = false;
            }

            if(tiles[r][c-1] != Tile.grass) {
                Left = true;
            }
            else {
                Left = false;
            }

            if(tiles[r][c+1] != Tile.grass) {
                Right = true;
            }
            else {
                Right = false;
            }

            if(Up && Down && Right && Left) {
                tiles[r][c] = Tile.ALL_EDGES_GRASS;
            }

            else if(Up && Down && Left) {
                tiles[r][c] = Tile.BOTTOM_LEFT_RIGHT_GRASS;
            }

            else if(Up && Down && Right) {
                tiles[r][c] = Tile.RIGHT_TOP_BOTTOM_GRASS;
            }

            else if(Up && Left && Right) {
                tiles[r][c] = Tile.TOP_LEFT_RIGHT_GRASS;
            }

            else if (Left && Right && Down) {
                tiles[r][c] = Tile.BOTTOM_LEFT_RIGHT_GRASS;
            }

            else if(Up && Left) {
                tiles[r][c] = Tile.TOP_LEFT_CORNER_GRASS;
            }

            else if(Up & Right) {
                tiles[r][c] = Tile.TOP_RIGHT_CORNER_GRASS;
            }

            else if(Down && Left) {
                tiles[r][c] = Tile.BOTTOM_LEFT_GRASS;
            }

            else if(Down && Right) {
                tiles[r][c] = Tile.BOTTOM_RIGHT_GRASS;
            }

            else if(Left && Right) {
                tiles[r][c] = Tile.MIDDLE_LEFT_RIGHT_GRASS;
            }

            else if(Up && Down) {
                tiles[r][c] = Tile.BOTTOM_MIDDLE_GRASS;
            }

            else if(Up) {
                tiles[r][c] = Tile.TOP_MIDDLE_GRASS;
            }

            else if(Left) {
                tiles[r][c] = Tile.MIDDLE_LEFT_GRASS;
            }

            else if(Right) {
                tiles[r][c] = Tile.MIDDLE_RIGHT_GRASS;
            }

            else if(Down) {
                tiles[r][c] = Tile.BOTTOM_MIDDLE_GRASS;
            }
        }
        else if(tiles[r][c] == Tile.sand) {
            if(tiles[r-1][c] != Tile.sand){
                Up = true;
            }
            else {
                Up = false;
            }

            if(tiles[r+1][c] != Tile.sand) {
                Down = true;
            }
            else {
                Down = false;
            }

            if(tiles[r][c-1] != Tile.sand) {
                Left = true;
            }
            else {
                Left = false;
            }

            if(tiles[r][c+1] != Tile.sand) {
                Right = true;
            }
            else {
                Right = false;
            }

            if(Up && Down && Right && Left) {
                tiles[r][c] = Tile.ALL_EDGES_SAND;
            }

            else if(Up && Down && Left) {
                tiles[r][c] = Tile.BOTTOM_LEFT_RIGHT_SAND;
            }

            else if(Up && Down && Right) {
                tiles[r][c] = Tile.RIGHT_TOP_BOTTOM_SAND;
            }

            else if(Up && Left && Right) {
                tiles[r][c] = Tile.TOP_LEFT_RIGHT_SAND;
            }

            else if (Left && Right && Down) {
                tiles[r][c] = Tile.BOTTOM_LEFT_RIGHT_SAND;
            }

            else if(Up && Left) {
                tiles[r][c] = Tile.TOP_LEFT_CORNER_SAND;
            }

            else if(Up & Right) {
                tiles[r][c] = Tile.TOP_RIGHT_CORNER_SAND;
            }

            else if(Down && Left) {
                tiles[r][c] = Tile.BOTTOM_LEFT_SAND;
            }

            else if(Down && Right) {
                tiles[r][c] = Tile.BOTTOM_RIGHT_SAND;
            }

            else if(Left && Right) {
                tiles[r][c] = Tile.MIDDLE_LEFT_RIGHT_SAND;
            }

            else if(Up && Down) {
                tiles[r][c] = Tile.BOTTOM_MIDDLE_SAND;
            }

            else if(Up) {
                tiles[r][c] = Tile.TOP_MIDDLE_SAND;
            }

            else if(Left) {
                tiles[r][c] = Tile.MIDDLE_LEFT_SAND;
            }

            else if(Right) {
                tiles[r][c] = Tile.MIDDLE_RIGHT_SAND;
            }

            else if(Down) {
                tiles[r][c] = Tile.BOTTOM_MIDDLE_SAND;
            }
        }
    }

    public static void spawnTrees(int r, int c) {
        for (r = 0; r < tiles.length; r++) {
            for (c = 0; c < tiles[0].length; c++) {
                if(tiles[r][c] == Tile.grass) {
                    if(Math.random() < 0.1) {
                        sprites[r][c] = new MultiAnimatedSprite(10,10,10,10,Images.TREE);
                    }
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
