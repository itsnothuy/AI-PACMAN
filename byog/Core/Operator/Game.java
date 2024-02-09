package byog.Core.Operator;

import byog.Core.PacMan.PacManAction;
import byog.Core.Truc.TrucsAction;
import byog.Core.Operator.PacManSimulator.GameConfig;
import byog.Core.Operator.Direction;

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;



public class Game implements G {
    /* Feel free to change the width and height. */
//    public static final int WIDTH = 80;
//    public static final int HEIGHT = 30;

    /*public static Random rand;
    public static TETile[][] world;
    public static int keysTotal = 0;

    public static Player player1;
    public static Player player2;

    public static void setPlayer1(Player player1) {
        Game.player1.playerTile = player1.playerTile;
        Game.player1.keys = player1.keys;
        Game.player1.position = player1.position;
        Game.player1.playerName = player1.playerName;
    }


    public static void setPlayer2(Player player2) {
        Game.player2 = player2;
    }


    *//**
      // Method used for playing a fresh game. The game should start from the main menu.
     *//*
    public void playWithKeyboard() {
        UI.drawMainMenu();
        char command = waitCommand();
        while (true) {
            if (command == 'n') {
                long seed = UI.askForSeed();
                System.out.println(seed);
                world = playWithInputString(Long.toString(seed));
                WorldCreator.renderWorld(world);
                MapGenerator.mapCountKeys(world);
                play(world);
            } else if (command == 'l') {
                OutputObject o = loadWorld();
                world = o.world;
                setPlayer1(o.player1);
                setPlayer2(o.player2);
                System.out.println("the keys is " + o.player1.keys);
                System.out.println("the keys is " + o.player2.keys);

                WorldCreator.renderWorld(world);
                MapGenerator.mapCountKeys(world);
                play(world);
            }
            command = waitCommand();
        }
    }

    *//**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     *//*
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        TETile[][] world = WorldCreator.createEmptyWorld();
        Game.rand = Data.setGameSeed(input);
        int numRooms = rand.nextInt(15) + 5;
        Room[] rooms = new Room[numRooms];
        MapGenerator.mapGenerateRooms(world, numRooms, rooms);
        MapGenerator.mapGenerateKeys(world,rooms);
        player1 = new Player(PathGenerator.randomPoint(rooms[0]), Tileset.PLAYER, "Player1");
        player2 = new Player(PathGenerator.randomPoint(rooms[rooms.length - 1]), Tileset.PLAYER2, "Player2");
        player1.placePlayerToWorld(world);
        player2.placePlayerToWorld(world);
        PathGenerator.drawAllPaths(world, numRooms, rooms);
        MapGenerator.mapFillWalls(world);
        System.out.println(TETile.toString(world));
        String moveStr = precessString(input);
        moveNoDraw(world, moveStr);
        return world;

    }
    private String precessString(String input) {
        String moveStr = input;
        if ((moveStr.charAt(0)) == 'n') {
            moveStr = moveStr.substring(1);
        } else if (moveStr.charAt(0) == 'l') {
            OutputObject w = loadWorld();
            Game.world = w.world;
            Game.player1 = w.player1;
            Game.player2 = w.player2;
            moveStr = moveStr.substring(1);
        } else if (moveStr.charAt(0) == 'q') {
            saveGame(new OutputObject(Game.player1, Game.player2, world));
        }
        moveStr = moveStr.replaceAll("\\d", "");
        return moveStr;
    }
    public static char waitCommand() {
        while (!StdDraw.hasNextKeyTyped()) {
            StdDraw.pause(10);

        }
        return StdDraw.nextKeyTyped();
    }

    private void moveNoDraw(TETile[][] world, String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'w') {
                player1.moveNoDraw(world, Direction.TOP);
            } else if (c == 'a') {
                player1.moveNoDraw(world, Direction.LEFT);
            } else if (c == 's') {
                player1.moveNoDraw(world, Direction.DOWN);
            } else if (c == 'd') {
                player1.moveNoDraw(world, Direction.RIGHT);
            } else if (c == 'i') {
                player2.moveNoDraw(world, Direction.TOP);
            } else if (c == 'j') {
                player2.moveNoDraw(world, Direction.LEFT);
            } else if (c == 'k') {
                player2.moveNoDraw(world, Direction.DOWN);
            } else if (c == 'l') {
                player2.moveNoDraw(world, Direction.RIGHT);
            } else if (c == 'q') {
                saveGame(new OutputObject(player1, player2, world));
            }
        }
    }

    public static char waitForControlKey() {
        while (!StdDraw.hasNextKeyTyped()) {
            StdDraw.pause(10);
            mouseTile();
        }
        return StdDraw.nextKeyTyped();
    }
    public static void mouseTile() {
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
        int w = (int) Math.floorDiv((long) x, 1);
        int h = (int) Math.floorDiv((long) y, 1);

        if (h >= Data.HEIGHT) {
            h = Data.HEIGHT - 1;
        }
        if (w >= Data.WIDTH) {
            w = Data.WIDTH - 1;
        }
        TETile tile = world[w][h];
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledRectangle(Data.WIDTH / 2, Data.HEIGHT - 1, Data.WIDTH / 2, 1);
        StdDraw.setPenColor(Color.PINK);
        StdDraw.textLeft(1, Data.HEIGHT - 1, tile.description());
        StdDraw.textRight(Data.WIDTH - 1, Data.HEIGHT - 1,
                "keysTotal: " + keysTotal + "    Player 1: " + Game.player1.keys
                        + "    Player 2: " + Game.player2.keys);
        StdDraw.show(10);
    }

    public static boolean isWin() {

        return player1.keys + player2.keys == keysTotal;
    }

    private void saveGame(OutputObject outputObject) {
        File f = new File("./world.txt");
        try {
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(outputObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OutputObject loadWorld() {
        File f = new File("./world.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (OutputObject) os.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        throw new RuntimeException("no file exists");
    }
    private void endGame() {
        StdDraw.clear(StdDraw.PRINCETON_ORANGE);
        StdDraw.setPenColor(Color.PINK);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 60));
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT / 2 + 10, "Final score:");
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT / 2 + 5, "Player 1 - " + Game.player1.keys);
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT / 2, "Player 2 - " + Game.player2.keys);
        if (player1.keys > player2.keys) {
            StdDraw.text(Data.WIDTH / 2, Data.HEIGHT / 2 - 5, "Player 1 wins!");
        } else if (player2.keys > player1.keys) {
            StdDraw.text(Data.WIDTH / 2, Data.HEIGHT / 2 - 5, "Player 1 wins!");
        } else {
            StdDraw.text(Data.WIDTH / 2, Data.HEIGHT / 2 - 5, "It's a tie!");
        }
        StdDraw.show();
        while (true) {
            StdDraw.pause(100);
            if (StdDraw.hasNextKeyTyped()) {
                char k = StdDraw.nextKeyTyped();
                if ((k == 'n') || (k == 'q')) {
                    break;
                }
            }
        }
        StdDraw.clear(StdDraw.PRINCETON_ORANGE);
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT / 2 + 5, "Play again? ");
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT / 2, "(Q)uit or (N)ew game?");
        StdDraw.show();
        char k = waitCommand();
        while (true) {
            if (k == 'q') {
                System.exit(0);
            } else if (k == 'n') {
                playWithKeyboard();
            }
        }
    }

    private void play(TETile[][] world) {
        while (true) {
            char k = waitForControlKey();
            if (k == 'q') {
                saveGame(new OutputObject(Game.player1, Game.player2, world));
                System.out.println("player1 has " + player1.keys);
                System.out.println("player2 has " + player2.keys);

                playWithKeyboard();
            }
            if (k == 'w') {
                player1.move(world, Direction.TOP);
            }
            if (k == 's') {
                player1.move(world, Direction.DOWN);
            }
            if (k == 'a') {
                player1.move(world, Direction.LEFT);
            }
            if (k == 'd') {
                player1.move(world, Direction.RIGHT);
            }
            if (k == 'i') {
                player2.move(world, Direction.TOP);
            }
            if (k == 'j') {
                player2.move(world, Direction.LEFT);
            }
            if (k == 'k') {
                player2.move(world, Direction.DOWN);
            }
            if (k == 'l') {
                player2.move(world, Direction.RIGHT);
            }
            if (isWin()) {
                endGame();
            }
        }
    }
*/
    // ORIGIN

