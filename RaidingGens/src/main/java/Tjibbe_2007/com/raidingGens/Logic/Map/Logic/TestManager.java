package Tjibbe_2007.com.raidingGens.Logic.Map.Logic;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.ModelType;
import Tjibbe_2007.com.raidingGens.Logic.Map.Manager.ModelManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class TestManager {
    private final int cubeSize = MapConfig.CUBE_SIZE;
    private final int mapSize = MapConfig.MAP_SIZE;
    private final int mapHeight = MapConfig.MAP_HEIGHT;

    Random random = new Random();

    private final JavaPlugin plugin;
    private final Location location;

    private final int maxWidth = this.mapSize / this.cubeSize;
    private final int maxDepth = this.mapSize / this.cubeSize;
    private final int maxHeight = this.mapHeight / this.cubeSize;

    public TestManager(JavaPlugin plugin, Location location) {
        this.plugin = plugin;
        this.location = location;
    }


    public void loadMap() {
        int[][] heightMap = mockHeightMap();
        ModelType[][][] map = mockMap(heightMap);
        Map<Integer, Queue<Runnable>> queueMap = generateMapQueue(map);
        generateMap(queueMap);
    }

    private int[][] mockHeightMap() {
        int[][] heightMap = new int[maxWidth][maxDepth];

        for (int x = 0; x < maxWidth; x++) {
            for (int z = 0; z < maxDepth; z++) {
                heightMap[x][z] = random.nextInt(maxHeight);
            }
        }

        return heightMap;
    }

    private ModelType[][][] mockMap(int[][] heightMap) {
        ModelType[][][] map = new ModelType[maxWidth][maxHeight][maxDepth];

        for (int x = 0; x < maxWidth; x++) {
            for (int z = 0; z < maxDepth; z++) {
                for (int y = 0; y <= heightMap[x][z]; y++) {
                    if (ModelType.PYRAMID_MODEL.canPlace(x,y,z,heightMap) && random.nextInt(100) > 50) {
                        map[x][y][z] = ModelType.PYRAMID_MODEL;
                    } else if (ModelType.SUPPORT_MODEL.canPlace(x,y,z, heightMap) && random.nextInt(100) > 75) {
                        map[x][y][z] = ModelType.SUPPORT_MODEL;
                    } else if (ModelType.CUBE_MODEL.canPlace(x,y,z, heightMap)) {
                        map[x][y][z] = ModelType.CUBE_MODEL;
                    }
                }
            }
        } return map;
    }

    private Map<Integer, Queue<Runnable>> generateMapQueue(ModelType[][][] map) {
        Map<Integer, Queue<Runnable>> loadQueue = new HashMap<>();
        for (int priority = 0; priority <= ModelType.MAX_PRIORITY; priority++) loadQueue.put(priority, new ArrayDeque<>());

        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                for (int z = 0; z < maxDepth; z++) {
                    if (y == 0) {
                        HashMap<Location, Material> floorLocations = new ModelManager.Builder(ModelType.FLOOR_MODEL, location.clone().add(x * cubeSize, 0, z * cubeSize)).build().createModel().getBlockLocations();
                        loadQueue.get(ModelType.FLOOR_MODEL.getPriority()).add(() -> floorLocations.forEach((blockLocation, material) -> blockLocation.getBlock().setType(material)));
                    }

                    ModelType modelType = map[x][y][z];
                    if (modelType == null) continue;

                    int blockPriority = modelType.getPriority();
                    HashMap<Location, Material> blockLocations = new ModelManager.Builder(modelType, location.clone().add(x * cubeSize, y * cubeSize, z * cubeSize))
                            .build()
                            .createModel()
                            .getBlockLocations();

                    loadQueue.get(blockPriority).add(() -> blockLocations.forEach((blockLocation, material) -> blockLocation.getBlock().setType(material)));
                }
            }
        } return loadQueue;
    }

    private void generateMap(Map<Integer, Queue<Runnable>> loadQueue) {
        Queue<Runnable> combinedQueue = new ArrayDeque<>();
        for (int i = 0; i < loadQueue.size(); i++) combinedQueue.addAll(loadQueue.getOrDefault(i, new ArrayDeque<>()));
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < Math.min(MapConfig.MODEL_SPAWN_AMOUNT, combinedQueue.size()); i++)
                    combinedQueue.poll().run();

                if (combinedQueue.isEmpty()) cancel();
            }
        }.runTaskTimer(plugin, 0L, MapConfig.MODEL_SPAWN_DELAY);
    }
}
