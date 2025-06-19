package Tjibbe_2007.com.raidingGens.Listeners;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Manager.GameItemManager;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Manager.GameItemManagerInterface;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
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
        GameItemManager.getInstance().place(event);
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        GameItemManager.getInstance().remove(event);
    }
}
