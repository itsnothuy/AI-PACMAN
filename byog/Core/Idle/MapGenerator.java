/*
package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.TERenderer;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {
    static final int WIDTH = 72;
    static final int HEIGHT = 30;
    static final long SEED = 123456;
    static Random RANDOM = new Random(SEED);

    static final Position ORIGIN = new Position (0,0);
    static final Position  DIAGNAL = new Position (WIDTH, HEIGHT);
    static final Room BACKGROUND = new Room(ORIGIN, DIAGNAL);

    public static void randomGenerator(TETile[][] world, int num) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        ArrayList<Room> rooms = new ArrayList<>();
        Room first = new Room();
        rooms.add(first);
        first.draw(world);

        while (num > 0) {
            int index = RANDOM.nextInt(rooms.size());
            Room curRoom = rooms.get(index);
            Room newRoom = curRoom.generate();
            if (newRoom.isValid()) {
                num--;
                rooms.add(newRoom);
                curRoom.draw(world);
            }
        }

        Wall.generateWall(world);
    }

    public static void main (String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        randomGenerator(world, 4);

        ter.renderFrame(world);
    }
}
*/
package byog.Core.Idle;

import byog.TileEngine.TETile;

public class MapGenerator {
    /*public static void mapGenerateRooms(TETile[][] world, int numRooms, Room[] rooms) {
        Room.generateRooms(world, numRooms, rooms);
    }
    public static void mapFillWalls(TETile[][] world) {
        Wall.fillWalls(world);
    }
    public static void mapGenerateKeys(TETile[][] world, Room[] rooms) {
        Key.generateKeys(world, rooms);
    }
    public static void mapCountKeys(TETile[][] world) {
        Key.countKeys(world);
    }
*/

}

