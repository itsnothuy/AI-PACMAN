package byog.Core.Truc;

public class TrucsAction {
    private static TrucAction NONE;
    public TrucAction[] actions = new TrucAction[4];
    public final int trucCount;
    public boolean pauseSimulation;
    public boolean nextFrame;

    public TrucsAction(int trucCount) {
        this.trucCount = trucCount;
        for (int i = 0; i < actions.length; i++) {
            actions[i] = new TrucAction();
        }
    }

    public TrucAction Truc(int index) {
        if (index < 0 || index > actions.length) return NONE;
        return actions[index];
    }
    public void set(int[] directions) {
        for (int i = 0; i < directions.length && i < actions.length; ++i) {
            actions[i].set(directions[i]);
        }
    }

    public TrucAction blinky() {
        return actions[TrucType.BLINKY.index];
    }
    public TrucAction pinky() {
        return actions[TrucType.PINKY.index];
    }
    public TrucAction clyde() {
        return actions[TrucType.CLYDE.index];
    }
    public TrucAction inky() {
        return actions[TrucType.INKY.index];
    }
    public void pause() {
        pauseSimulation = true;
    }
    public void resume() {
        pauseSimulation = false;
    }
    public void togglePause() {
        pauseSimulation = !pauseSimulation;
    }

    public void reset() {
        for (int i = 0; i < actions.length; ++i) {
            actions[i].reset();
        }
        pauseSimulation = false;
        nextFrame = false;
    }

    public TrucsAction clone() {
        TrucsAction result = new TrucsAction(trucCount);

        for (int i = 0; i < actions.length; i++) {
            result.actions[i] = actions[i].clone();
        }
        result.pauseSimulation = pauseSimulation;
        result.nextFrame = nextFrame;

        return result;
    }

}
