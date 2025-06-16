package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorBuilder;
import Tjibbe_2007.com.raidingGens.Logic.Player.Model.CustomPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GeneratorManager {
    @Getter
    @Setter
    private static HashMap<Location, GeneratorModel> generators = new HashMap<>();

    public static void place(CustomPlayer customPlayer, Block block) {
        Material material = block.getType();
        Location location = block.getLocation();

        if (GeneratorConfig.isValidMaterial(material)) {
            GeneratorModel generatorModel = new GeneratorBuilder(material).setLocation(location).setOwner(customPlayer.getUuid()).build();

            customPlayer.addPlacedGenerators(location, generatorModel);
            generators.put(block.getLocation(), generatorModel);
        }
    }

    public static void remove(CustomPlayer customPlayer, Block block) {
        Material material = block.getType();
        Location location = block.getLocation();

        if (GeneratorConfig.isValidMaterial(material)) {
            customPlayer.removePlacedGenerators(location);
            GeneratorModel generator = generators.get(block.getLocation());
            generators.remove(block.getLocation());

            ItemStack itemStack = generator.create();
            customPlayer.getPlayer().getInventory().addItem(itemStack);
        }
    }
}