    /*
     * Simple implementation of Ms Pac-Man. The class Game contains all code relating to the
     * game; the class GameView displays the game. Controllers must implement PacManController
     * and GhostController respectively. The game may be executed using Exec.
     */

    public static Random rnd = new Random();
    protected GameConfig config;
    protected int remainingLevels;

    // static stuff (mazes are immutable - hence static)
    protected static Maze[] mazes = new Maze[NUM_MAZES];
    // Variables (game state);
    protected BitSet pills, powerPills;
    //level-specific
    protected int curMaze, totLevel, levelTime, totalTime, score, trucEatMultiplier;
    protected boolean gameOver;
    //pac-man-specific
    protected int curPacManLoc, lastPacManDir, livesRemaining;
    protected boolean extraLife;
    //ghosts-specific
    protected int[] curTrucLocs, lastTrucDirs, edibleTimes, lairTimes;

    /////////////////////////////////////////////////////////////////////////////
    /////////////////  Constructors and Initialisers   //////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    // Constructor
    protected Game() {}

    // loads the mazes and store them
    protected void init() {
        for (int i = 0; i < mazes.length; i++) {
            if (mazes[i] == null) mazes[i] = new Maze(i);
        }
    }

    // Creates an exact copy of the game
    public G copy() {
        Game copy = new Game();
        copy.pills = (BitSet) pills.clone();
        copy.powerPills = (BitSet)powerPills.clone();
        copy.curMaze = curMaze;
        copy.totLevel = totLevel;
        copy.levelTime = levelTime;
        copy.totalTime = totalTime;
        copy.score = score;
        copy.trucEatMultiplier = trucEatMultiplier;
        copy.gameOver = gameOver;
        copy.curPacManLoc = curPacManLoc;
        copy.lastPacManDir = lastPacManDir;
        copy.livesRemaining = livesRemaining;
        copy.extraLife = extraLife;
        copy.curTrucLocs = Arrays.copyOf(curTrucLocs, curTrucLocs.length);
        copy.lastTrucDirs = Arrays.copyOf(lastTrucDirs,lastTrucDirs.length);
        copy.edibleTimes = Arrays.copyOf(edibleTimes, edibleTimes.length);
        copy.lairTimes = Arrays.copyOf(lairTimes, lairTimes.length);

        return copy;
    }

    // If pac-man has been eaten or a new level has been reached

