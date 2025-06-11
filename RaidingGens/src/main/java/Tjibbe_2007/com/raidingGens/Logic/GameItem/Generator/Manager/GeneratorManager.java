package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.Player.Model.CustomPlayer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GeneratorManager {
    private static final HashMap<Location, GeneratorModel> generators = new HashMap<>();

    public void placeItemBlock(CustomPlayer customPlayer, Block block) {
        Material material = block.getType();

        if (GeneratorConfig.isValidMaterial(material)) {
                GeneratorModel generatorModel = new GeneratorModel(
                        GeneratorConfig.getTier(material),
                        material,
                        GeneratorConfig.getName(material),
                        GeneratorConfig.getLore(material),
                        GeneratorConfig.getCost(material),
                        GeneratorConfig.getExp(material),
                        GeneratorConfig.getWorth(material),
                        GeneratorConfig.getRequirement(material),
                        customPlayer.getUuid()
                );

                generators.put(block.getLocation(), generatorModel);
        }
    }

    public void removeItemBlock(CustomPlayer customPlayer, Block block) {
        Material material = block.getType();

        if (GeneratorConfig.isValidMaterial(material)) {
            GeneratorModel generator = generators.get(block.getLocation());
            generators.remove(block.getLocation());

            ItemStack itemStack = generator.create();
            customPlayer.getPlayer().getInventory().addItem(itemStack);
        }
    }

    public static HashMap<Location, GeneratorModel> getGenerators() { return generators; }
}
