package Tjibbe_2007.com.raidingGens.Logic.Map.Utils;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class ModelUtils {
    private ModelUtils() {}

    public static HashMap<Location, Material> getLocations(Location start, Location end, ModelMode mode) {
        return getLocationsCustom(start, end, MapConfig.getCubeFillMaterial(), mode.checkY, mode.checkX, mode.checkZ);
    }
    public static HashMap<Location, Material> getLocations(Location start, Location end, Material material, ModelMode mode) {
        return getLocationsCustom(start, end, material, mode.checkY, mode.checkX, mode.checkZ);
    }

    public static HashMap<Location, Material> getLocationsCustom(Location startLocation, Location endLocation, Material material, boolean checkY, boolean checkX, boolean checkZ) {
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
                    Material blockMaterial = isOutline ? MapConfig.getCubeOutlineMaterial() : material;

                    fillLocations.put(new Location(startLocation.getWorld(), x, y, z), blockMaterial);
                }
            }
        }

        return fillLocations;
    }

    public static HashMap<Location, Material> getPyramidLocations(Map<String, Location> corners) {
        HashMap<Location, Material> pyramidLocations = new HashMap<>();
        double distance = (corners.get("minX_minY_minZ").distance(corners.get("minX_minY_maxZ"))/2 - 1);

        for (int i = 1; i < distance; i++) {
            pyramidLocations.putAll(getLocations(corners.get("maxX_minY_maxZ").clone().add(-i,i,-i), corners.get("minX_minY_maxZ").clone().add(i,i,-i), Material.GLASS, ModelMode.X));
            pyramidLocations.putAll(getLocations(corners.get("minX_minY_minZ").clone().add(i,i,i), corners.get("maxX_minY_minZ").clone().add(-i,i,i), Material.GLASS, ModelMode.X));
            pyramidLocations.putAll(getLocations(corners.get("maxX_minY_minZ").clone().add(-i,i,i), corners.get("maxX_minY_maxZ").clone().add(-i,i,-i), Material.GLASS, ModelMode.Z));
            pyramidLocations.putAll(getLocations(corners.get("minX_minY_minZ").clone().add(i,i,i), corners.get("minX_minY_maxZ").clone().add(i,i,-i), Material.GLASS, ModelMode.Z));
        }

        return pyramidLocations;
    }
}
