package byog.Core.Idle;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.io.Serializable;

public class WorldCreator implements Serializable {
    /*public static TETile[][] world;

    public static TETile[][] createEmptyWorld() {
        world = new TETile[Data.WIDTH][Data.HEIGHT];
        for (int x = 0; x < Data.WIDTH; x++) {
            for (int y = 0; y < Data.HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }
    public static void display(TETile[][] world) {
        TERenderer ter = new TERenderer();
        ter.initialize(Data.WIDTH, Data.HEIGHT);
        ter.renderFrame(world);
    }

    public static void renderWorld(TETile[][] f) {
        TERenderer ter = new TERenderer();
        ter.initialize(Data.WIDTH, Data.HEIGHT);
        ter.renderFrame(f);
        StdDraw.show();
    }*/
}
