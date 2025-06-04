package Tjibbe_2007.com.raidingGens;

import Tjibbe_2007.com.raidingGens.Commands.GeneratorCommand;
import Tjibbe_2007.com.raidingGens.Commands.Map.MapCreateCommand;
import Tjibbe_2007.com.raidingGens.Listeners.BlockHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class RaidingGens extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockHandler(), this);

        this.getCommand("generator").setExecutor(new GeneratorCommand());
        this.getCommand("createmap").setExecutor(new MapCreateCommand(this));

        this.getLogger().info("Successfully enabled");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Successfully disabled");
    }
}
