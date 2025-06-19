package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Manager.GameItemManagerInterface;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilder;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilderInterface;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GeneratorManager implements GameItemManagerInterface {
    @Getter @Setter
    private static HashMap<Location, GameItemBuilderInterface> generators = new HashMap<>();

    public void place(BlockPlaceEvent event) {
        // Init variabels
        Block block = event.getBlock();
        Material material = block.getType();

        // Skip if it's not a generator
        if (!GeneratorConfig.getInstance().isValidMaterial(material)) { return; }

        // Init variabels
        Location location = block.getLocation();
        CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayers().get(event.getPlayer().getUniqueId());
        Player player = customPlayer.getPlayer();

        // Check if the player has reached the maximum generators
        if (customPlayer.getPlacedGenerators().size() >= customPlayer.getMaxGenerators()) {
            // Play a sound
            player.stopSound(SoundCategory.BLOCKS);
            player.playSound(location, Sound.BLOCK_VAULT_BREAK,1.0f,0.1f);

            // Send a title message
            player.sendTitle(
                    String.format("%s%s§8/%s%s", Style.VALUE_COLOUR, customPlayer.getPlacedGenerators().size(), Style.VALUE_COLOUR, customPlayer.getMaxGenerators()),
                    String.format("§8[§c!§8]§cYou have reached the maximum generators!"),
                    0, 15, 5
                );
            event.setCancelled(true);
            return;
        }

        // Replace the sound
        player.stopSound(SoundCategory.BLOCKS);
        player.playSound(location, Sound.BLOCK_VAULT_ACTIVATE,1.0f,0.1f);

        // Create the generator model and add it to the player and global generators
        GameItemBuilderInterface generatorModel = new GameItemBuilder(material).setLocation(location).setOwner(customPlayer.getUuid()).build();
        customPlayer.addPlacedGenerators(location, generatorModel);
        generators.put(block.getLocation(), generatorModel);
    }

    public void remove(BlockBreakEvent event) {
        // Init variables
        Block block = event.getBlock();
        Material material = block.getType();

        // Skip if it's not a generator
        if (!GeneratorConfig.getInstance().isValidMaterial(material)) { return; }

        // Init variables
        CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayers().get(event.getPlayer().getUniqueId());
        Location location = block.getLocation();

        // Create the model and remove it from the player and global generators
        customPlayer.removePlacedGenerators(location);
        GameItemBuilderInterface generator = generators.get(block.getLocation());
        generators.remove(block.getLocation());

        // Give the item to the player
        ItemStack itemStack = generator.create();
        customPlayer.getPlayer().getInventory().addItem(itemStack);
    }

    public static GeneratorManager getInstance() { return new GeneratorManager(); }
}
