package Tjibbe_2007.com.raidingGens.Logic.Map.Enum;

public enum ModelType {
    FLOOR_MODEL("FloorModel") {
        @Override
        public int getPriority() { return 3; }
        public boolean canPlace(int x, int y, int z, int[][] map) { return isBottom(y); }
    },
    CUBE_MODEL("CubeModel"),
    SUPPORT_MODEL("SupportModel") {
        @Override
        public boolean canPlace(int x, int y, int z, int[][] map) { return isAbove(x, y, z, map); }
        @Override
        public int getPriority() { return 1; }
    },
    PYRAMID_MODEL("PyramidModel") {
        @Override
        public boolean canPlace(int x, int y, int z, int[][] map) { return isTop(x, y, z, map); }
        @Override
        public int getPriority() { return 2; }
    };

    public final String type;
    public static final int MAX_PRIORITY = 3;

    ModelType(String type) {
        this.type = type;
    }
    public int getPriority() { return 0; }
    public boolean canPlace(int x, int y, int z, int[][] map) { return true; }
    protected static boolean isTop(int x, int y, int z, int[][] map) { return (map[x][z] == y); }
    protected static boolean isAbove(int x, int y, int z, int[][] map) { return (map[x][z] > y); }
    protected static boolean isBottom(int y) { return (0 == y); }
}
