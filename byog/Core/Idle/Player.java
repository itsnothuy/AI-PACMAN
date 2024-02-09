package byog.Core.Idle;

import byog.Core.Operator.Direction;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Player implements Serializable {
    /*public Position position;
    public TETile playerTile;
    public String playerName;
    public int keys = 0;

    public Player(){
    }

    public Player(Position p, TETile playerTile, String playerName) {
        this.position = p;
        this.playerTile = playerTile;
        this.playerName = playerName;
    }

    public Player(Position p, TETile playerTile, String playerName, int keys) {
        this.position = p;
        this.playerTile = playerTile;
        this.playerName = playerName;
        this.keys = keys;
    }

    public void placePlayerToWorld(TETile[][] world) {
        world[this.position.x][this.position.y] = this.playerTile;
    }
    public static boolean isClear(TETile[][] world, Position p) {
        return !(world[p.x][p.y].description().equals("wall"));
    }

    public static void moveTop(TETile[][] world, Player player) {
        int x = player.position.x;
        int y = player.position.y;
        Position checkWallPosition = new Position(x, y + 1);
        if (isClear(world, checkWallPosition)) {
            System.out.println("tile is" + world[x][y + 1].description());
            if (world[x][y + 1].description().equals("key")) {
                player.keys++;
            }
            world[x][y] = Tileset.FLOOR;
            world[x][y + 1] = player.playerTile;
            world[x][y].draw(x,y);
            y += 1;
            world[x][y].draw(x,y);
            player.position.x = x;
            player.position.y = y;
            System.out.println("move top");
        }
    }
    public static void moveLeft(TETile[][] world, Player player) {
        int x = player.position.x;
        int y = player.position.y;
        Position checkWallPosition = new Position(x - 1, y);
        if (isClear(world, checkWallPosition)) {
            System.out.println("tile is" + world[x - 1][y].description());
            if (world[x - 1][y].description().equals("key")) {
                player.keys++;
            }
            world[x][y] = Tileset.FLOOR;
            world[x - 1][y] = player.playerTile;
            world[x][y].draw(x, y);
            x -= 1;
            world[x][y].draw(x, y);
            player.position.x = x;
            player.position.y = y;
            System.out.println("move left");
        }
    }
    public static void moveRight(TETile[][] world, Player player) {
        int x = player.position.x;
        int y = player.position.y;
        Position checkWallPosition = new Position(x + 1, y);
        if (isClear(world, checkWallPosition)) {
            System.out.println("tile is" + world[x + 1][y].description());
            if (world[x + 1][y].description().equals("key")) {
                player.keys++;
            }
            world[x][y] = Tileset.FLOOR;
            world[x + 1][y] = player.playerTile;
            world[x][y].draw(x, y);
            x += 1;
            world[x][y].draw(x, y);
            player.position.x = x;
            player.position.y = y;
            System.out.println("move right");
        }
    }

    public static void moveDown(TETile[][] world, Player player) {
        int x = player.position.x;
        int y = player.position.y;
        Position checkWallPosition = new Position(x, y - 1);
        if (isClear(world, checkWallPosition)) {
            System.out.println("tile is" + world[x + 1][y].description());
            if (world[x][y - 1].description().equals("key")) {
                player.keys++;
            }
            world[x][y] = Tileset.FLOOR;
            world[x][y - 1] = player.playerTile;
            world[x][y].draw(x, y);
            y -= 1;
            world[x][y].draw(x, y);
            player.position.x = x;
            player.position.y = y;
            System.out.println("move down");
        }
    }
    public void move(TETile[][] world, Direction direction) {
        if (direction.equals(Direction.TOP)) {
            moveTop(world, this);
            System.out.println("PlayerObj " + this.keys);
        }
        if (direction.equals(Direction.DOWN)) {
            moveDown(world, this);
            System.out.println("PlayerObj " + this.keys);
        }
        if (direction.equals(Direction.LEFT)) {
            moveLeft(world, this);
            System.out.println("PlayerObj " + this.keys);
        }
        if (direction.equals(Direction.RIGHT)) {
            moveRight(world, this);
            System.out.println("playerObj " + this.keys);
        }
    }

    public static void moveNoDrawTop(TETile[][] world, Player player) {
        int x = player.position.x;
        int y = player.position.y;
        Position checkWallPosition = new Position(x, y + 1);
        if (isClear(world, checkWallPosition)) {
            if (world[x][y + 1].description().equals("key")) {
                player.keys++;
            }

            world[x][y] = Tileset.FLOOR;
            world[x][y + 1] = player.playerTile;
            y += 1;
            player.position.x = x;
            player.position.y = y;
        }
    }

    public static void moveNoDrawDown(TETile[][] world, Player player) {
        int x = player.position.x;
        int y = player.position.y;
        Position checkWallPosition = new Position(x, y - 1);
        if (isClear(world, checkWallPosition)) {
            if (world[x][y - 1].description().equals("key")) {
                player.keys++;
            }

            world[x][y] = Tileset.FLOOR;
            world[x][y - 1] = player.playerTile;
            y -= 1;
            player.position.x = x;
            player.position.y = y;
        }
    }

    public static void moveNoDrawRight(TETile[][] world, Player player) {
        int x = player.position.x;
        int y = player.position.y;
        Position checkWallPosition = new Position(x + 1, y);
        if (isClear(world, checkWallPosition)) {
            if (world[x + 1][y].description().equals("key")) {
                player.keys++;
            }

            world[x][y] = Tileset.FLOOR;
            world[x + 1][y] = player.playerTile;
            x += 1;
            player.position.x = x;
            player.position.y = y;
        }
    }

    public static void moveNoDrawLeft(TETile[][] world, Player player) {
        int x = player.position.x;
        int y = player.position.y;
        Position checkWallPosition = new Position(x - 1, y);
        if (isClear(world, checkWallPosition)) {
            if (world[x - 1][y].description().equals("key")) {
                player.keys++;
            }

            world[x][y] = Tileset.FLOOR;
            world[x - 1][y] = player.playerTile;
            x -= 1;
            player.position.x = x;
            player.position.y = y;
        }
    }

    public void moveNoDraw(TETile[][] world, Direction direction) {
        if (direction.equals(Direction.TOP)) {
            moveNoDrawTop(world, this);
        }
        if (direction.equals(Direction.DOWN)) {
            moveNoDrawDown(world, this);
        }
        if (direction.equals(Direction.LEFT)) {
            moveNoDrawLeft(world, this);
        }
        if (direction.equals(Direction.RIGHT)) {
            moveNoDrawRight(world, this);
        }
    }*/
}
