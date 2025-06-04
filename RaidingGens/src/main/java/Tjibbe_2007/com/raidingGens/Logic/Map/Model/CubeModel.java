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
              location.clone().add(MapConfig.getCubeSize(), MapConfig.getCubeSize(), MapConfig.getCubeSize()),
              MapConfig.getCubeSize(),
              MapConfig.getCubeOutlineMaterial(),
              MapConfig.getCubeFillMaterial());
    }

    @Override
    public HashMap<Location, Material> getFillLocations() {
        Map<String, Location> corners = this.getCorners();
        HashMap<Location, Material> fillLocations = new HashMap<>();

        fillLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("maxX_maxY_minZ"), ModelMode.X));
        fillLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("minX_maxY_maxZ"), ModelMode.Z));
        fillLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_maxZ"), corners.get("minX_maxY_maxZ"), ModelMode.X));
        fillLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_maxZ"), corners.get("maxX_maxY_minZ"), ModelMode.Z));
        fillLocations.putAll(ModelUtils.getLocations(corners.get("minX_maxY_minZ"), corners.get("maxX_maxY_maxZ"), ModelMode.VERTICAL));
        fillLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("maxX_minY_maxZ"), ModelMode.VERTICAL));

        return fillLocations;
    }
}
