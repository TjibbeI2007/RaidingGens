package Tjibbe_2007.com.raidingGens.Logic.Map.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Map.Model.CubeModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.FloorModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Model;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.PyramidModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.SupportModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelType;
import org.bukkit.Location;

public class ModelManager {
    private final ModelType modelType;
    private final Location startLocation;

    public ModelManager(Builder builder) {
        this.modelType = builder.modelType;
        this.startLocation = builder.startLocation;
    }

    public static class Builder {
        private final ModelType modelType;
        private final Location startLocation;

        public Builder(ModelType modelType, Location startLocation) {
            this.modelType = modelType;
            this.startLocation = startLocation;
        }

        public ModelManager build() { return new ModelManager(this); }
    }

    public Model createModel() {
        return switch (modelType) {
            case ModelType.FLOOR_MODEL -> new FloorModel(startLocation);
            case ModelType.CUBE_MODEL -> new CubeModel(startLocation);
            case ModelType.SUPPORT_MODEL -> new SupportModel(startLocation);
            case ModelType.PYRAMID_MODEL -> new PyramidModel(startLocation);
            default -> null;
        };
    }
}
