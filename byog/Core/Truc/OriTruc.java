package byog.Core.Truc;

import byog.Core.Operator.G;

public interface OriTruc {
    public final int BLINKY = 0;
    public final int PINKY = 1;
    public final int CLYDE = 2;
    public final int INKY = 3;
    int[] execute(int trucType, G game, long timeDue);
}
