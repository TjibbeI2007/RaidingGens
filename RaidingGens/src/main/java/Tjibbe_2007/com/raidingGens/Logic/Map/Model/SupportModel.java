package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class SupportModel extends Model {
    public SupportModel(Location location) {
        super(location,
              location.clone().add(MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE),
              MapConfig.CUBE_SIZE,
              MapConfig.CUBE_OUTLINE_MATERIAL,
              MapConfig.CUBE_FILL_MATERIAL);
    }

    @Override
    public HashMap<Location, Material> getBlockLocations() {
        HashMap<Location, Material> outlineLocations = new HashMap<>();

        for (double i = 0; i <= size; i += 1) {
            outlineLocations.put(startLocation.clone().add(i, i, 0), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(0, i, i), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(i, i, size), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(size, i, i), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(i, -i + size, 0), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(0, -i + size, i), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(i, -i + size, size), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(size, -i + size, i), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(i, 0, i), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(-i + size, 0, i), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(i, size, i), MapConfig.CUBE_OUTLINE_MATERIAL);
            outlineLocations.put(startLocation.clone().add(-i + size, size, i), MapConfig.CUBE_OUTLINE_MATERIAL);
        }

        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("minX_maxY_minZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_maxZ"), corners.get("minX_maxY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_minZ"), corners.get("maxX_maxY_minZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_maxZ"), corners.get("maxX_maxY_maxZ"), ModelMode.ALL));

        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("maxX_minY_minZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_maxZ"), corners.get("minX_minY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_minZ"), corners.get("maxX_minY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("minX_minY_maxZ"), ModelMode.ALL));

        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_maxY_maxZ"), corners.get("minX_maxY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_maxY_minZ"), corners.get("minX_maxY_minZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_maxY_minZ"), corners.get("minX_maxY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_maxY_minZ"), corners.get("maxX_maxY_maxZ"), ModelMode.ALL));
        return outlineLocations;
    }
}
