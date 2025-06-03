package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import org.bukkit.Location;

public class FloorModel extends MapModel {
    public FloorModel(Location location) {
        super(location,
              location.clone().add(MapConfig.getCubeSize(), 0, MapConfig.getCubeSize()),
              MapConfig.getCubeSize(),
              MapConfig.getCubeOutlineMaterial(),
              MapConfig.getCubeFillMaterial());
    }
}
