package Tjibbe_2007.com.raidingGens.Logic.Map.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.MapModel;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.ModelFactory.Model;
import org.bukkit.Location;

import java.util.*;
import java.util.function.Supplier;

public class MapLoadManager {
    private final Supplier<Integer> random = () -> new Random().nextInt(100);
    private final Location location;
    private final int mapSize = MapConfig.getMapSize();
    private final int mapHight = MapConfig.getMapHeight();
    private final int cubeSize = MapConfig.getCubeSize();
    private final Map<Integer, Queue<Runnable>> loadQueue = new HashMap<>();

    public MapLoadManager(Location location) {
        this.location = location;
    }

    public void create() {
        for (int x = 0; x < mapSize; x += cubeSize) {
            for (int z = 0; z < mapSize; z += cubeSize) {
                int y = 0;
                addFloorModel(location.clone().add(x, y, z));
                while (random.get() >= 50) {
                    Location cubeLocation = location.clone().add(x, y, z);
                    y += cubeSize;

                    if (y > mapHight) break;

                    if (random.get() >= 50) {
                        addCubeModel(cubeLocation);
                    } else {
                        addSupportModel(cubeLocation);
                    }
                }
            }
        }
    }
    private void addFloorModel(Location location) {
        loadQueue.computeIfAbsent(0, (key) -> new ArrayDeque<>());
        loadQueue.get(0).add(() -> {
            MapModel floorModel = new Model.Builder("FloorModel", location).build().createModel();
            MapPlaceManager placeManager = new MapPlaceManager(location, floorModel);
            placeManager.placeModel();
        });
    }

    private void addCubeModel(Location location) {
        loadQueue.computeIfAbsent(1, (key) -> new ArrayDeque<>());
        loadQueue.get(1).add(() -> {
            MapModel cubeModel = new Model.Builder("CubeModel", location).build().createModel();
            MapPlaceManager placeManager = new MapPlaceManager(location, cubeModel);
            placeManager.placeModel();
        });
    }

    private void addSupportModel(Location location) {
        loadQueue.computeIfAbsent(2, (key) -> new ArrayDeque<>());
        loadQueue.get(2).add(() -> {
            MapModel supportModel = new Model.Builder("SupportModel", location).build().createModel();
            MapPlaceManager placeManager = new MapPlaceManager(location, supportModel);
            placeManager.placeModel();
        });
    }

    public void loadQueue() {
        for (int i = 0; i < loadQueue.size(); i++) {
            Queue<Runnable> queue = loadQueue.get(i);
            if (queue != null) while (!queue.isEmpty()) queue.poll().run();
        }
    }

    public Location getLocation() { return location; }
    public int getCubeSize() { return cubeSize; }
}
