package Tjibbe_2007.com.raidingGens.Logic.Map.Logic;

import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Model;
import org.bukkit.Location;

public class MapPlaceManager {
    Location location;
    Model model;
    public MapPlaceManager(Location location, Model model) {
        this.location = location;
        this.model = model;
    }

    public void placeModel() {
        model.getFillLocations().forEach((location, material) -> location.getBlock().setType(material));
    }
}
