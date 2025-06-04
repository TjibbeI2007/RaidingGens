package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class SupportModel extends MapModel {
    public SupportModel(Location location) {
        super(location,
              location.clone().add(MapConfig.getCubeSize(), MapConfig.getCubeSize(), MapConfig.getCubeSize()),
              MapConfig.getCubeSize(),
              MapConfig.getCubeOutlineMaterial(),
              MapConfig.getCubeFillMaterial());
    }


    @Override
    public List<Location> getOutlineLocations() {
        List<Location> outlineLocations = new ArrayList<>();
        outlineLocations.addAll(UtilitiesModel.getDiagonalLocations(this.corners.get("minX_minY_minZ"), this.corners.get("maxX_minY_minZ")));
        outlineLocations.addAll(UtilitiesModel.getCubeOutlineLocations(this.corners));
        return outlineLocations;
    }

    @Override
    public List<Location> getFillLocations() {
        return List.of();
    }
}
