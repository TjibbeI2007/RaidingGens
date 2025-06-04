package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

import static Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils.getLocations;

public class PyramidModel extends Model {

    public PyramidModel(Location location) {
        super(location,
              location.clone().add(MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE),
              MapConfig.CUBE_SIZE,
              MapConfig.CUBE_OUTLINE_MATERIAL,
              MapConfig.CUBE_FILL_MATERIAL);
    }

    @Override
    public HashMap<Location, Material> getBlockLocations() {
        HashMap<Location, Material> pyramidLocations = new HashMap<>();
        for (int i = 1; i < (size-1)/2; i++) {
            pyramidLocations.putAll(getLocations(corners.get("maxX_minY_maxZ").clone().add(-i,i,-i), corners.get("minX_minY_maxZ").clone().add(i,i,-i), Material.GLASS, ModelMode.X));
            pyramidLocations.putAll(getLocations(corners.get("minX_minY_minZ").clone().add(i,i,i), corners.get("maxX_minY_minZ").clone().add(-i,i,i), Material.GLASS, ModelMode.X));
            pyramidLocations.putAll(getLocations(corners.get("maxX_minY_minZ").clone().add(-i,i,i), corners.get("maxX_minY_maxZ").clone().add(-i,i,-i), Material.GLASS, ModelMode.Z));
            pyramidLocations.putAll(getLocations(corners.get("minX_minY_minZ").clone().add(i,i,i), corners.get("minX_minY_maxZ").clone().add(i,i,-i), Material.GLASS, ModelMode.Z));
        }
        return pyramidLocations;
    }
}
