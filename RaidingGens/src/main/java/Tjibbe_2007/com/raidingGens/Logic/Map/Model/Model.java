package Tjibbe_2007.com.raidingGens.Logic.Map.Model;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

public abstract class Model {
    protected final Location startLocation;
    protected final Location endLocation;
    protected final Map<String, Location> corners;
    protected final int size;
    protected final Material outlineMaterial;
    protected final Material fillMaterial;
    protected final int priority;
    protected final int placeChance;

    protected Model(Location startLocation, Location endLocation, int size, Material outlineMaterial, Material fillMaterial, int priority, int placeChance) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.size = size;
        this.outlineMaterial = outlineMaterial;
        this.fillMaterial = fillMaterial;
        this.corners = calculateCorners(startLocation, endLocation);
        this.priority = priority;
        this.placeChance = placeChance;
    }

    public abstract HashMap<Location, Material> getBlockLocations();

    public Map<String, Location> calculateCorners(Location startLocation, Location endLocation) {
        Map<String, Location> corners = new HashMap<>();
        World world = startLocation.getWorld();

        double minX = Math.min(startLocation.getX(), endLocation.getX());
        double maxX = Math.max(startLocation.getX(), endLocation.getX());
        double minY = Math.min(startLocation.getY(), endLocation.getY());
        double maxY = Math.max(startLocation.getY(), endLocation.getY());
        double minZ = Math.min(startLocation.getZ(), endLocation.getZ());
        double maxZ = Math.max(startLocation.getZ(), endLocation.getZ());

        corners.put("minX_minY_minZ", new Location(world, minX, minY, minZ));
        corners.put("minX_minY_maxZ", new Location(world, minX, minY, maxZ));
        corners.put("maxX_minY_minZ", new Location(world, maxX, minY, minZ));
        corners.put("maxX_minY_maxZ", new Location(world, maxX, minY, maxZ));

        corners.put("minX_maxY_minZ", new Location(world, minX, maxY, minZ));
        corners.put("minX_maxY_maxZ", new Location(world, minX, maxY, maxZ));
        corners.put("maxX_maxY_minZ", new Location(world, maxX, maxY, minZ));
        corners.put("maxX_maxY_maxZ", new Location(world, maxX, maxY, maxZ));

        return corners;
    }

    public Location getStartLocation() { return startLocation; }
    public Location getEndLocation() { return endLocation; }
    public Map<String, Location> getCorners() { return corners; }
    public int getPriority() { return priority; }
    public int getPlaceChance() { return placeChance; }
    public int getSize() { return size; }
    public Material getOutlineMaterial() { return outlineMaterial; }
    public Material getFillMaterial() { return fillMaterial; }
}