package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GeneratorManager {
    private static HashMap<Location, GeneratorModel> generators = new HashMap<>();

    public void placeItemBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
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
                        player.getUniqueId()
                );

                generators.put(block.getLocation(), generatorModel);
        }
    }

    public void removeItemBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material material = block.getType();

        if (GeneratorConfig.isValidMaterial(material)) {
            GeneratorModel generator = generators.get(block.getLocation());

            ItemStack itemStack = generator.create();
            player.getInventory().addItem(itemStack);
        }
    }
}
