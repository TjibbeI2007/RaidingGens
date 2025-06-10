package Tjibbe_2007.com.raidingGens.Logic.Map.Model.Enterance;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Model;
import Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class EntranceEastModel extends Model {
    public EntranceEastModel(Location location, int priority, int placeChance) {
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
        return new HashMap<>(ModelUtils.getLocations(
                this.corners.get("minX_maxY_maxZ").clone().add(0, -margin, -margin),
                this.corners.get("minX_minY_minZ").clone().add(0, margin, margin),
                Material.AIR,
                ModelMode.YZ
        ));
    }
}
