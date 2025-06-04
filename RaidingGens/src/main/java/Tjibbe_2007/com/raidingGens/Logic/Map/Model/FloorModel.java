package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class FloorModel extends Model {
    public FloorModel(Location location) {
        super(location,
              location.clone().add(MapConfig.getCubeSize(), 0, MapConfig.getCubeSize()),
              MapConfig.getCubeSize(),
              MapConfig.getCubeOutlineMaterial(),
              MapConfig.getCubeFillMaterial());
    }

    @Override
    public HashMap<Location, Material> getFillLocations() {
        return ModelUtils.getLocations(this.corners.get("minX_minY_minZ"), this.corners.get("maxX_minY_maxZ"), ModelMode.VERTICAL);
    }
}
