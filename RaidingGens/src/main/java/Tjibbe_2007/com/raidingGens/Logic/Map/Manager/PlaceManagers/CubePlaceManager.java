package Tjibbe_2007.com.raidingGens.Logic.Map.Manager.PlaceManagers;

import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CubePlaceManager {
    Location location;

    public CubePlaceManager(Location location) {
        this.location = location;
    }

    private List<Location> getLocations(Location startLocation, Location endLocation) {
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

    public List<Location> getOutlineLocations(MapModel model) {
        Map<String, Location> corners = model.getCorners();
        List<Location> fillLocations = new ArrayList<>();

        fillLocations.addAll(getLocations(corners.get("minX_minY_minZ").clone().add(1, 1, 0), corners.get("maxX_maxY_minZ").clone().add(-1, -1, 0)));
        fillLocations.addAll(getLocations(corners.get("minX_minY_minZ").clone().add(0, 1, 1), corners.get("minX_maxY_maxZ").clone().add(0, -1, -1)));
        fillLocations.addAll(getLocations(corners.get("maxX_minY_maxZ").clone().add(-1, 1, 0), corners.get("minX_maxY_maxZ").clone().add(1, -1, 0)));
        fillLocations.addAll(getLocations(corners.get("maxX_minY_maxZ").clone().add(0, 1, -1), corners.get("maxX_maxY_minZ").clone().add(0, -1, 1)));
        fillLocations.addAll(getLocations(corners.get("minX_maxY_minZ").clone().add(1,0,1), corners.get("maxX_maxY_maxZ").clone().add(-1,0,-1)));
        fillLocations.addAll(getLocations(corners.get("minX_minY_minZ").clone().add(1,0,1), corners.get("maxX_minY_maxZ").clone().add(-1,0,-1)));

        return fillLocations;
    }

    private List<Location> getFillLocations(MapModel cubeModel) {
        Map<String, Location> corners = cubeModel.getCorners();
        List<Location> fillLocations = new ArrayList<>();

        fillLocations.addAll(getLocations(corners.get("minX_minY_minZ"), corners.get("minX_maxY_minZ")));
        fillLocations.addAll(getLocations(corners.get("minX_minY_maxZ"), corners.get("minX_maxY_maxZ")));
        fillLocations.addAll(getLocations(corners.get("maxX_minY_minZ"), corners.get("maxX_maxY_minZ")));
        fillLocations.addAll(getLocations(corners.get("maxX_minY_maxZ"), corners.get("maxX_maxY_maxZ")));

        fillLocations.addAll(getLocations(corners.get("minX_minY_minZ"), corners.get("maxX_minY_minZ")));
        fillLocations.addAll(getLocations(corners.get("maxX_minY_maxZ"), corners.get("minX_minY_maxZ")));
        fillLocations.addAll(getLocations(corners.get("maxX_minY_minZ"), corners.get("maxX_minY_maxZ")));
        fillLocations.addAll(getLocations(corners.get("minX_minY_minZ"), corners.get("minX_minY_maxZ")));

        fillLocations.addAll(getLocations(corners.get("maxX_maxY_maxZ"), corners.get("minX_maxY_maxZ")));
        fillLocations.addAll(getLocations(corners.get("maxX_maxY_minZ"), corners.get("minX_maxY_minZ")));
        fillLocations.addAll(getLocations(corners.get("minX_maxY_minZ"), corners.get("minX_maxY_maxZ")));
        fillLocations.addAll(getLocations(corners.get("maxX_maxY_minZ"), corners.get("maxX_maxY_maxZ")));

        return fillLocations;
    }
 }
