package byog.Core.Truc;


import byog.Core.Operator.G;

public abstract class TrucsControllBase implements TrucController{
    protected int trucCount;
    protected TrucsAction input;
    public TrucsControllBase(int trucCount) {
        this.trucCount = trucCount;
        if (this.trucCount < 0) trucCount = 0;
        if (this.trucCount > 4) trucCount = 4;
        input = new TrucsAction(trucCount);
    }

    @Override
    public int getTrucCount() {
        return trucCount;
    }

    @Override
    public void reset(G game) {
        input.reset();
    }
    @Override
    public void nextLevel(G game) {

    }
    @Override
    public abstract void tick(G game, long timeDue);
    @Override
    public TrucsAction getActions() {
        return input;
    }


}
