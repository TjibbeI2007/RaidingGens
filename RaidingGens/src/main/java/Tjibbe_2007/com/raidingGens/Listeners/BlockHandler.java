package Tjibbe_2007.com.raidingGens.Listeners;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager.GeneratorManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockHandler implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        new GeneratorManager().placeItemBlock(event);
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        new GeneratorManager().removeItemBlock(event);
    }
}
