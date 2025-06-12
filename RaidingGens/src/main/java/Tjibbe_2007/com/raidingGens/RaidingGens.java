package Tjibbe_2007.com.raidingGens;

import Tjibbe_2007.com.raidingGens.Commands.GeneratorCommand;
import Tjibbe_2007.com.raidingGens.Commands.Map.MapCreateCommand;
import Tjibbe_2007.com.raidingGens.Listeners.BlockHandler;
import Tjibbe_2007.com.raidingGens.Listeners.ConnectionHandler;
import Tjibbe_2007.com.raidingGens.Logic.Player.repository.CustomPlayerRepository;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class RaidingGens extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockHandler(), this);
        getServer().getPluginManager().registerEvents(new ConnectionHandler(), this);

        this.getCommand("generator").setExecutor(new GeneratorCommand());
        this.getCommand("createmap").setExecutor(new MapCreateCommand(this));

        CustomPlayerRepository customPlayerRepository = new CustomPlayerRepository();
        customPlayerRepository.createRepository();
        if (!customPlayerRepository.loadRepository()) this.getLogger().severe("Failed to load player repository, some data may be lost!");

        this.getLogger().info("Successfully enabled");
    }

    @Override
    public void onDisable() {

        CustomPlayerRepository customPlayerRepository = new CustomPlayerRepository();
        customPlayerRepository.createRepository();
        customPlayerRepository.saveRepository();

        this.getLogger().info("Successfully disabled");
    }
}
