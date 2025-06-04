package Tjibbe_2007.com.raidingGens.Logic.Map.Enum;

public enum ModelType {
    FLOOR_MODEL("FloorModel"),
    CUBE_MODEL("CubeModel"),
    SUPPORT_MODEL("SupportModel"),
    PYRAMID_MODEL("PyramidModel");

    private final String type;
    ModelType(String type) {
        this.type = type;
    }

    public String getType() { return type;}
}
