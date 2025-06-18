package Tjibbe_2007.com.raidingGens.Listeners;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager.GeneratorManager;
import Tjibbe_2007.com.raidingGens.Logic.Player.Manager.CustomPlayerManager;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockHandler implements Listener {
    JavaPlugin javaPlugin;
    public BlockHandler(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        Location location = block.getLocation();

        GeneratorManager.place(event);
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = block.getLocation();

        if (GeneratorConfig.isValidMaterial(block.getType())) {
            player.stopSound(SoundCategory.BLOCKS);
            player.playSound(location, Sound.BLOCK_VAULT_BREAK,1.0f,0.1f);
        }

        GeneratorManager.remove(CustomPlayerManager.getCustomPlayers().get(player.getUniqueId()), block);
    }
}
