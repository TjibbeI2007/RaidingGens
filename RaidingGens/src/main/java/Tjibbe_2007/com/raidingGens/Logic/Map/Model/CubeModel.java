package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CubeModel extends MapModel {
    public CubeModel(Location location) {
        super(location,
              location.clone().add(MapConfig.getCubeSize(), MapConfig.getCubeSize(), MapConfig.getCubeSize()),
              MapConfig.getCubeSize(),
              MapConfig.getCubeOutlineMaterial(),
              MapConfig.getCubeFillMaterial());
    }

    @Override
    public List<Location> getOutlineLocations() {
        return new ArrayList<>(UtilitiesModel.getCubeOutlineLocations(this.corners));
    }

    @Override
    public List<Location> getFillLocations() {
        Map<String, Location> corners = this.getCorners();
        List<Location> fillLocations = new ArrayList<>();

        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ").clone().add(1, 1, 0), corners.get("maxX_maxY_minZ").clone().add(-1, -1, 0)));
        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ").clone().add(0, 1, 1), corners.get("minX_maxY_maxZ").clone().add(0, -1, -1)));
        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_minY_maxZ").clone().add(-1, 1, 0), corners.get("minX_maxY_maxZ").clone().add(1, -1, 0)));
        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("maxX_minY_maxZ").clone().add(0, 1, -1), corners.get("maxX_maxY_minZ").clone().add(0, -1, 1)));
        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_maxY_minZ").clone().add(1,0,1), corners.get("maxX_maxY_maxZ").clone().add(-1,0,-1)));
        fillLocations.addAll(UtilitiesModel.getFillLocations(corners.get("minX_minY_minZ").clone().add(1,0,1), corners.get("maxX_minY_maxZ").clone().add(-1,0,-1)));

        return fillLocations;
    }
}
