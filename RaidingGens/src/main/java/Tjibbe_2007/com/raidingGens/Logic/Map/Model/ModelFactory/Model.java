package Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory;

import Tjibbe_2007.com.raidingGens.Logic.Map.Model.CubeModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.FloorModel;
import org.bukkit.Location;

public class Model {
    private final String modelType;
    private final Location startLocation;

    public Model(Builder builder) {
        this.modelType = builder.modelType;
        this.startLocation = builder.startLocation;
    }

    public static class Builder {
        private final String modelType;
        private final Location startLocation;

        public Builder(String modelType, Location startLocation) {
            this.modelType = modelType;
            this.startLocation = startLocation;
        }

        public Model build() { return new Model(this); }
    }

    public MapModel createModel() {
        if (modelType.equals("FloorModel")) {
            return new FloorModel(startLocation);
        } else if (modelType.equals("CubeModel")) {
            return new CubeModel(startLocation);
        }
        return null;
    }
}
