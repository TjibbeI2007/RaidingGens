package Tjibbe_2007.com.raidingGens.Logic.Map.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.Model;
import org.bukkit.Location;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class MapLoadManager {
    private final Random random = new Random();
    private final Location location;
    private final int mapSize = MapConfig.getMapSize();
    private final int mapHight = MapConfig.getMapHeight();
    private final int cubeSize = MapConfig.getCubeSize();
    private Queue<Runnable> loadQueue = new ArrayDeque<>();

    public MapLoadManager(Location location) {
        this.location = location;
    }

    public void create() {
        for (int x = 0; x < mapSize; x += cubeSize) {
            for (int z = 0; z < mapSize; z += cubeSize) {
                int y = 0;
                Location floorLocation = location.clone().add(x, y, z);

                loadQueue.add(() -> {
                    MapPlaceManager placeManager = new MapPlaceManager(floorLocation);
                    MapModel floorModel = new Model.Builder("FloorModel", floorLocation).build().createModel();
                    placeManager.placeModel(floorModel);
                });

                while (random.nextInt(100) >= 50) {
                    Location cubeLocation = location.clone().add(x, y, z);
                    y += cubeSize;

                    loadQueue.add(() -> {
                        MapPlaceManager placeManager = new MapPlaceManager(cubeLocation);
                        MapModel cubeModel = new Model.Builder("CubeModel", cubeLocation).build().createModel();
                        placeManager.placeModel(cubeModel);
                    });
                }
            }
        }
    }

    public void loadQueue() { while (!loadQueue.isEmpty()) loadQueue.poll().run(); }

    public Location getLocation() { return location; }
    public int getCubeSize() { return cubeSize; }
}