    protected void reset(boolean newLevel) {
        if (newLevel) {
            if (remainingLevels > 0) {
                --remainingLevels;
                if (remainingLevels <= 0) {
                    gameOver = true;
                    return;
                }
            }

            curMaze = (curMaze + 1) % Game.NUM_MAZES;
            totLevel++;
            levelTime = 0;
            pills = new BitSet(getNumberPills());
            pills.set(0, getNumberPills());
            powerPills = new BitSet(getNumberPowerPills());
            powerPills.set(0, getNumberPowerPills());

            if (!config.powerPillsEnabled) {
                powerPills.clear();
            }
            if (config.totalPills < 1) {
                int number = (int) Math.ceil(pills.length() * (1 - (config.totalPills > 0 ? config.totalPills : 0)));
                decimatePills(number);
            }
        }

        curPacManLoc = getInitialPacPosition();
        lastPacManDir = Game.INITIAL_PAC_DIR;

        Arrays.fill(curTrucLocs, mazes[curMaze].lairPosition);
        lastTrucDirs = Arrays.copyOf(Game.INITIAL_TRUC_DIRS, Game.INITIAL_TRUC_DIRS.length);

        Arrays.fill(edibleTimes, 0);
        trucEatMultiplier = 1;

        for (int i = 0; i < lairTimes.length; i++) {
            lairTimes[i] = (int)(Game.LAIR_TIMES[i] * Math.pow(LAIR_REDUCTION, totLevel));
        }
    }

    // Remove 'number' of pills from the maze
    protected void decimatePills(int number) {
        if (number == pills.length()) {
            pills.clear();
        } else {
            List<Integer> pillNodeIndices = new ArrayList<>();
            Node[] graph = mazes[curMaze].graph;
            for (int i = 0; i < graph.length; ++i) {
                if (graph[i].pillIndex >= 0) {
                    pillNodeIndices.add(i);
                }
            }
            while (number > 0) {
                int startNodePillIndex = pillNodeIndices.get(Game.rnd.nextInt(pillNodeIndices.size()));
                List<Integer> nodeIndices = new ArrayList<>();
                Set<Integer> closedIndices = new HashSet<>();
                nodeIndices.add(startNodePillIndex);
                while (number > 0 && nodeIndices.size() > 0) {
                    // CLEAR PILL
                    int nodeIndex = nodeIndices.remove(0);
                    int pillIndex = getPillIndex(nodeIndex);
                    pillNodeIndices.remove((Object) nodeIndex);
                    closedIndices.add(nodeIndex);

                    if (pillIndex >= 0 && pills.get(pillIndex)) {
                        pills.clear(pillIndex);
                        --number;
                    }

                    //CHECK NEIGHBOURS
                    int[] neighbours = new int[4];
                    int numNeighbours = 0;
                    for (Direction dir : Direction.arrows()) {
                        int nextNode = getNeighbour(nodeIndex, dir.index);
                        if (nextNode >= 0) {
                            neighbours[dir.index] = nextNode;
                            ++numNeighbours;
                        } else {
                            neighbours[dir.index] = -1;
                        }
                    }
                    if (numNeighbours == 2) {
                        // CORRIDOR
                        for (int neighbour : neighbours) {
                            if (neighbour >= 0 && !closedIndices.contains(neighbour)) {
                                nodeIndices.add(neighbour);
                            }
                        }
                    }

                }

            }

        }
    }

    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////  Game Play   //////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    // Central method that advances the game state

    public int[] advanceGame(PacManAction pacMan, TrucsAction trucs) {
        updatePacMan(pacMan);   //move pac-man
        eatPill();  // eat a pill
        boolean reverse = eatPowerPill(); // eat a power pill
        if (trucs != null) {
            updateTrucs(trucs, reverse); // move ghosts
        }

        //This is primarily done for the replays as reset (as possibly called by feast()) sets the
        // last directions to the initial ones, not the ones taken
        int[] replayStep = {lastPacManDir, lastTrucDirs[0], lastTrucDirs[1], lastTrucDirs[2], lastTrucDirs[3],
                curPacManLoc, curTrucLocs[0], curTrucLocs[1], curTrucLocs[2], curTrucLocs[3]};
        feast();    // ghosts eat pac-man or vice versa

        if (trucs != null) {
            for (int i = 0; i < lairTimes.length && i < trucs.trucCount; i++) {
                if (lairTimes[i] > 0) {
                    lairTimes[i]--;
                    if (lairTimes[i] == 0) {
                        curTrucLocs[i] = mazes[curMaze].initialTrucsPosition;
                    }
                }

            }
        }

        if (!extraLife && score >= EXTRA_LIFE_SCORE) { // award 1 extra life at 10000 points
            extraLife = true;
            livesRemaining++;
        }

        totalTime++;
        levelTime++;
        checkLevelState(); // check if level/game is over

        return replayStep;
    }

    // Central method that advances the game state

