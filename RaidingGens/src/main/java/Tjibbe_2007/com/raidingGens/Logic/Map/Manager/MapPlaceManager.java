package Tjibbe_2007.com.raidingGens.Logic.Map.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Map.Model.CubeModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;
import java.util.Map;

public class MapPlaceManager {
    Location location;
    MapModel model;
    public MapPlaceManager(Location location, MapModel model) {
        this.location = location;
        this.model = model;
    }

    private void fillOutline(List<Location> locations) {
        locations.forEach(location -> location.getBlock().setType(model.getOutlineMaterial()));
    }

    private void fillSides(List<Location> locations) {
        locations.forEach(location -> location.getBlock().setType(model.getFillMaterial()));
    }

    public void placeModel() {
        fillOutline(model.getOutlineLocations());
        fillSides(model.getFillLocations());
    }
}
