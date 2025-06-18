package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Repository;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilder;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager.GeneratorManager;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.Utils.Repository.RepositoryInterface;
import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class GeneratorRepository implements RepositoryInterface {
    private final File dataFile = new File("plugins/RaidingGens/Data/GameBlocks/PlacedGenerators.yml");
    private FileConfiguration dataConfig;

    @SneakyThrows
    public void create() {
        File folder = dataFile.getParentFile();
        if (!folder.exists()) folder.mkdirs();
        if (!dataFile.exists()) dataFile.createNewFile();

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public boolean save() {
        create();

        HashMap<Location, GeneratorModel> generators = GeneratorManager.getGenerators();
        HashMap<Integer, HashMap<String, Object>> generatorData = new HashMap<>();

        generators.forEach(((location, generatorModel) -> {
            HashMap<String, Object> generatorInfo = new HashMap<>();

            generatorInfo.put("owner", generatorModel.owner().toString());
            generatorInfo.put("location", location.serialize());

            generatorData.put(generatorData.size(), generatorInfo);
        }));

        try {
            dataConfig.set("generators", generatorData);
            dataConfig.save(dataFile);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean load() {
        create();

        ConfigurationSection playersSection = dataConfig.getConfigurationSection("generators");
        if (playersSection == null) return false;
        HashMap<Location, GeneratorModel> generators = new HashMap<>();

        for (String key : playersSection.getKeys(false)) {
            ConfigurationSection generatorInfo = playersSection.getConfigurationSection(key);
            if (generatorInfo == null) continue;

            ConfigurationSection locationSection = generatorInfo.getConfigurationSection("location");
            if (locationSection == null) continue;

            String ownerString = generatorInfo.getString("owner");
            if (ownerString == null || ownerString.isEmpty()) continue;
            UUID owner = UUID.fromString(ownerString);

            Location location = Location.deserialize(locationSection.getValues(false));
            Material material = location.getBlock().getType();
            if (!GeneratorConfig.getInstance().isValidMaterial(material)) continue;

            generators.put(location, (GeneratorModel) new GameItemBuilder(material)
                            .setOwner(owner)
                            .build());
        }

        GeneratorManager.setGenerators(generators);
        return true;
    }
}
