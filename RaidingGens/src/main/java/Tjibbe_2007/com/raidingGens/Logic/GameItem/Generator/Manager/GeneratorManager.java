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

    public void place(CustomPlayer customPlayer, Block block) {
        Material material = block.getType();
        Location location = block.getLocation();

        if (GeneratorConfig.isValidMaterial(material)) {
            GeneratorModel generatorModel = new GeneratorBuilder(material).setLocation(location).setOwner(customPlayer.getUuid()).build();
            generators.put(block.getLocation(), generatorModel);
        }
    }

    public void remove(CustomPlayer customPlayer, Block block) {
        Material material = block.getType();

        if (GeneratorConfig.isValidMaterial(material)) {
            GeneratorModel generator = generators.get(block.getLocation());
            generators.remove(block.getLocation());

            ItemStack itemStack = generator.create();
            customPlayer.getPlayer().getInventory().addItem(itemStack);
        }
    }
}
