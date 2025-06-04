package Tjibbe_2007.com.raidingGens.Logic.Map.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Logic.MapPlaceManager;
import Tjibbe_2007.com.raidingGens.Logic.Map.Model.Model;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelType;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.function.Supplier;

public class MapLoadManager {
    private final JavaPlugin plugin;
    private final Supplier<Integer> random = () -> new Random().nextInt(100);
    private final Location location;
    private final int mapSize = MapConfig.MAP_SIZE;
    private final int mapHight = MapConfig.MAP_HEIGHT;
    private final int cubeSize = MapConfig.CUBE_SIZE;
    private final int modelSpawnAmount = MapConfig.MODEL_SPAWN_AMOUNT;
    private final long modelSpawnDelay = MapConfig.MODEL_SPAWN_DELAY;
    private final Map<Integer, Queue<Runnable>> loadQueue = new HashMap<>();

    public MapLoadManager(JavaPlugin plugin, Location location) {
        this.location = location;
        this.plugin = plugin;
    }

    public void create() {
        for (int x = 0; x < mapSize; x += cubeSize) {
            for (int z = 0; z < mapSize; z += cubeSize) {
                int y = 0;
                addFloorModel(location.clone().add(x, y, z));

                while (random.get() >= 50) {
                    Location cubeLocation = location.clone().add(x, y, z);

                    if (y > mapHight) {
                        addPyramidModel(cubeLocation);
                        break;
                    } else if (random.get() >= 50) {
                        addCubeModel(cubeLocation);
                    } else {
                        addSupportModel(cubeLocation);
                    } y += cubeSize;
                }
            }
        }
    }
    private void addFloorModel(Location location) {
        loadQueue.computeIfAbsent(0, (key) -> new ArrayDeque<>());
        loadQueue.get(0).add(() -> {
            Model floorModel = new ModelManager.Builder(ModelType.FLOOR_MODEL, location).build().createModel();
            MapPlaceManager placeManager = new MapPlaceManager(location, floorModel);
            placeManager.placeModel();
        });
    }

    private void addCubeModel(Location location) {
        loadQueue.computeIfAbsent(1, (key) -> new ArrayDeque<>());
        loadQueue.get(1).add(() -> {
            Model cubeModel = new ModelManager.Builder(ModelType.CUBE_MODEL, location).build().createModel();
            MapPlaceManager placeManager = new MapPlaceManager(location, cubeModel);
            placeManager.placeModel();
        });
    }

    private void addSupportModel(Location location) {
        loadQueue.computeIfAbsent(2, (key) -> new ArrayDeque<>());
        loadQueue.get(2).add(() -> {
            Model supportModel = new ModelManager.Builder(ModelType.SUPPORT_MODEL, location).build().createModel();
            MapPlaceManager placeManager = new MapPlaceManager(location, supportModel);
            placeManager.placeModel();
        });
    }

    private void addPyramidModel(Location location) {
        loadQueue.computeIfAbsent(3, (key) -> new ArrayDeque<>());
        loadQueue.get(3).add(() -> {
            Model pyramidModel = new ModelManager.Builder(ModelType.PYRAMID_MODEL, location).build().createModel();
            MapPlaceManager placeManager = new MapPlaceManager(location, pyramidModel);
            placeManager.placeModel();
        });
    }

    public void loadQueue() {
        Queue<Runnable> combinedQueue = new ArrayDeque<>();
        for (int i = 0; i < loadQueue.size(); i++) combinedQueue.addAll(loadQueue.getOrDefault(i, new ArrayDeque<>()));

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < modelSpawnAmount; i++) {
                    if (combinedQueue.isEmpty()) {
                        cancel();
                        return;
                    }

                    Runnable task = combinedQueue.poll();
                    if (task != null) task.run();
                }
            }
        }.runTaskTimer(plugin, 0L, modelSpawnDelay);
    }

    public Location getLocation() { return location; }
}