    public int[] advanceGameReplay(PacManAction pacMan, TrucsAction trucs, int pacManLocation, int[] trucsLocations) {
        //updatePacMan(pacMan);         // move pac-man
        lastPacManDir = pacMan.get().index;
        curPacManLoc = pacManLocation;

        eatPill(); // eat a pill
        eatPowerPill(); // eat a power pill

        if (trucs != null) {
            //updateTrucs(trucs, reverse);  //move trucs
            for (int i = 0; i < 4; ++i) {
                lastTrucDirs[i] = trucs.Truc(i).get().index;
                curTrucLocs[i] = trucsLocations[i];
            }
        }

        // This is primarily done for the replays as reset (as possibly called by feast()) sets the
        //last directions to the initial ones, not the ones taken
        int[] replayStep = {lastPacManDir, lastTrucDirs[0], lastTrucDirs[1], lastTrucDirs[2], lastTrucDirs[3], curPacManLoc, curTrucLocs[0], curTrucLocs[1], curTrucLocs[2], curTrucLocs[3]};

        feast();   // ghosts eat pac-man or vice versa

        if (trucs != null) {
            for (int i = 0; i < lairTimes.length; i++) {
                if (lairTimes[i] > 0) {
                    lairTimes[i]--;
                    if (lairTimes[i] == 0) curTrucLocs[i] = mazes[curMaze].initialTrucsPosition;
                }
            }
        }

        if (!extraLife && score >= EXTRA_LIFE_SCORE) {
            extraLife = true;
            livesRemaining++;
        }

        totalTime++;
        levelTime++;
        checkLevelState();

        return replayStep;
    }

    // Updates the location of Ms Pac-Man
    protected void updatePacMan(PacManAction pacMan) {
        int direction = checkPacManDir(pacMan.get().index);
        lastPacManDir = direction;
        curPacManLoc = getNeighbour(curPacManLoc, direction);
    }

    // Check the direction supplied by the controller and substitutes for a legal one if necessary
    protected int checkPacManDir(int direction) {
        int[] neighbours = getPacManNeighbours();
        if ((direction > 3 || direction < 0 || neighbours[direction] == -1) &&
                (lastPacManDir > 3 || lastPacManDir < 0 || neighbours[lastPacManDir] == -1))
            return 4;
        if (direction < 0 || direction > 3)
            direction = lastPacManDir;

        if (neighbours[direction] == -1)
            if (neighbours[lastPacManDir] != -1)
                direction = lastPacManDir;
            else {
                int[] options = getPossiblePacManDirs(true);
                direction = options[Game.rnd.nextInt(options.length)];
            }
        return direction;
    }

    // Update the locations of the ghosts
    protected void updateTrucs(TrucsAction trucs, boolean reverse) {
        int[] directions = new int[4];
        for (int i = 0; i < trucs.trucCount; ++i) {
            directions[i] = trucs.actions[i].get().index;
        }

        for (int i = 0; i < trucs.trucCount; i++) {
            if (lairTimes[i] == 0) {
                if (reverse) {
                    lastTrucDirs[i] = getReverse(lastTrucDirs[i]);
                    curTrucLocs[i] = getNeighbour(curTrucLocs[i], lastTrucDirs[i]);
                }
                else if (edibleTimes[i] == 0 || edibleTimes[i] % TRUC_SPEED_REDUCTION != 0) {
                    directions[i] = checkTrucDir(i, directions[i]);
                    lastTrucDirs[i] = directions[i];
                    curTrucLocs[i] = getNeighbour(curTrucLocs[i], directions[i]);
                }
            }
        }
    }

    // Checks the directions supplied by the controller and substitutes for a legal ones if necessary
    protected int checkTrucDir(int whichTruc, int direction) {
        if (direction < 0 || direction > 3)
            direction = lastTrucDirs[whichTruc];

        int[] neighbours = getTrucNeighbours(whichTruc);

        if (neighbours[direction] == -1) {
            if (neighbours[lastTrucDirs[whichTruc]] != -1)
                direction = lastTrucDirs[whichTruc];
            else {
                int[] options = getPossibleTrucDirs(whichTruc);
                direction = options[Game.rnd.nextInt(options.length)];
            }
        }
        return direction;
    }

    // Eats a pill
    protected void eatPill() {
        int pillIndex = getPillIndex(curPacManLoc);

        if (pillIndex >= 0 && pills.get(pillIndex)) {
            score += Game.PILL;
            pills.clear(pillIndex);
        }
    }

    // Eats a power pill - turn ghosts edible (blue)

    protected boolean eatPowerPill() {
        boolean reverse = false;
        int powerPillIndex = getPowerPillIndex(curPacManLoc);

        if (powerPillIndex >= 0 && powerPills.get(powerPillIndex)) {
            score += Game.POWER_PILL;
            trucEatMultiplier = 1;
            powerPills.clear(powerPillIndex);

            // This ensures that only ghosts outside the lair (i.e., inside the maze) turn edible
            int newEdibleTime = (int) (Game.EDIBLE_TIME * (Math.pow(Game.EDIBLE_TIME_REDUCTION, totLevel)));

            for (int i = 0; i < NUM_TRUCS; i++)
                if (lairTimes[i] == 0)
                    edibleTimes[i] = newEdibleTime;
                else
                    edibleTimes[i] = 0;

            // This turns all ghosts edible, independents on whether they are in the lair or not
            //Arrays.fill(edibleTimes, (int) (Game.EDIBLE_TIME * (Math.pow(Game.EDIBLE_TIME_REDUCTION, totLevel))));

            reverse = true;

        } else if (levelTime > 1 && Game.rnd.nextDouble() < Game.TRUC_REVERSAL) // random truc reversal
                reverse = true;

        return reverse;
    }

