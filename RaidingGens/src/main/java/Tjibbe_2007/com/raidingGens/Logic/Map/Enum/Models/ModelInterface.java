package Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models;

public interface ModelInterface {
    String getType();
    int getPriority();
    int placeChance();
    boolean canPlace(int x, int y, int z, int[][] map);
}
