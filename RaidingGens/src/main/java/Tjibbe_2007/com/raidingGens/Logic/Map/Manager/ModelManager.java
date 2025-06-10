package Tjibbe_2007.com.raidingGens.Logic.Map.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models.ModelEntrance;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models.ModelInterface;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.*;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models.ModelStructure;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Enterance.EntranceEastModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Enterance.EntranceNorthModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Enterance.EntranceSouthModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Enterance.EntranceWestModel;
import org.bukkit.Location;

public class ModelManager {
    private final ModelInterface modelType;
    private final Location startLocation;
    private final int priority;
    private final int placeChance;


    public ModelManager(Builder builder) {
        this.modelType = builder.modelType;
        this.startLocation = builder.startLocation;
        this.priority = builder.getPriority();
        this.placeChance = builder.getPlaceChance();
    }

    public static class Builder {
        private final ModelInterface modelType;
        private final Location startLocation;
        private int priority = 0;
        private int placeChance = 0;

        public Builder(ModelInterface modelType, Location startLocation) {
            this.modelType = modelType;
            this.startLocation = startLocation;
        }

        public Builder setPriority(int priority) {
            if (priority < 0) throw new IllegalArgumentException("Priority must be non-negative");
            this.priority = priority;
            return this;
        }
        public Builder setPlaceChance(int placeChance) {
            if (placeChance < 0 || placeChance > 100) throw new IllegalArgumentException("Place chance must be between 0 and 100");
            this.placeChance = placeChance;
            return this;
        }

        public int getPriority() { return priority; }
        public int getPlaceChance() { return placeChance; }

        public ModelManager build() { return new ModelManager(this); }
    }

    public Model createModel() {
        if (modelType instanceof ModelStructure structure) {
            return switch (structure) {
                case FLOOR_MODEL -> new FloorModel(startLocation, priority, placeChance);
                case CUBE_MODEL -> new CubeModel(startLocation, priority, placeChance);
                case SUPPORT_MODEL -> new SupportModel(startLocation, priority, placeChance);
                case PYRAMID_MODEL -> new PyramidModel(startLocation, priority, placeChance);
                case TRIANGLE_MODEL -> new TriangleModel(startLocation, priority, placeChance);
            };
        } else if (modelType instanceof ModelEntrance structure) {
            return switch (structure) {
                case NORTH_ENTRANCE -> new EntranceNorthModel(startLocation, priority, placeChance);
                case EAST_ENTRANCE -> new EntranceEastModel(startLocation, priority, placeChance);
                case SOUTH_ENTRANCE -> new EntranceSouthModel(startLocation, priority, placeChance);
                case WEST_ENTRANCE -> new EntranceWestModel(startLocation, priority, placeChance);
            };
        }
        return null;
    }
}