    // This is where the characters of the game eat one another if possible
    protected void feast() {
        for(int i = 0; i < curTrucLocs.length; i++) {
            int distance = getPathDistance(curPacManLoc, curTrucLocs[i]);

            if (distance <= Game.EAT_DISTANCE && distance != -1) {
                if (edibleTimes[i] > 0) {
                    score += Game.TRUC_EAT_SCORE * trucEatMultiplier;
                    trucEatMultiplier *= 2;
                    edibleTimes[i] = 0;
                    lairTimes[i] = (int) (Game.COMMON_LAIR_TIMES *(Math.pow(Game.LAIR_REDUCTION, totLevel)));
                    curTrucLocs[i] = mazes[curMaze].lairPosition;
                    lastTrucDirs[i] = Game.INITIAL_TRUC_DIRS[i];
                } else {
                    livesRemaining--;

                    if (livesRemaining <= 0) {
                        gameOver = true;
                        return;
                    } else {
                        reset(false);
                    }
                }
            }
        }

        for (int i = 0; i < edibleTimes.length; i++)
            if (edibleTimes[i] > 0)
                edibleTimes[i]--;

    }

    // Checks the state of the level/game and advances to the next level or terminates the game
    protected void checkLevelState() {
        // if all pills have been eaten or the time is up...
        if ((pills.isEmpty() && powerPills.isEmpty()) || levelTime >= LEVEL_LIMIT) {

            //award any remaining pills to Ms Pac-Man
            score += Game.PILL * pills.cardinality() + Game.POWER_PILL * powerPills.cardinality();

            // put a cap on the total number of levels played
            if (totLevel + 1 == Game.MAX_LEVELS) {
                gameOver = true;
                return;
            } else
                reset(true);
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    ///////////////////////////  Getter Methods  ////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    // Return the reverse of the direction supplied
    public int getReverse(int direction) {
        switch (direction){
            case 0: return 2;
            case 1: return 3;
            case 2: return 0;
            case 3: return 1;
        }

        return 4;
    }

    // Whether the game is over or not
    public boolean gameOver() { return gameOver; }

    // Whether the pill specified is still here
    public boolean checkPill(int nodeIndex) { return pills.get(nodeIndex); }

    // Whether the power pill specified is still there
    public boolean checkPowerPill(int nodeIndex) { return powerPills.get(nodeIndex); }

    // Returns the neighbours of the node at which Ms Pac-Man currently resides
    public int[] getPacManNeighbours() {
        return Arrays.copyOf(mazes[curMaze].graph[curPacManLoc].neighbours,
                mazes[curMaze].graph[curPacManLoc].neighbours.length);
    }

    // Returns the neighbours of the node at which the specified ghost currently resides.
    // NOTE: since ghosts are not allowed to reverse, that neighbour is filtered out.
    // Alternatively use: getNeighbour(), given curGhostLoc[-] for all directions

    public int[] getTrucNeighbours(int whichTruc) {
        int[] neighbours = Arrays.copyOf(mazes[curMaze].graph[curTrucLocs[whichTruc]].neighbours,
                mazes[curMaze].graph[curTrucLocs[whichTruc]].neighbours.length);
        neighbours[getReverse(lastTrucDirs[whichTruc])] = -1;

        return neighbours;
    }

    // The current level
    public int getCurLevel() { return totLevel; }

    // The current maze (1-4)
    public int getCurMaze() { return curMaze; }

    // Current node index of Ms Pac-Man
    public int getCurPacManLoc() { return curPacManLoc; }

    // Current node index of Ms Pac-Man

    public int getCurPacManDir() { return lastPacManDir; }

    // Lives remaining for Ms Pac-Man
    public int getLivesRemaining() { return livesRemaining; }

    // Current node at which the specified ghost resides
    public int getCurTrucLoc(int whichTruc) { return curTrucLocs[whichTruc]; }

    // Current direction of the specified ghost
    public int getCurTrucDir(int whichTruc) { return lastTrucDirs[whichTruc]; }

    // Returns the edible time for the specified ghost
    public int getEdibleTime(int whichTruc) { return edibleTimes[whichTruc]; }

    // Simpler check to see if a ghost is edible
    public boolean isEdible(int whichTruc) { return edibleTimes[whichTruc] > 0; }

    // Return the score of the game
    public int getScore() { return score; }

    // Returns the time of the current level (important with respect to LEVEL_LIMIT)
    public int getLevelTime() { return levelTime; }

    // Total time the game has been plaued for (at most LEVEL_LIMIT * MAX_LEVELS)
    public int getTotalTime() { return totalTime; }

    // Total number of pills in the maze
    public int getNumberPills() { return mazes[curMaze].pillIndices.length; }

    // Total number of power pills in the maze
    public int getNumberPowerPills() { return mazes[curMaze].powerPillIndices.length; }

    // Time left that the specified ghost will spend in the lair
    public int getLairTime(int whichTruc) { return lairTimes[whichTruc]; }

    // If in lair (getLairTime(-) > 0) or if not at junction
    public boolean trucRequiresAction(int whichTruc) {
        return (isJunction(curTrucLocs[whichTruc]) && (edibleTimes[whichTruc] == 0 || edibleTimes[whichTruc] % TRUC_SPEED_REDUCTION != 0));
    }

    // Returns name of the maze: A, B, C, D
    public String getName() { return mazes[curMaze].name; }

    // Returns the starting position of Ms PacMan
    public int getInitialPacPosition() { return mazes[curMaze].initialPacPosition; }

    // Returns  the starting position of the ghosts (i.e., first node AFTER leaving the lair)
    public int getInitialTrucsPosition() { return mazes[curMaze].initialTrucsPosition; }

    // Total number of nodes in the graph (i.e.m those with pills, power pills and those that are empty)
    public int getNumberOfNodes() { return mazes[curMaze].graph.length; }

    // Returns the x coordinate of the specified node
    public int getX(int index) { return mazes[curMaze].graph[index].x; }

    // Returns the y coordinate of the specified node
    public int getY(int index) { return mazes[curMaze].graph[index].y; }

    // Returns the pill index of the node. If it is -1, the node has no pill
    // Otherwise, one can use the bitset to check whether the pill has already been eaten
    public int getPillIndex(int nodeIndex) {
        return mazes[curMaze].graph[nodeIndex].pillIndex;
    }

    //Returns the power pill index of the node. If it is -1, the node has no pill. Otherwise one
    //can use the bitset to check whether the pill has already been eaten
    public int getPowerPillIndex(int nodeIndex){
        return mazes[curMaze].graph[nodeIndex].powerPillIndex;
    }

    // Returns the neighbour of node index that corresponds to direction
    // In the case of neutral, the same node index is returned
    public int getNeighbour(int nodeIndex, int direction){
        return (direction < 0 || direction > 3) ? nodeIndex : mazes[curMaze].graph[nodeIndex].neighbours[direction];
    }

    // Returns the indices to all the nodes that have pills
    public int[] getPillIndices() {
        return Arrays.copyOf(mazes[curMaze].pillIndices, mazes[curMaze].pillIndices.length);
    }

    // Returns the indices to all the nodes that have power pills
    public int[] getPowerPillIndices() {
        return Arrays.copyOf(mazes[curMaze].powerPillIndices, mazes[curMaze].powerPillIndices.length);
    }

    // Returns the indices to all the nodes that are junctions
    public int[] getJunctionIndices() {
        return Arrays.copyOf(mazes[curMaze].junctionIndices, mazes[curMaze].junctionIndices.length);
    }

    // Checks of a node is a junction
    public boolean isJunction(int nodeIndex){
        return mazes[curMaze].graph[nodeIndex].numNeighbours > 2;
    }

    // Returns the score awarded for the next ghost to be eaten
    public int getNextEdibleTrucScore() {
        return Game.TRUC_EAT_SCORE * trucEatMultiplier;
    }

    // Returns the number of pills still in the maze
    public int getNumActivePills() {
        return pills.cardinality();
    }

    // Returns the number of power pills still in the maze
    public int getNumActivePowerPills() {
        return powerPills.cardinality();
    }

    // Returns the indices of all active pills in the maze
    public int[] getPillIndicesActive() {
        int[] indices = new int[pills.cardinality()];

        int index = 0;

        for (int i = 0; i < mazes[curMaze].pillIndices.length; i++) {
            if (pills.get(i))
                indices[index++] = mazes[curMaze].pillIndices[i];
        }

        return indices;
    }
    // Returns the indices of all active power pills in the maze
    public int[] getPowerPillIndicesActive() {
        int[] indices = new int[powerPills.cardinality()];

        int index = 0;

        for (int i = 0; i < mazes[curMaze].powerPillIndices.length; i++)
            if (powerPills.get(i))
                indices[index++] = mazes[curMaze].powerPillIndices[i];

        return indices;
    }

    // Returns the number of neighbours of a node: 2, 3, or 4.
    // Exception: lair, which has no neighbours
    public int getNumNeighbours ( int nodeIndex){
        return mazes[curMaze].graph[nodeIndex].numNeighbours;
    }

    // Returns the actual directions Ms Pac-Man can take
    public int[] getPossiblePacManDirs ( boolean includeReverse){
        return getPossibleDirs(curPacManLoc, lastPacManDir, includeReverse);
    }

    // Ret  urns the actual directions the specified ghost can take
    public int[] getPossibleTrucDirs ( int whichTruc){
        return getPossibleDirs(curTrucLocs[whichTruc], lastTrucDirs[whichTruc], false);
    }

    // Computes the directions to be taken given the current location
    public int[] getPossibleDirs(int curLoc, int curDir, boolean includeReverse){
        int numNeighbours = mazes[curMaze].graph[curLoc].numNeighbours;

        if (numNeighbours == 0)
            return new int[0];

        int[] nodes = mazes[curMaze].graph[curLoc].neighbours;
        int[] directions;

        if (includeReverse || (curDir < 0 || curDir > 3))
            directions = new int[numNeighbours];
        else
            directions = new int[numNeighbours - 1];

        int index = 0;

        for (int i = 0; i < nodes.length; i++)
            if (nodes[i] != -1) {
                if (includeReverse || (curDir < 0 || curDir > 3))
                    directions[index++] = i;
                else if (i != getReverse(curDir))
                    directions[index++] = i;
            }
        return directions;
    }

    // Returns the direction Pac-Man should take to approach/retreat a target
    // (to) given some distance measure
    public int getNextPacManDir ( int to, boolean closer, DM measure){
        return getNextDir(mazes[curMaze].graph[curPacManLoc].neighbours, to, closer, measure);
    }

    // Returns the direction the ghost should take to approach/retreat a target
    // (to) given some distance measure. Reversals are filtered
    public int getNextTrucDir ( int whichTruc, int to, boolean closer, G.DM measure){
        return getNextDir(getTrucNeighbours(whichTruc), to, closer, measure);
    }

    // This method returns the direction to take given some options
    // (usually corresponding to the neighbours of the node in question),
    // moving either towards or away (closer in {true, false}) using one of the three distance measures
    private int getNextDir ( int[] from, int to, boolean closer, G.DM measure){
        int dir = -1;

        double min = Integer.MAX_VALUE;
        double max = Integer.MAX_VALUE;

        for (int i = 0; i < from.length; i++) {
            if (from[i] != -1) {
                double dist = 0;

                switch (measure) {
                    case PATH:
                        dist = getPathDistance(from[i], to);
                        break;
                    case EUCLID:
                        dist = getEuclideanDistance(from[i], to);
                        break;
                    case MANHATTAN:
                        dist = getManhattanDistance(from[i], to);
                        break;
                }

                if (closer && dist < min) {
                    min = dist;
                    dir = i;
                }

                if (!closer && dist > max) {
                    max = dist;
                    dir = i;
                }
            }
        }

        return dir;
    }

    // Returns the PATH distance from any node to any other node
    public int getPathDistance (int from, int to){
        if (from == to)
            return 0;
        else if (from < to)
            return mazes[curMaze].distances[((to * (to + 1)) / 2) + from];
        else
            return mazes[curMaze].distances[((from * (from + 1)) / 2) + to];
    }

    // Returns the EUCLEDIAN distance between two nodes in the current maze
    public double getEuclideanDistance (int from, int to){
        return Math.sqrt(Math.pow(mazes[curMaze].graph[from].x - mazes[curMaze].graph[to].x, 2) +
                Math.pow(mazes[curMaze].graph[from].y - mazes[curMaze].graph[to].y, 2));
    }

    // Returns the MANHATTAN distance between two nodes in the current maze
    public int getManhattanDistance (int from, int to){
        return (int) (Math.abs(mazes[curMaze].graph[from].x - mazes[curMaze].graph[to].x) +
                Math.abs(mazes[curMaze].graph[from].y - mazes[curMaze].graph[to].y));
    }

    // Returns the path of adjacent nodes from one node to another, including these nodes
    // E.g., path from a to c might be [a,f,r,t,c]

    public int[] getPath (int from, int to){
        if (from < 0 || to < 0)
            return new int[0];
        int currentNode = from;
        ArrayList<Integer> path = new ArrayList<>();
        int lastDir;

        while (currentNode != to) {
            path.add(currentNode);
            int[] neighbours = mazes[curMaze].graph[currentNode].neighbours;
            lastDir = getNextDir(neighbours, to, true, DM.PATH);
            currentNode = neighbours[lastDir];
        }

        int[] arrayPath = new int[path.size()];

        for (int i = 0; i < arrayPath.length; i++)
            arrayPath[i] = path.get(i);

        return arrayPath;
    }

    // Similar to getPath(-) but takes into consideration the fact that ghosts may not reverse. Hence the path to be
    // taken may be significantly longer than the shortest available path
    public int[] getTrucPath(int whichTruc, int to) {
        if (mazes[curMaze].graph[curTrucLocs[whichTruc]].numNeighbours == 0)
            return new int[0];

        int currentNode = curTrucLocs[whichTruc];
        ArrayList<Integer> path = new ArrayList<>();
        int lastDir = lastTrucDirs[whichTruc];

        while (currentNode != to) {
            path.add(currentNode);
            int[] neighbours = getTrucNeighbours(currentNode, lastDir);
            lastDir = getNextDir(neighbours, to, true, Game.DM.PATH);
            currentNode = neighbours[lastDir];
        }

        int[] arrayPath = new int[path.size()];

        for (int i = 0; i < arrayPath.length; i++)
            arrayPath[i] = path.get(i);

        return arrayPath;
    }

    // Returns the node from 'targets' that is closest/farthest
    // from the node 'from' given the distance measure specified
    public int getTarget(int from, int[] targets, boolean nearest, G.DM measure) {
        int target = -1;

        double min = Integer.MAX_VALUE;
        double max = Integer.MAX_VALUE;

        for (int i = 0; i < targets.length; i++) {
            double dist = 0;
            switch (measure) {
                case PATH: dist = getPathDistance(targets[i], from); break;
                case EUCLID: dist = getEuclideanDistance(targets[i], from); break;
                case MANHATTAN: dist = getManhattanDistance(targets[i], from); break;
            }

            if (nearest && dist < min) {
                min = dist;
                target = targets[i];
            }

            if (!nearest && dist > max) {
                max = dist;
                target = targets[i];
            }
        }

        return target;
    }

    // Returns the target closes from the position of the ghost,
    // considering that reversals are not allowed
    public int getTrucTarget (int whichTruc, int[] targets, boolean nearest){
        int target = -1;

        double min = Integer.MAX_VALUE;
        double max = -Integer.MAX_VALUE;

        for (int i = 0; i < targets.length; i++) {
            double dist = getTrucPathDistance(whichTruc, targets[i]);

            if (nearest && dist < min) {
                min = dist;
                target = targets[i];
            }

            if (!nearest && dist > max) {
                max = dist;
                target = targets[i];
            }
        }

        return target;
    }

    // Returns the path distance for a particular ghost: takes into account the face that ghosts
    // may not reverse
    public int getTrucPathDistance ( int whichTruc, int to){
        return getTrucPath(whichTruc, to).length;
    }

    // Returns the neighbours of a node with the one corresponding to the reverse of direction
    // being deleted (i.e., = -1)
    private int[] getTrucNeighbours ( int node, int lastDirection){
        int[] neighbours = Arrays.copyOf(mazes[curMaze].graph[node].neighbours,
                mazes[curMaze].graph[node].neighbours.length);
        neighbours[getReverse(lastDirection)] = -1;

        return neighbours;
    }

    /*
     * Stores the actual mazes, each of which is simply a connected graph. The differences between the mazes are the connectivity
     * and the x,y coordinates (used for drawing or to compute the Euclidean distance. There are 3 built-in distance functions in
     * total: Euclidean, Manhattan and Dijkstra's shortest path distance. The latter is pre-computed and loaded, the others are
     * computed on the fly whenever getNextDir(-) is called.
     */
    protected final class Maze {
        private String[] nodeNames = {"a", "b", "c", "d"};
        private String[] distNames = {"da", "db", "dc", "dd"};

        protected int[] distances, pillIndices, powerPillIndices, junctionIndices; //Information for the controllers
        protected Node[] graph; //The actual maze, stored as a graph (set of nodes)
        protected int initialPacPosition, lairPosition, initialTrucsPosition, width, height; //Maze-specific information
        protected String name; //Name of the Maze


        /*
         * Each maze is stored as a (connected) graph: all nodes have neighbours, stored in an array of length 4. The
         * index of the array associates the direction the neighbour is located at: '[up,right,down,left]'.
         * For instance, if node '9' has neighbours '[-1,12,-1,6]', you can reach node '12' by going right, and node
         * 6 by going left. The directions returned by the controllers should thus be in {0,1,2,3} and can be used
         * directly to determine the next node to go to.
         */
        protected Maze(int index) {
            loadNodes(nodeNames[index]);
            loadDistances(distNames[index]);
        }

        // Loads all the nodes from files and initialises all maze-specific information
        private void loadNodes(String fileName) {
            try {
                //APPLET
                //BufferedReader br=new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/data/"+fileName)));
                //APPLICATION

                // ORIG:
                //BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(pathMazes+System.getProperty("file.separator")+fileName)));
                BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("Resources/data/" + fileName)));
                String input = br.readLine();

                //preamble
                String[] pr = input.split("\t");
                this.name = pr[0];
                this.initialPacPosition = Integer.parseInt(pr[1]);
                this.lairPosition = Integer.parseInt(pr[2]);
                this.initialTrucsPosition = Integer.parseInt(pr[3]);
                this.graph = new Node[Integer.parseInt(pr[4])];
                this.pillIndices = new int[Integer.parseInt(pr[5])];
                this.powerPillIndices = new int[Integer.parseInt(pr[6])];
                this.junctionIndices = new int[Integer.parseInt(pr[7])];
                this.width = Integer.parseInt(pr[8]);
                this.height = Integer.parseInt(pr[9]);

                input = br.readLine();

                int nodeIndex = 0;
                int pillIndex = 0;
                int powerPillIndex = 0;
                int junctionIndex = 0;

                while (input != null) {
                    String[] nd = input.split("\t");
                    Node node = new Node(nd[0], nd[1], nd[2], nd[7], nd[8], new String[]{nd[3], nd[4], nd[5], nd[6]});

                    graph[nodeIndex++] = node;

                    if (node.pillIndex >= 0)
                        pillIndices[pillIndex++] = node.nodeIndex;
                    else if (node.powerPillIndex >= 0)
                        powerPillIndices[powerPillIndex++] = node.nodeIndex;

                    if (node.numNeighbours > 2)
                        junctionIndices[junctionIndex++] = node.nodeIndex;

                    input = br.readLine();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        /*
         * Loads the shortest path distances which have been pre-computed. The data contains the shortest distance from
         * any node in the maze to any other node. Since the graph is symmetric, the symmetries have been removed to preserve
         * memory and all distances are stored in a 1D array; they are looked-up using getDistance(-).
         */
        private void loadDistances(String fileName) {
            this.distances = new int[((graph.length * (graph.length - 1)) / 2) + graph.length];

            try {
                //APPLET
//	        	BufferedReader br=new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/data/"+fileName)));
                //APPLICATION

                //ORIG:
                //BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(pathMazes+System.getProperty("file.separator")+fileName)));
                BufferedReader br = new BufferedReader(new InputStreamReader((this.getClass().getResourceAsStream("Resources/data/" + fileName))));

                String input = br.readLine();

                int index = 0;

                while (input != null) {
                    distances[index++] = Integer.parseInt(input);
                    input = br.readLine();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }


    /*
     * Stores all information relating to a node in the graph, including all the indices required
     * to check and update the current state of the game.
     */

    protected final class Node {
        protected int x, y, nodeIndex, pillIndex, powerPillIndex, numNeighbours;
        protected int[] neighbours;

        protected Node(String nodeIndex, String x, String y, String pillIndex, String powerPillIndex, String[] neighbours) {
            this.nodeIndex = Integer.parseInt(nodeIndex);
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
            this.pillIndex = Integer.parseInt(pillIndex);
            this.powerPillIndex = Integer.parseInt(powerPillIndex);

            this.neighbours = new int[neighbours.length];

            for (int i = 0; i < neighbours.length; i++) {
                this.neighbours[i] = Integer.parseInt(neighbours[i]);

                if(this.neighbours[i] != -1)
                    numNeighbours++;
            }
        }
    }


}
