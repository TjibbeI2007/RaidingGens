package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class CubeModel extends Model {
    public CubeModel(Location location) {
        super(location,
              location.clone().add(MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE),
              MapConfig.CUBE_SIZE,
              MapConfig.CUBE_OUTLINE_MATERIAL,
              MapConfig.CUBE_FILL_MATERIAL);
    }

    @Override
    public HashMap<Location, Material> getBlockLocations() {
        HashMap<Location, Material> fillLocations = new HashMap<>();

        fillLocations.putAll(ModelUtils.getLocations(this.corners.get("minX_minY_minZ"), this.corners.get("maxX_maxY_minZ"), ModelMode.X));
        fillLocations.putAll(ModelUtils.getLocations(this.corners.get("minX_minY_minZ"), this.corners.get("minX_maxY_maxZ"), ModelMode.Z));
        fillLocations.putAll(ModelUtils.getLocations(this.corners.get("maxX_minY_maxZ"), this.corners.get("minX_maxY_maxZ"), ModelMode.X));
        fillLocations.putAll(ModelUtils.getLocations(this.corners.get("maxX_minY_maxZ"), this.corners.get("maxX_maxY_minZ"), ModelMode.Z));
        fillLocations.putAll(ModelUtils.getLocations(this.corners.get("minX_maxY_minZ"), this.corners.get("maxX_maxY_maxZ"), ModelMode.VERTICAL));
        fillLocations.putAll(ModelUtils.getLocations(this.corners.get("minX_minY_minZ"), this.corners.get("maxX_minY_maxZ"), ModelMode.VERTICAL));

        return fillLocations;
    }
}
