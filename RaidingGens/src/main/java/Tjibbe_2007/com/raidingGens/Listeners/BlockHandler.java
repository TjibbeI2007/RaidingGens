package Tjibbe_2007.com.raidingGens.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockHandler implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        System.out.println("Block broken: " + event.getBlock().getType());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        System.out.println("Block placed: " + event.getBlock().getType());
    }
}
