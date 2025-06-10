package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Utils.ModelUtils;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelMode;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class SupportModel extends Model {
    public SupportModel(Location location, int priority, int placeChance) {
        super(location,
              location.clone().add(MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE, MapConfig.CUBE_SIZE),
              MapConfig.CUBE_SIZE,
              MapConfig.CUBE_OUTLINE_MATERIAL,
              MapConfig.CUBE_FILL_MATERIAL,
              priority,
              placeChance);
    }

    @Override
    public HashMap<Location, Material> getBlockLocations() {
        HashMap<Location, Material> outlineLocations = new HashMap<>();

        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("minX_minY_minZ"), corners.get("maxX_maxY_minZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("minX_minY_maxZ"), corners.get("maxX_maxY_maxZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("maxX_minY_minZ"), corners.get("minX_maxY_minZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("maxX_minY_maxZ"), corners.get("minX_maxY_maxZ"), this.outlineMaterial));

        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("minX_minY_minZ"), corners.get("minX_maxY_maxZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("minX_maxY_minZ"), corners.get("minX_minY_maxZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("maxX_minY_minZ"), corners.get("maxX_maxY_maxZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("maxX_maxY_minZ"), corners.get("maxX_minY_maxZ"), this.outlineMaterial));

        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("minX_maxY_minZ"), corners.get("maxX_maxY_maxZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("maxX_maxY_minZ"), corners.get("minX_maxY_maxZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("minX_minY_minZ"), corners.get("maxX_minY_maxZ"), this.outlineMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("maxX_minY_minZ"), corners.get("minX_minY_maxZ"), this.outlineMaterial));

        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("minX_minY_minZ"), corners.get("maxX_maxY_maxZ"), this.fillMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("maxX_minY_minZ"), corners.get("minX_maxY_maxZ"), this.fillMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("minX_maxY_minZ"), corners.get("maxX_minY_maxZ"), this.fillMaterial));
        outlineLocations.putAll(ModelUtils.getDiagonalLine(corners.get("maxX_minY_minZ"), corners.get("minX_maxY_maxZ"), this.fillMaterial));

        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("minX_maxY_minZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_maxZ"), corners.get("minX_maxY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_minZ"), corners.get("maxX_maxY_minZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_maxZ"), corners.get("maxX_maxY_maxZ"), ModelMode.ALL));

        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("maxX_minY_minZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_maxZ"), corners.get("minX_minY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_minY_minZ"), corners.get("maxX_minY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_minY_minZ"), corners.get("minX_minY_maxZ"), ModelMode.ALL));

        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_maxY_maxZ"), corners.get("minX_maxY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_maxY_minZ"), corners.get("minX_maxY_minZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("minX_maxY_minZ"), corners.get("minX_maxY_maxZ"), ModelMode.ALL));
        outlineLocations.putAll(ModelUtils.getLocations(corners.get("maxX_maxY_minZ"), corners.get("maxX_maxY_maxZ"), ModelMode.ALL));

        return outlineLocations;
    }
}
