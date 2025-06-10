package Tjibbe_2007.com.raidingGens.Logic.Map.Enum;

public enum ModelMode {
    ALL(true, true, true),
    NONE(false, false, false),
    YZ(false, true, true),
    XY(true, true, false),
    XZ(true, false, true),
    Z(false, false, true),
    Y(false, true, false),
    X(true, false, false);

    public final boolean checkX, checkY, checkZ;

    ModelMode(boolean checkX, boolean checkY, boolean checkZ) {
        this.checkX = checkX;
        this.checkY = checkY;
        this.checkZ = checkZ;
    }
}
