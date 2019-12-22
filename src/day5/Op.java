package day5;

public enum Op {

    ADD(3),MUL(3),IN(1),OUT(1),IF_T(2),IF_F(2),LT(3),EQ(3),END(0);

    private int paramCount;

    Op(int paramCount) {
        this.paramCount = paramCount;
    }

    public static Op getByOpcode(int opCode) {
        switch (opCode) {
            case 1:
                return ADD;
            case 2:
                return MUL;
            case 3:
                return IN;
            case 4:
                return OUT;
            case 5:
                return IF_T;
            case 6:
                return IF_F;
            case 7:
                return LT;
            case 8:
                return EQ;
            case 99:
                return END;
            default:
                return null;
        }
    }

    public int getParamCount() {
        return  this.paramCount;
    }

}
