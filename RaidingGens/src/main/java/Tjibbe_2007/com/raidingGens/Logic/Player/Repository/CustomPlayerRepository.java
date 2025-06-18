package Tjibbe_2007.com.raidingGens.Logic.Player.Repository;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorBuilder;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.Player.Manager.CustomPlayerManager;
import Tjibbe_2007.com.raidingGens.Logic.Player.Model.CustomPlayer;
import Tjibbe_2007.com.raidingGens.Logic.Utils.Repository.RepositoryInterface;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomPlayerRepository implements RepositoryInterface {
    private final File dataFile = new File("plugins/RaidingGens/Data/CustomPlayer.yml");
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

        HashMap<UUID, CustomPlayer> customPlayers = CustomPlayerManager.getCustomPlayers();
        dataConfig.set("customPlayers", null);

        customPlayers.forEach((uuid, customPlayer) -> {
            HashMap<String, Object> playerData = new HashMap<>();

            playerData.put("uuid", uuid.toString());
            playerData.put("level", customPlayer.getLevel());
            playerData.put("exp", customPlayer.getExp());
            playerData.put("mana", customPlayer.getMana());
            playerData.put("tokens", customPlayer.getTokens());
            playerData.put("maxGenerators", customPlayer.getMaxGenerators());

            HashMap<Integer, HashMap<String, Object>> generatorData = new HashMap<>();
            customPlayer.getPlacedGenerators().forEach((location, generatorModel) ->
                generatorData.put(generatorData.size(), new HashMap<>() {{
                    put("location", location.serialize());
                }})
            );
            playerData.put("placedGenerators", generatorData);

            dataConfig.set("customPlayers."+uuid, playerData);
        });

        try {
            dataConfig.save(dataFile);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean load() {
        create();

        ConfigurationSection playersSection = dataConfig.getConfigurationSection("customPlayers");
        if (playersSection == null) return false;

        for (String key : playersSection.getKeys(false)) {
            UUID uuid = UUID.fromString(key);
            ConfigurationSection playerData = playersSection.getConfigurationSection(key);
            if (playerData == null) continue;

            int level = playerData.getInt("level");
            float exp = (float) playerData.getDouble("exp");
            float mana = (float) playerData.getDouble("mana");
            float tokens = (float) playerData.getDouble("tokens");
            int maxGenerators = playerData.getInt("maxGenerators");

            HashMap<Location, GeneratorModel> placedGenerators = new HashMap<>();
            ConfigurationSection placedGeneratorsSection = playerData.getConfigurationSection("placedGenerators");
            if (placedGeneratorsSection != null) {
                for (String generatorKey : placedGeneratorsSection.getKeys(false)) {
                    ConfigurationSection generatorInfo = placedGeneratorsSection.getConfigurationSection(generatorKey);
                    if (generatorInfo == null) continue;

                    ConfigurationSection locationSection = generatorInfo.getConfigurationSection("location");
                    if (locationSection == null) continue;

                    Location location = Location.deserialize(locationSection.getValues(false));
                    Material material = location.getBlock().getType();
                    if (!GeneratorConfig.isValidMaterial(material)) continue;

                    GeneratorModel generatorModel = new GeneratorBuilder(material)
                            .setOwner(uuid)
                            .setLocation(location)
                            .build();

                    placedGenerators.put(location, generatorModel);
                }
            }

            CustomPlayer customPlayer = new CustomPlayer(uuid);
            customPlayer.setLevel(level);
            customPlayer.setExp(exp);
            customPlayer.setMana(mana);
            customPlayer.setTokens(tokens);
            customPlayer.setMaxGenerators(maxGenerators);
            customPlayer.setPlacedGenerators(placedGenerators);

            new CustomPlayerManager().add(uuid, customPlayer);
        }
        return true;
    }
}
