package Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Manager;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public interface GameItemManagerInterface {
    void place(BlockPlaceEvent event);
    void remove(BlockBreakEvent event);
}
