package byog.Core.Truc;

import byog.Core.Operator.G;

public class TrucNormalState implements OriTruc{
    int X = 0;
    int Y = 1;

    int[] BlinkyCorner = {0, 0};
    int[] PinkyCorner = {0, 0};
    int[] ClydeCorner = {0, 0};
    int[] InkyCorner = {0, 0};

    int BlinkyCornerNode = 0;
    int PinkyCornerNode = 76;
    int ClydeCornerNode = 1195;
    int InkyCornerNode = 1290;

    @Override
    public int[] execute(int trucType, G game, long timeDue) {
        int[] target = {0, 0};
        if (trucType == BLINKY) {
            if (game.getNumActivePills() < 20) {
                target[X] = game.getX(game.getCurPacManLoc());
                target[Y] = game.getY(game.getCurPacManLoc());
            } else {
                target[X] = game.getX(BlinkyCornerNode);
                target[Y] = game.getY(BlinkyCornerNode);
            }
        }
        if (trucType == PINKY) {
            target[X] = game.getX(PinkyCornerNode);
            target[Y] = game.getY(PinkyCornerNode);
        }
        if (trucType == CLYDE) {
            target[X] = game.getX(PinkyCornerNode);
            target[Y] = game.getY(PinkyCornerNode);
        }
        if (trucType == INKY) {
            target[X] = game.getX(InkyCornerNode);
            target[Y] = game.getY(InkyCornerNode);
        }
        return target;
    }
}
