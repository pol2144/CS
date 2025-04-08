package Map;

import Client.Settings;

public class Map {
    private static Tile[][] tiles = new Tile[Settings.MAP_HEIGHT][Settings.MAP_WIDTH];
    //Sprite[][] sprites;
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

    public static String convertToString(Tile[][] map) {
        String result = "";
        for(Tile[] T : tiles)for(Tile t: T){
            result += t.toString() + ";";
        }
        return result;
    }

}
