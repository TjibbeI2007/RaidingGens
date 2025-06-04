package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FloorModel extends MapModel {
    public FloorModel(Location location) {
        super(location,
              location.clone().add(MapConfig.getCubeSize(), 0, MapConfig.getCubeSize()),
              MapConfig.getCubeSize(),
              MapConfig.getCubeOutlineMaterial(),
              MapConfig.getCubeFillMaterial());
    }

    @Override
    public List<Location> getOutlineLocations() {
        Map<String, Location> corners = this.getCorners();
        List<Location> fillLocations = new ArrayList<>();

        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ"), corners.get("maxX_minY_minZ")));
        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ"), corners.get("minX_minY_maxZ")));
        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_maxY_maxZ"), corners.get("maxX_minY_minZ")));
        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_maxY_maxZ"), corners.get("minX_minY_maxZ")));

        return fillLocations;
    }

    @Override
    public List<Location> getFillLocations() {
        Map<String, Location> corners = this.getCorners();
        return UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ").add(1,0,1), corners.get("maxX_minY_maxZ").add(-1,0,-1));
    }
}
