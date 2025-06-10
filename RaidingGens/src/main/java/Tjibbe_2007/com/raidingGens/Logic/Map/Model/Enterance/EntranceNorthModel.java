package Tjibbe_2007.com.raidingGens.Logic.Map.Model.Enterance;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Model;
import Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class EntranceNorthModel extends Model {
    public EntranceNorthModel(Location location, int priority, int placeChance) {
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
        int margin = this.size/3;
        return ModelUtils.getLocations(
                this.corners.get("minX_minY_maxZ").clone().add(margin,margin,0),
                this.corners.get("maxX_maxY_maxZ").clone().add(-margin,-margin,0),
                Material.AIR,
                ModelMode.XY
        );
    }
}
