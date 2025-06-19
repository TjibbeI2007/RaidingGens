package Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Config.DefenseConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Manager.DefenseManager;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager.GeneratorManager;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class GameItemManager implements GameItemManagerInterface {
    @Getter
    private static final GameItemManager instance = new GameItemManager();

    @Override
    public void place(BlockPlaceEvent event) {
        Material material = event.getBlockPlaced().getType();
        if (GeneratorConfig.getInstance().isValidMaterial(material)) GeneratorManager.getInstance().place(event);
        else if (DefenseConfig.getInstance().isValidMaterial(material)) DefenseManager.getInstance().place(event);
    }

    @Override
    public void remove(BlockBreakEvent event) {
        Material material = event.getBlock().getType();
        if (GeneratorConfig.getInstance().isValidMaterial(material)) GeneratorManager.getInstance().remove(event);
        else if (DefenseConfig.getInstance().isValidMaterial(material)) DefenseManager.getInstance().remove(event);
    }
}
