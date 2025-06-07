package Tjibbe_2007.com.raidingGens.Logic.Map.Enum;

import java.util.*;

public enum ModelType {
    FLOOR_MODEL("FloorModel") {
        @Override
        public boolean canPlace(int x, int y, int z, int[][] map) { return isBottom(y); }
        @Override
        public int getPriority() { return 3; }
        @Override
        public int placeChance() { return 0; }
    },
    CUBE_MODEL("CubeModel") {
        @Override
        public boolean canPlace(int x, int y, int z, int[][] map) { return true; }
        @Override
        public int getPriority() { return 0; }
        @Override
        public int placeChance() { return 100; }
    },
    SUPPORT_MODEL("SupportModel") {
        @Override
        public boolean canPlace(int x, int y, int z, int[][] map) { return isAbove(x, y, z, map); }
        @Override
        public int getPriority() { return 1; }
        @Override
        public int placeChance() { return 25; }
    },
    PYRAMID_MODEL("PyramidModel") {
        @Override
        public boolean canPlace(int x, int y, int z, int[][] map) { return isTop(x, y, z, map); }
        @Override
        public int getPriority() { return 2; }
        @Override
        public int placeChance() { return 25; }
    },
    TRIANGLE_MODEL("TriangleModel") {
        @Override
        public boolean canPlace(int x, int y, int z, int[][] map) { return true; }
        @Override
        public int getPriority() { return 4; }
        @Override
        public int placeChance() { return 25; }
    };

    public final String type;

    ModelType(String type) {
        this.type = type;
    }

    abstract public int getPriority();
    abstract public int placeChance();
    abstract public boolean canPlace(int x, int y, int z, int[][] map);

    protected boolean isTop(int x, int y, int z, int[][] map) { return (map[x][z] == y+1); }
    protected boolean isBottom(int y) { return (0 == y); }
    protected boolean isAbove(int x, int y, int z, int[][] map) { return (map[x][z] > y); }
    protected boolean isBelow(int x, int y, int z, int[][] map) { return (map[x][z] < y); }

    public static int getHighestPriority() {
        return Arrays.stream(ModelType.values())
                .mapToInt(ModelType::getPriority)
                .max()
                .orElseThrow(() -> new NoSuchElementException("No ModelType values found"));
    }
    public static ModelType[] getModelTypes() {
        return Arrays.stream(ModelType.values())
                .sorted(Comparator.comparingInt(ModelType::getPriority).reversed())
                .toArray(ModelType[]::new);
    }
}
