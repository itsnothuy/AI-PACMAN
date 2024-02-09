package byog.Core.Truc;

import byog.Core.Operator.EntityAction;

public final class TrucAction extends EntityAction {
    public TrucAction clone() {
        TrucAction result = new TrucAction();
        result.direction = direction;
        return result;
    }
}
