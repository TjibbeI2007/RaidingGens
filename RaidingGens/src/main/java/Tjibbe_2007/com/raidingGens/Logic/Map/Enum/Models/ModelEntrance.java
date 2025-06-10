package Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models;

public enum ModelEntrance implements ModelInterface {
    NORTH_ENTRANCE("North", 4, 25) {
        @Override public ModelEntrance getOpposite() { return SOUTH_ENTRANCE; }
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return true; }
    },
    EAST_ENTRANCE("East", 4, 25) {
        @Override public ModelEntrance getOpposite() { return WEST_ENTRANCE; }
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return true; }
    },
    SOUTH_ENTRANCE("South", 4, 25) {
        @Override public ModelEntrance getOpposite() { return NORTH_ENTRANCE; }
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return true; }
    },
    WEST_ENTRANCE("West", 4, 25) {
        @Override public ModelEntrance getOpposite() { return EAST_ENTRANCE; }
        @Override public boolean canPlace(int x, int y, int z, int[][] map) { return true; }
    };
    private final String type;
    private final int priority;
    private final int placeChance;

    ModelEntrance(String name, int priority, int placeChance) {
        this.type = name;
        this.priority = priority;
        this.placeChance = placeChance;
    }

    public abstract ModelEntrance getOpposite();
    @Override public String getType() { return type; }
    @Override public int getPriority() { return priority; }
    @Override public boolean canPlace(int x, int y, int z, int[][] map) { return false; }
    @Override public int placeChance() { return placeChance; }
}
