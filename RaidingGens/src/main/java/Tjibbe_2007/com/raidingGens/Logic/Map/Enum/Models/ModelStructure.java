package Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models;

public enum ModelStructure implements ModelInterface{
    FLOOR_MODEL("FloorModel",0,0) {
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return isBottom(y); }
    },
    CUBE_MODEL("CubeModel",3,100) {
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return true; }

    },
    SUPPORT_MODEL("SupportModel",2,25) {
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return isAbove(x, y, z, map); }
    },
    PYRAMID_MODEL("PyramidModel",1,25) {
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return isTop(x, y, z, map); }
    },
    TRIANGLE_MODEL("TriangleModel",4,0) {
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return true; }
    };

    private final String type;
    private final int priority;
    private final int placeChance;
    ModelStructure(String type, int priority, int placeChance) {
        this.type = type;
        this.priority = priority;
        this.placeChance = placeChance;
    }

    @Override public String getType() { return type; }
    @Override public int getPriority() { return priority; }
    @Override public boolean canPlace(int x, int y, int z, int[][] map) { return false; }
    @Override public int placeChance() { return placeChance; }

    protected boolean isTop(int x, int y, int z, int[][] map) { return (map[x][z] == y+1); }
    protected boolean isBottom(int y) { return (0 == y); }
    protected boolean isAbove(int x, int y, int z, int[][] map) { return (map[x][z] > y); }
    protected boolean isBelow(int x, int y, int z, int[][] map) { return (map[x][z] < y); }
}
