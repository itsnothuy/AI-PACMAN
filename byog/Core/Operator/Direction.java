package byog.Core.Operator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Direction implements Serializable {
    NONE(-1, 0, 0), TOP(G.TOP, 0,-1), LEFT(G.LEFT, -1, 0), DOWN(G.DOWN, 0 , 1), RIGHT(G.RIGHT, 1, 0);

    private static Direction[] arrows = new Direction[] {TOP, RIGHT, DOWN, LEFT};

    private static List<Direction> arrowsList = null;
    private static Map<Integer, Direction> indices = null;
    public final int index;
    public final int dX;
    public final int dY;

    Direction(int directionIndex, int dX, int dY) {
        this.index = directionIndex;
        this.dX = dX;
        this.dY = dY;
    }

    public Direction opposite() {
        switch(this) {
            case NONE: return NONE;
            case DOWN: return TOP;
            case LEFT: return RIGHT;
            case TOP: return DOWN;
        }
        return null;
    }

    public static Direction forIndex(int directionIndex) {
        if (indices == null) {
            indices = new HashMap<>();
            for (Direction dir : values()) {
                indices.put(dir.index, dir);
            }
        }
        Direction dir = indices.get(directionIndex);
        if (dir == null) {
            return NONE;
        }
        return dir;
    }

    /**
     * UP, RIGHT, DOWN, LEFT
     * @return
     */
    public static Direction[] arrows() { return arrows; }

    /**
     * UP, RIGHT, DOWN, LEFT
     * @return
     */
    public static List<Direction> arrowsList() {
        if (arrowsList == null) {
            arrowsList = new ArrayList<>(4);
            for (Direction d : arrows) {
                arrowsList.add(d);
            }
        }
        return arrowsList;
    }

}
