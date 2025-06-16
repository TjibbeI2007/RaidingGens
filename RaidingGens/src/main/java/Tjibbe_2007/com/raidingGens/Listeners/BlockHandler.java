package Tjibbe_2007.com.raidingGens.Listeners;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager.GeneratorManager;
import Tjibbe_2007.com.raidingGens.Logic.Player.Manager.CustomPlayerManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockHandler implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();

        GeneratorManager.place(
                CustomPlayerManager.getCustomPlayers().get(player.getUniqueId()),
                block
        );
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        
        GeneratorManager.remove(
                CustomPlayerManager.getCustomPlayers().get(player.getUniqueId()),
                block
        );
    }
}
