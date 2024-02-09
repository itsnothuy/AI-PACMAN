/*
package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import static byog.Core.Idle.MapGenerator.*;
public class Wall {
    public static void generateWall (TETile[][] world) {
        int count = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    count = 1;
                }
                if (count == 1 && world[i][j].equals(Tileset.NOTHING)) {
                    count = 0;
                    world[i][j] = Tileset.WALL;
                }
            }
        }
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    count = 1;
                }
                if (count == 1 && world[i][j].equals(Tileset.NOTHING)) {
                    count = 0;
                    world[i][j] = Tileset.WALL;
                }
            }
        }
        for (int j = HEIGHT - 1; j >= 0; j--) {
            for (int i = WIDTH - 1; i >= 0; i--) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    count = 1;
                }
                if (count == 1 && world[i][j].equals(Tileset.NOTHING)) {
                    count = 0;
                    world[i][j] = Tileset.WALL;
                }
            }
        }
        for (int i = WIDTH - 1; i >= 0; i -= 1) {
            for (int j = HEIGHT - 1; j >= 0; j -= 1) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    count = 1;
                }
                if (count == 1 && world[i][j].equals(Tileset.NOTHING)) {
                    count = 0;
                    world[i][j] = Tileset.WALL;
                }
            }
        }
    }
}
*/
package byog.Core.Idle;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Wall {
/*

    public static void fillWalls(TETile[][] world) {
        for (int i = 1; i < world.length - 1; i++) {
            for (int j = 1; j < world[i].length - 1; j++) {
                if (world[i][j] == Tileset.FLOOR) {
                    if (world[i][j + 1] == Tileset.NOTHING) {
                        world[i][j + 1] = Tileset.WALL;
                    }
                    if (world[i][j - 1] == Tileset.NOTHING) {
                        world[i][j - 1] = Tileset.WALL;
                    }
                    if (world[i + 1][j] == Tileset.NOTHING) {
                        world[i + 1][j] = Tileset.WALL;
                    }
                    if (world[i - 1][j] == Tileset.NOTHING) {
                        world[i - 1][j] = Tileset.WALL;
                    }
                }
            }
        }
    }
*/

}
