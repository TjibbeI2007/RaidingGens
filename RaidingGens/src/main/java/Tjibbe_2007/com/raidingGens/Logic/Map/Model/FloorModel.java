package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class FloorModel extends Model {
    public FloorModel(Location location, int priority, int placeChance) {
        super(location,
              location.clone().add(MapConfig.CUBE_SIZE, 0, MapConfig.CUBE_SIZE),
              MapConfig.CUBE_SIZE,
              MapConfig.CUBE_OUTLINE_MATERIAL,
              MapConfig.CUBE_FILL_MATERIAL,
              priority,
              placeChance);
    }

    @Override
    public HashMap<Location, Material> getBlockLocations() {
        return ModelUtils.getLocations(this.corners.get("minX_minY_minZ"), this.corners.get("maxX_minY_maxZ"), ModelMode.XZ);
    }
}
