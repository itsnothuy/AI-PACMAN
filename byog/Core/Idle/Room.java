package byog.Core.Idle;/*
package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import static byog.Core.Idle.MapGenerator.*;

public class Room {
    public Position p;
    public Position q;

    public Room(Position p, Position q) {
        Position.regularization(p,q);
        this.p = p;
        this.q = q;
    }

    public Room() {
        Position p = Position.randomPosition();
        Position q = Position.randomPosition();
        while (p.equals(q)) {
            q = Position.randomPosition();
        }
        Position.regularization(p,q);
        this.p = p;
        this.q = q;
    }

    public Room generate() {
        Position p = Position.randomPositionInsideRoom(this);
        if (this.isHallWay()) {
            Position q = Position.randomPosition();
            while (q.isInside(this)) {
                q = Position.randomPosition();
            }
            return new Room(p, q);
        }
        return generateHallway(p);
    }

    public Room generateHallway(Position p) {
        Position q = p;
        while (q.isInside(this)) {
            int x = RANDOM.nextInt(2);
            int diff = 2 * RANDOM.nextInt(2) - 1;
            if (x == 0) {
                int xEnd = RANDOM.nextInt(WIDTH);
                q = new Position(xEnd, p.x + diff);
            } else {
                int yEnd = RANDOM.nextInt(HEIGHT);
                q = new Position(p.x + diff, yEnd);
            }
        }
        return new Room(p,q);
    }

    public boolean isValid() {
        return !(!p.isInside(BACKGROUND) || !q.isInside(BACKGROUND) || p.equals(q));
    }

    public void draw(TETile[][] world) {
        for (int i = p.x; i < q.x; i++) {
            for (int j = p.y; i < q.y; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }

    public boolean isHallWay() {
        return (Math.abs(p.x - q.x) == 1) || (Math.abs(p.y - q.y) == 1);
    }

    public int roomWidth() {
        return q.x - p.x;
    }

    public int roomHeight() {
        return q.y - p.y;
    }
}
*/

import java.io.Serializable;
/*
package byog.Core.Idle;

import byog.Core.Game;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
*/


public class Room implements Serializable {}
    /*
    public int roomWidth;
    public int roomHeight;
    public int roomX;
    public int roomY;


    public Room(int roomWidth, int roomHeight, int roomX, int roomY) {
        this.roomWidth = roomWidth;
        this.roomHeight = roomHeight;
        this.roomX = roomX;
        this.roomY = roomY;
    }

    public static void createRoom(Room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            int roomWidth = Game.rand.nextInt(Math.min(Data.WIDTH, Data.HEIGHT) / 5) + 2;
            if (roomWidth < 3) {
                roomWidth += 4;
            }
            int roomHeight = Game.rand.nextInt(Math.min(Data.WIDTH, Data.HEIGHT) / 5) + 2;
            if (roomHeight < 3) {
                roomHeight += 3;
            }
            int roomX = Game.rand.nextInt(Data.WIDTH - roomWidth - 2) + 1;
            int roomY = Game.rand.nextInt(Data.HEIGHT - roomHeight - 5) + 1;
            rooms[i] = new Room(roomWidth, roomHeight, roomX, roomY);
        }
    }

    private static void drawRoom(TETile[][] world, Room room) {
        for (int i = 0; i < room.roomWidth; i++) {
            for (int j = 0; j < room.roomHeight; j++) {
                if (world[i][j] != Tileset.FLOOR) {
                    world[room.roomX][room.roomY + j] = Tileset.WALL;
                }
                if (world[room.roomX + room.roomWidth - 1][room.roomY + j] != Tileset.FLOOR) {
                    world[room.roomX + room.roomWidth - 1][room.roomY + j] = Tileset.WALL;
                }
            }
            if (world[room.roomX + i][room.roomY] != Tileset.FLOOR) {
                world[room.roomX + i][room.roomY] = Tileset.WALL;
            }
            if (world[room.roomX + i][room.roomY + room.roomHeight - 1] != Tileset.FLOOR) {
                world[room.roomX + i][room.roomY + room.roomHeight - 1] = Tileset.WALL;
            }

        }
    }

    private static void fillRooms(TETile[][] world, Room room) {
        for (int i = 1; i < room.roomWidth; i++) {
            for (int j = 1; j < room.roomHeight - 1; j++) {
                world[room.roomX + i][room.roomY + j] = Tileset.FLOOR;
            }
        }
    }

    public static void fillAll(TETile[][] world, int numRooms, Room[] rooms) {
        for (int count = 0; count < numRooms; count++) {
            Room.fillRooms(world, rooms[count]);
        }
        for (int count = 0; count < numRooms; count++) {
            Room.fillRooms(world, rooms[count]);
        }
    }

    public static void drawRooms(TETile[][] world, int numRooms, Room[] rooms) {
        for (int count = 0; count < numRooms; count++) {
            Room.drawRoom(world, rooms[count]);
        }
    }

    public static void generateRooms(TETile[][] world, int numRooms, Room[] rooms) {
        createRoom(rooms);
        drawRooms(world, numRooms, rooms);
        fillAll(world, numRooms, rooms);

    }

}*/
