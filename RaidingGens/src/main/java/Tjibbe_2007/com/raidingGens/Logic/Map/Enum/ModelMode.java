package Tjibbe_2007.com.raidingGens.Logic.Map.Enum;

public enum ModelMode {
    ALL(true, true, true),
    VERTICAL(false, true, true),
    Z(false, false, true),
    X(false, true, false),
    Y(true, false, false);

    public final boolean checkY, checkX, checkZ;

    ModelMode(boolean checkY, boolean checkX, boolean checkZ) {
        this.checkY = checkY;
        this.checkX = checkX;
        this.checkZ = checkZ;
    }
}
