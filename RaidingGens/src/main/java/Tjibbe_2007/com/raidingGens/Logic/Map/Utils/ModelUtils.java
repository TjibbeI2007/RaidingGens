package Tjibbe_2007.com.raidingGens.Logic.Map.Utils;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class ModelUtils {
    private ModelUtils() {}

    public static HashMap<Location, Material> getLocations(Location start, Location end, ModelMode mode) {
        return getLocationsCustom(start, end, MapConfig.CUBE_FILL_MATERIAL, mode.checkX, mode.checkY, mode.checkZ);
    }
    public static HashMap<Location, Material> getLocations(Location start, Location end, Material material, ModelMode mode) {
        return getLocationsCustom(start, end, material, mode.checkX, mode.checkY, mode.checkZ);
    }

    public static HashMap<Location, Material> getLocationsCustom(Location startLocation, Location endLocation, Material material, boolean checkX, boolean checkY, boolean checkZ) {
        HashMap<Location, Material> fillLocations = new HashMap<>();

        int minX = Math.min(startLocation.getBlockX(), endLocation.getBlockX());
        int minY = Math.min(startLocation.getBlockY(), endLocation.getBlockY());
        int minZ = Math.min(startLocation.getBlockZ(), endLocation.getBlockZ());

        int maxX = Math.max(startLocation.getBlockX(), endLocation.getBlockX());
        int maxY = Math.max(startLocation.getBlockY(), endLocation.getBlockY());
        int maxZ = Math.max(startLocation.getBlockZ(), endLocation.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    boolean isOutline = (checkX && (x == minX || x == maxX)) || (checkY && (y == minY || y == maxY)) || (checkZ && (z == minZ || z == maxZ));
                    Material blockMaterial = isOutline ? MapConfig.CUBE_OUTLINE_MATERIAL : material;

                    fillLocations.put(new Location(startLocation.getWorld(), x, y, z), blockMaterial);
                }
            }
        }

        return fillLocations;
    }

    public static HashMap<Location, Material> getDiagonalLine(Location start, Location end) {
        return getDiagonalLine(start, end, MapConfig.CUBE_OUTLINE_MATERIAL);
    }

    public static HashMap<Location, Material> getDiagonalLine(Location start, Location end, Material material) {
        HashMap<Location, Material> line = new HashMap<>();

        int dx = end.getBlockX() - start.getBlockX();
        int dy = end.getBlockY() - start.getBlockY();
        int dz = end.getBlockZ() - start.getBlockZ();

        int steps = Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz)));
        int stepX = Integer.signum(dx);
        int stepY = Integer.signum(dy);
        int stepZ = Integer.signum(dz);

        for (int i = 0; i <= steps; i++) {
            line.put(new Location(start.getWorld(),
                    start.getBlockX() + i * stepX,
                    start.getBlockY() + i * stepY,
                    start.getBlockZ() + i * stepZ),
                    material
            );
        }

        return line;
    }
}
