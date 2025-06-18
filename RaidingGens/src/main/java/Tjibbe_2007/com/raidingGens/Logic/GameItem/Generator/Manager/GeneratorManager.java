package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorBuilder;
import Tjibbe_2007.com.raidingGens.Logic.Player.Manager.CustomPlayerManager;
import Tjibbe_2007.com.raidingGens.Logic.Player.Model.CustomPlayer;
import Tjibbe_2007.com.raidingGens.Logic.Utils.Style;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;

public class GeneratorManager {
    @Getter
    @Setter
    private static HashMap<Location, GeneratorModel> generators = new HashMap<>();

    public static void place(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        Location location = block.getLocation();
        CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayers().get(event.getPlayer().getUniqueId());
        Player player = customPlayer.getPlayer();

        if (GeneratorConfig.isValidMaterial(material)) {
            if (customPlayer.getPlacedGenerators().size() >= customPlayer.getMaxGenerators()) {
                player.stopSound(SoundCategory.BLOCKS);
                player.playSound(location, Sound.BLOCK_VAULT_BREAK,1.0f,0.1f);
                player.sendTitle(
                        String.format("%s%s§8/%s%s", Style.VALUE_COLOUR, customPlayer.getPlacedGenerators().size(), Style.VALUE_COLOUR, customPlayer.getMaxGenerators()),
                        String.format("§8[§c!§8]§cYou have reached the maximum generators!"),
                        0, 15, 5
                        );
                event.setCancelled(true);
                return;
            }
            player.stopSound(SoundCategory.BLOCKS);
            player.playSound(location, Sound.BLOCK_VAULT_ACTIVATE,1.0f,0.1f);

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
