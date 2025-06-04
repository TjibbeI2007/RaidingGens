package Tjibbe_2007.com.raidingGens.Logic.Map.Config;

import org.bukkit.Material;

public class MapConfig {
    private static final int MAP_SIZE = 150;
    private static final int MAP_HIGHT = 100;
    private static final int CUBE_SIZE = 10;

    private static final Material CUBE_OUTLINE_MATERIAL = Material.BLACK_CONCRETE;
    private static final Material CUBE_FILL_MATERIAL = Material.GRAY_CONCRETE;

    public static int getMapSize() { return MAP_SIZE; }
    public static int getMapHeight() { return MAP_HIGHT; }
    public static int getCubeSize() { return CUBE_SIZE; }
    public static Material getCubeOutlineMaterial() { return CUBE_OUTLINE_MATERIAL; }
    public static Material getCubeFillMaterial() { return CUBE_FILL_MATERIAL; }
}
