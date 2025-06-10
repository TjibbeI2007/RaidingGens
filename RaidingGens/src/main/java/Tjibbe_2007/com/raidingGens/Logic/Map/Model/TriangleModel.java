package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class TriangleModel extends Model {
    public TriangleModel(Location location, int priority, int placeChance) {
        super(location,
              location.clone().add(MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE),
              MapConfig.CUBE_SIZE,
              MapConfig.CUBE_OUTLINE_MATERIAL,
              MapConfig.CUBE_FILL_MATERIAL,
              priority,
              placeChance);
    }

    @Override
    public HashMap<Location, Material> getBlockLocations() {
        HashMap<Location, Material> fillLocations = new HashMap<>();

        for (int i = 1; i < size; i++) {
            fillLocations.putAll(ModelUtils.getLocations(startLocation.clone().add(size, i, i), startLocation.clone().add(0, i, i), Material.GLASS, ModelMode.NONE));
            fillLocations.putAll(ModelUtils.getLocations(startLocation.clone().add(0, i, i), startLocation.clone().add(0, 0, i), Material.GLASS, ModelMode.X));
            fillLocations.putAll(ModelUtils.getLocations(startLocation.clone().add(size, i, i), startLocation.clone().add(size, 0, i), Material.GLASS, ModelMode.X));
        }

        fillLocations.putAll(ModelUtils.getLocations(corners.get("maxX_maxY_maxZ"), corners.get("minX_minY_maxZ"),ModelMode.X));

        return fillLocations;
    }
}
