package Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory;

import Tjibbe_2007.com.raidingGens.Logic.Map.Model.CubeModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.FloorModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.PyramidModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.SupportModel;
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
        return switch (modelType) {
            case "FloorModel" -> new FloorModel(startLocation);
            case "CubeModel" -> new CubeModel(startLocation);
            case "SupportModel" -> new SupportModel(startLocation);
            case "PyramidModel" -> new PyramidModel(startLocation);
            default -> null;
        };
    }
}
