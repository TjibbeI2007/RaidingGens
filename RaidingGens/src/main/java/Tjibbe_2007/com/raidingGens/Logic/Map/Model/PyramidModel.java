package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import org.bukkit.Location;

import java.util.List;

public class PyramidModel extends MapModel {

    public PyramidModel(Location location) {
        super(location,
              location.clone().add(MapConfig.getCubeSize(), MapConfig.getCubeSize(), MapConfig.getCubeSize()),
              MapConfig.getCubeSize(),
              MapConfig.getCubeOutlineMaterial(),
              MapConfig.getCubeFillMaterial());
    }

    @Override
    public List<Location> getOutlineLocations() {
        return UtilitiesModel.getPyramidLocations(this.corners);
    }

    @Override
    public List<Location> getFillLocations() {
        return List.of();
    }

}
