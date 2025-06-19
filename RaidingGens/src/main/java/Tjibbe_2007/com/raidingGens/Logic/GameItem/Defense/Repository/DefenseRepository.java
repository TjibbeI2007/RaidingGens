package Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Repository;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Config.DefenseConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Manager.DefenseManager;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Model.DefenseModel;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilder;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilderInterface;
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

public class DefenseRepository implements RepositoryInterface {
    private final File dataFile = new File("plugins/RaidingGens/Data/GameBlocks/PlacedDefense.yml");
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

        HashMap<Location, GameItemBuilderInterface> defense = DefenseManager.getDefense();
        HashMap<Integer, HashMap<String, Object>> defenseData = new HashMap<>();

        defense.forEach(((location, gameItemModel) -> {
            HashMap<String, Object> defenseInfo = new HashMap<>();

            defenseInfo.put("owner", ((DefenseModel) gameItemModel).owner().toString());
            defenseInfo.put("location", location.serialize());

            defenseData.put(defenseData.size(), defenseInfo);
        }));

        try {
            dataConfig.set("defense", defenseData);
            dataConfig.save(dataFile);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean load() {
        create();

        ConfigurationSection playersSection = dataConfig.getConfigurationSection("defense");
        if (playersSection == null) return false;
        HashMap<Location, GameItemBuilderInterface> defense = new HashMap<>();

        for (String key : playersSection.getKeys(false)) {
            ConfigurationSection defenseInfo = playersSection.getConfigurationSection(key);
            if (defenseInfo == null) continue;

            ConfigurationSection locationSection = defenseInfo.getConfigurationSection("location");
            if (locationSection == null) continue;

            String ownerString = defenseInfo.getString("owner");
            if (ownerString == null || ownerString.isEmpty()) continue;
            UUID owner = UUID.fromString(ownerString);

            Location location = Location.deserialize(locationSection.getValues(false));
            Material material = location.getBlock().getType();
            if (!DefenseConfig.getInstance().isValidMaterial(material)) continue;

            defense.put(location, new GameItemBuilder(material)
                            .setOwner(owner)
                            .build());
        }

        DefenseManager.setDefense(defense);
        return true;
    }
}
