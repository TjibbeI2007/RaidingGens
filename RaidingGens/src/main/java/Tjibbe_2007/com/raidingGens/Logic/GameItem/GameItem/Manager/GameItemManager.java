package Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager.GeneratorManager;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class GameItemManager implements GameItemManagerInterface {
    @Override
    public void place(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
        if (GeneratorConfig.getInstance().isValidMaterial(material)) GeneratorManager.getInstance().place(event);
    }

    @Override
    public void remove(BlockBreakEvent event) {
        Material material = event.getBlock().getType();
        if (GeneratorConfig.getInstance().isValidMaterial(material)) GeneratorManager.getInstance().remove(event);
    }
}
