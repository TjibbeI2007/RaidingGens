package Tjibbe_2007.com.raidingGens.Logic.Map.Logic;

import Tjibbe_2007.com.raidingGens.Logic.Map.Config.MapConfig;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models.ModelEntrance;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models.ModelInterface;
import Tjibbe_2007.com.raidingGens.Logic.Map.Enum.Models.ModelStructure;
import Tjibbe_2007.com.raidingGens.Logic.Map.Manager.ModelManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class LoadManager {
    private final int cubeSize = MapConfig.CUBE_SIZE;
    private final int mapSize = MapConfig.MAP_SIZE;
    private final int mapHeight = MapConfig.MAP_HEIGHT;

    Random random = new Random();

    private final JavaPlugin plugin;
    private final Location location;

    private final int maxWidth = this.mapSize / this.cubeSize;
    private final int maxDepth = this.mapSize / this.cubeSize;
    private final int maxHeight = this.mapHeight / this.cubeSize;

    public LoadManager(JavaPlugin plugin, Location location) {
        this.plugin = plugin;
        this.location = location;
    }


    public void loadMap() {
        int[][] heightMap = mockHeightMap();
        ModelStructure[][][] map = mockMap(heightMap);
        ModelEntrance[][][] entranceMap = mockEntrances(heightMap, map);

        Map<Integer, Queue<Runnable>> queueMap = generateMapQueue(map);

        generateEntranceQueue(entranceMap).forEach((key, value) ->
            queueMap.merge(key, value, (existingQueue, newQueue) -> {
                existingQueue.addAll(newQueue);
                return existingQueue;
            })
        );

        generateMap(queueMap);
    }

    private int[][] mockHeightMap() {
        int[][] heightMap = new int[maxWidth][maxDepth];

        for (int x = 0; x < maxWidth; x++) {
            for (int z = 0; z < maxDepth; z++) {
                for (int y = 1; y < maxHeight; y++) {
                    if (random.nextDouble() * 100 < 90.0 / (y + 1)) {
                        break;
                    } else {
                        heightMap[x][z] += 1;
                    }
                }
            }
        }

        return heightMap;
    }

    private ModelStructure[][][] mockMap(int[][] heightMap) {
        ModelStructure[][][] map = new ModelStructure[maxWidth][maxHeight][maxDepth];
        List<ModelStructure> sortedModels = Arrays.stream(ModelStructure.values())
            .sorted(Comparator.comparingInt(ModelStructure::getPriority))
            .toList();

        for (int x = 0; x < maxWidth; x++) {
            for (int z = 0; z < maxDepth; z++) {
                for (int y = 0; y < heightMap[x][z]; y++) {
                    for (ModelStructure model : sortedModels) {
                        if (model.placeChance() > 0 &&
                            model.canPlace(x, y, z, heightMap) &&
                            random.nextInt(100) < model.placeChance()
                        ) {
                            map[x][y][z] = model;
                            break;
                        }
                    }
                }
            }
        } return map;
    }

    private ModelEntrance[][][] mockEntrances(int[][] heightMap, ModelStructure[][][] mockMap) {
        ModelEntrance[][][] entranceMap = new ModelEntrance[maxWidth][maxHeight][maxDepth];
        int[][][] sizeMap = new int[maxWidth][maxHeight][maxDepth];
        List<ModelEntrance> sortedModels = Arrays.stream(ModelEntrance.values())
            .sorted(Comparator.comparingInt(ModelEntrance::getPriority).reversed())
            .toList();

        for (int x = 0; x < maxWidth; x++) {
            for (int z = 0; z < maxDepth; z++) {
                for (int y = 0; y < heightMap[x][z]; y++) {
                    if (mockMap[x][y][z] == null || mockMap[x][y][z] != ModelStructure.CUBE_MODEL) continue;

                    while (entranceMap[x][y][z] == null) {
                        for (ModelEntrance entrance : sortedModels) {
                            if (entrance.canPlace(x, y, z, heightMap) &&
                                random.nextInt(100) < entrance.placeChance()) {
                                entranceMap[x][y][z] = entrance;

                                if ((x != 0 && mockMap[x-1][y][z] == ModelStructure.CUBE_MODEL && entranceMap[x-1][y][z] == ModelEntrance.EAST_ENTRANCE && entrance == ModelEntrance.WEST_ENTRANCE)) entranceMap[x][y][z] = null;
                                if ((z != 0 && mockMap[x][y][z-1] == ModelStructure.CUBE_MODEL && entranceMap[x][y][z-1] == ModelEntrance.NORTH_ENTRANCE && entrance == ModelEntrance.SOUTH_ENTRANCE)) entranceMap[x][y][z] = null;

                                break;
                            }
                        }
                    }
                }
            }
        } return entranceMap;
    }

    private Map<Integer, Queue<Runnable>> generateMapQueue(ModelStructure[][][] map) {
        Map<Integer, Queue<Runnable>> loadQueue = new HashMap<>();
        for (int priority = 0; priority <= ModelStructure.values().length; priority++) loadQueue.put(priority, new ArrayDeque<>());

        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                for (int z = 0; z < maxDepth; z++) {
                    if (y == 0) {
                        HashMap<Location, Material> floorLocations = new ModelManager.Builder(ModelStructure.FLOOR_MODEL, location.clone().add(x * cubeSize, 0, z * cubeSize)).build().createModel().getBlockLocations();
                        loadQueue.get(ModelStructure.FLOOR_MODEL.getPriority()).add(() -> floorLocations.forEach((blockLocation, material) -> blockLocation.getBlock().setType(material)));
                    }

                    ModelStructure modelType = map[x][y][z];
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

    private Map<Integer, Queue<Runnable>> generateEntranceQueue(ModelInterface[][][] entranceMap) {
        Map<Integer, Queue<Runnable>> loadQueue = new HashMap<>();
        for (int priority = 0; priority <= ModelStructure.values().length; priority++) loadQueue.put(priority, new ArrayDeque<>());

        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                for (int z = 0; z < maxDepth; z++) {
                    ModelInterface modelType = entranceMap[x][y][z];
                    if (modelType == null) continue;

                    HashMap<Location, Material> blockLocations = new ModelManager.Builder(modelType, location.clone().add(x * cubeSize, y * cubeSize, z * cubeSize)).build().createModel().getBlockLocations();

                    loadQueue.get(modelType.getPriority()).add(() -> blockLocations.forEach((blockLocation, material) -> blockLocation.getBlock().setType(material)));
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
