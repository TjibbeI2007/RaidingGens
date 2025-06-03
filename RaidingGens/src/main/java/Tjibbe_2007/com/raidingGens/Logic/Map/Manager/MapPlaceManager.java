package Tjibbe_2007.com.raidingGens.Logic.Map.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Map.Model.CubeModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.FloorModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Map;

public class MapPlaceManager {
    Location location;
    public MapPlaceManager(Location location) {
        this.location = location;
    }
    private void fillBlocks(Location startLocation, Location endLocation, Material material) {
        int minX = Math.min(startLocation.getBlockX(), endLocation.getBlockX());
        int minY = Math.min(startLocation.getBlockY(), endLocation.getBlockY());
        int minZ = Math.min(startLocation.getBlockZ(), endLocation.getBlockZ());

        int maxX = Math.max(startLocation.getBlockX(), endLocation.getBlockX());
        int maxY = Math.max(startLocation.getBlockY(), endLocation.getBlockY());
        int maxZ = Math.max(startLocation.getBlockZ(), endLocation.getBlockZ());
        ;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Location loc = new Location(startLocation.getWorld(), x, y, z);
                    loc.getBlock().setType(material);
                }
            }
        }
    }

    private void fillOutline(MapModel cubeModel) {
        Map<String, Location> corners = cubeModel.getCorners();

        fillBlocks(corners.get("minX_minY_minZ"), corners.get("minX_maxY_minZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("minX_minY_maxZ"), corners.get("minX_maxY_maxZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("maxX_minY_minZ"), corners.get("maxX_maxY_minZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("maxX_minY_maxZ"), corners.get("maxX_maxY_maxZ"), cubeModel.getOutlineMaterial());

        fillBlocks(corners.get("minX_minY_minZ"), corners.get("maxX_minY_minZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("maxX_minY_maxZ"), corners.get("minX_minY_maxZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("maxX_minY_minZ"), corners.get("maxX_minY_maxZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("minX_minY_minZ"), corners.get("minX_minY_maxZ"), cubeModel.getOutlineMaterial());

        fillBlocks(corners.get("maxX_maxY_maxZ"), corners.get("minX_maxY_maxZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("maxX_maxY_minZ"), corners.get("minX_maxY_minZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("minX_maxY_minZ"), corners.get("minX_maxY_maxZ"), cubeModel.getOutlineMaterial());
        fillBlocks(corners.get("maxX_maxY_minZ"), corners.get("maxX_maxY_maxZ"), cubeModel.getOutlineMaterial());
    }

    private void fillSides(MapModel cubeModel) {
        Map<String, Location> corners = cubeModel.getCorners();

        if (cubeModel instanceof CubeModel) {
            fillBlocks(corners.get("minX_minY_minZ").clone().add(1, 1, 0), corners.get("maxX_maxY_minZ").clone().add(-1, -1, 0), cubeModel.getFillMaterial());
            fillBlocks(corners.get("minX_minY_minZ").clone().add(0, 1, 1), corners.get("minX_maxY_maxZ").clone().add(0, -1, -1), cubeModel.getFillMaterial());
            fillBlocks(corners.get("maxX_minY_maxZ").clone().add(-1, 1, 0), corners.get("minX_maxY_maxZ").clone().add(1, -1, 0), cubeModel.getFillMaterial());
            fillBlocks(corners.get("maxX_minY_maxZ").clone().add(0, 1, -1), corners.get("maxX_maxY_minZ").clone().add(0, -1, 1), cubeModel.getFillMaterial());
            fillBlocks(corners.get("minX_maxY_minZ").clone().add(1,0,1), corners.get("maxX_maxY_maxZ").clone().add(-1,0,-1), cubeModel.getFillMaterial());
        }

        fillBlocks(corners.get("minX_minY_minZ").clone().add(1,0,1), corners.get("maxX_minY_maxZ").clone().add(-1,0,-1), cubeModel.getFillMaterial());
    }

    public void placeModel(MapModel model) {
        fillOutline(model);
        fillSides(model);
    }
}
