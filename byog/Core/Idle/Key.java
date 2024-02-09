package byog.Core.Idle;

import byog.Core.Game;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Key {
   /* public static void generateKeys(TETile[][] world, Room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            int numbers = Game.rand.nextInt(rooms[i].roomWidth * rooms[i].roomHeight / 2);
            for (int j = 0; j < numbers; j++) {
                Position p = PathGenerator.randomPoint(rooms[i]);
                world[p.x][p.y] = Tileset.KEY;
            }
        }
    }

    public static void countKeys(TETile[][] world) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == Tileset.KEY) {
                    Game.keysTotal++;
                }
            }
        }
    }*/
}
