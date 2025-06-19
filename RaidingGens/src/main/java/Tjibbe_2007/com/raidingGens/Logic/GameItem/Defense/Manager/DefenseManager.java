package Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Config.DefenseConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Model.DefenseModel;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Manager.GameItemManagerInterface;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilder;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilderInterface;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.Player.Manager.CustomPlayerManager;
import Tjibbe_2007.com.raidingGens.Logic.Player.Model.CustomPlayer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class DefenseManager implements GameItemManagerInterface {
    @Getter  @Setter
    private static HashMap<Location, GameItemBuilderInterface> defense = new HashMap<>();

    public void place(BlockPlaceEvent event) {
        // Inits the variables
        Block block = event.getBlock();
        Material material = block.getType();

        // Skip if the material is not valid for defense
        if (!DefenseConfig.getInstance().isValidMaterial(material)) { return; }

        // Inits the variables
        Location location = block.getLocation();
        CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayers().get(event.getPlayer().getUniqueId());
        Player player = customPlayer.getPlayer();

        // Replaces the sound
        player.stopSound(SoundCategory.BLOCKS);
        player.playSound(location, Sound.BLOCK_ANCIENT_DEBRIS_PLACE,1.0f,0.1f);

        // Gets the defense model and adds it to the player and global defense
        GameItemBuilderInterface defenseModel = new GameItemBuilder(material).setLocation(location).setOwner(customPlayer.getUuid()).build();
        customPlayer.addPlacedDefense(location, defenseModel);
        defense.put(block.getLocation(), defenseModel);
    }

    public void remove(BlockBreakEvent event) {
        // Inits the variables
        Block block = event.getBlock();
        Material material = block.getType();

        // Skip if the material is not valid for defense
        if (!DefenseConfig.getInstance().isValidMaterial(material)) { return; }

        // Inits the variables
        CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayers().get(event.getPlayer().getUniqueId());
        Player player = customPlayer.getPlayer();
        Location location = block.getLocation();

        // Check if the player is the owner
        if (!((DefenseModel) defense.get(location)).owner().equals(customPlayer.getUuid())) {
            // Play a sound
            player.stopSound(SoundCategory.BLOCKS);
            player.playSound(location, Sound.BLOCK_VAULT_BREAK,1.0f,0.1f);

            // Send a message and cancel
            player.sendMessage("§8[§c!§8] §cThis is not your defense!");
            event.setCancelled(true);
            return;
        }

        // Replace the sound
        player.stopSound(SoundCategory.BLOCKS);
        player.playSound(location, Sound.BLOCK_LODESTONE_BREAK,1.0f,0.1f);

        // Removes the drops
        event.setDropItems(false);

        // Gets de defense model and removes it
        customPlayer.removePlacedDefense(location);
        GameItemBuilderInterface generator = defense.get(block.getLocation());
        defense.remove(block.getLocation());

        // Get the tool and the silk touch level
        ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
        int silkTouchLevel = tool.getEnchantmentLevel(Enchantment.SILK_TOUCH);

        // Checks if player has the chance to get the item back
        if (!(Math.random() <= silkTouchLevel * DefenseConfig.getInstance().getSilkTouchChance())) { return; }

        // Play sound
        player.playSound(location, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0f, 1f);

        // Creates the item and adds it to the player's inventory
        ItemStack itemStack = generator.create();
        customPlayer.getPlayer().getInventory().addItem(itemStack);
    }

    public static DefenseManager getInstance() { return new DefenseManager(); }
}
