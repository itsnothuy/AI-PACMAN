package byog.Core.Truc;

import java.util.HashMap;
import java.util.Map;

public enum TrucType {
    BLINKY(0), PINKY(1), CLYDE(2), INKY(3);

    public final int index;

    private static Map<Integer, TrucType> types = null;

    private TrucType(int index) {
        this.index = index;
    }

    public static TrucType forIndex(int index) {
        if (types == null) {
            types = new HashMap<>();
            for (TrucType type : values()) {
                types.put(type.index, type);
            }
        }
        return types.get(index);
    }
}
