package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtilitiesModel {
    public static List<Location> getFillLocations(Location startLocation, Location endLocation) {
        List<Location> fillLocations = new ArrayList<>();

        int minX = Math.min(startLocation.getBlockX(), endLocation.getBlockX());
        int minY = Math.min(startLocation.getBlockY(), endLocation.getBlockY());
        int minZ = Math.min(startLocation.getBlockZ(), endLocation.getBlockZ());

        int maxX = Math.max(startLocation.getBlockX(), endLocation.getBlockX());
        int maxY = Math.max(startLocation.getBlockY(), endLocation.getBlockY());
        int maxZ = Math.max(startLocation.getBlockZ(), endLocation.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    fillLocations.add(new Location(startLocation.getWorld(), x, y, z));
                }
            }
        }

        return fillLocations;
    }

    public static List<Location> getCubeOutlineLocations(Map<String, Location> corners) {
        List<Location> outlineLocations = new ArrayList<>();
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ"), corners.get("minX_maxY_minZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_maxZ"), corners.get("minX_maxY_maxZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_minY_minZ"), corners.get("maxX_maxY_minZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_minY_maxZ"), corners.get("maxX_maxY_maxZ")));

        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ"), corners.get("maxX_minY_minZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_minY_maxZ"), corners.get("minX_minY_maxZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_minY_minZ"), corners.get("maxX_minY_maxZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ"), corners.get("minX_minY_maxZ")));

        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_maxY_maxZ"), corners.get("minX_maxY_maxZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_maxY_minZ"), corners.get("minX_maxY_minZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_maxY_minZ"), corners.get("minX_maxY_maxZ")));
        outlineLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_maxY_minZ"), corners.get("maxX_maxY_maxZ")));
        return outlineLocations;
    }

    public static List<Location> getDiagonalLocations(Location startLocation, Location endLocation) {
        List<Location> diagonalLocations = new ArrayList<>();

        double distance = startLocation.distance(endLocation);
        for (double i = 0; i <= distance; i += 1) {
            diagonalLocations.add(startLocation.clone().add(i, i, 0));
            diagonalLocations.add(startLocation.clone().add(0, i, i));
            diagonalLocations.add(startLocation.clone().add(i, i, distance));
            diagonalLocations.add(startLocation.clone().add(distance, i, i));
            diagonalLocations.add(startLocation.clone().add(i, -i + distance, 0));
            diagonalLocations.add(startLocation.clone().add(0, -i + distance, i));
            diagonalLocations.add(startLocation.clone().add(i, -i + distance, distance));
            diagonalLocations.add(startLocation.clone().add(distance, -i + distance, i));
            diagonalLocations.add(startLocation.clone().add(i, 0, i));
            diagonalLocations.add(startLocation.clone().add(-i + distance, 0, i));
            diagonalLocations.add(startLocation.clone().add(i, distance, i));
            diagonalLocations.add(startLocation.clone().add(-i + distance, distance, i));
        }

        return diagonalLocations;
    }

    public static List<Location> getPyramidLocations(Map<String, Location> corners) {
        List<Location> pyramidLocations = new ArrayList<>();
        int size = (int) corners.get("minX_minY_minZ").distance(corners.get("maxX_minY_minZ"));

        pyramidLocations.addAll(getFillLocations(corners.get("minX_minY_minZ").clone().add(1,1,1), corners.get("maxX_minY_minZ").clone().add(-1,1,1)));
        pyramidLocations.addAll(getFillLocations(corners.get("minX_minY_minZ").clone().add(1,1,size-1), corners.get("maxX_minY_minZ").clone().add(-1,1,size-1)));
        pyramidLocations.addAll(getFillLocations(corners.get("minX_minY_minZ").clone().add(2,2,2), corners.get("maxX_minY_minZ").clone().add(-2,2,2)));
        pyramidLocations.addAll(getFillLocations(corners.get("minX_minY_minZ").clone().add(2,2,size-2), corners.get("maxX_minY_minZ").clone().add(-2,2,size-2)));
        pyramidLocations.addAll(getFillLocations(corners.get("minX_minY_minZ").clone().add(3,3,3), corners.get("maxX_minY_minZ").clone().add(-3,3,3)));
        pyramidLocations.addAll(getFillLocations(corners.get("minX_minY_minZ").clone().add(3,3,size-3), corners.get("maxX_minY_minZ").clone().add(-3,3,size-3)));

        return pyramidLocations;
    }
}
