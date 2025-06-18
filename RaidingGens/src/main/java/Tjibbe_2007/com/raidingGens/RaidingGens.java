package Tjibbe_2007.com.raidingGens;

import Tjibbe_2007.com.raidingGens.Commands.GeneratorCommand;
import Tjibbe_2007.com.raidingGens.Commands.Map.MapCreateCommand;
import Tjibbe_2007.com.raidingGens.Listeners.BlockHandler;
import Tjibbe_2007.com.raidingGens.Listeners.ConnectionHandler;
import Tjibbe_2007.com.raidingGens.Listeners.InteractionHandler;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Logic.GeneratorDrop;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Repository.GeneratorRepository;
import Tjibbe_2007.com.raidingGens.Logic.Player.Repository.CustomPlayerRepository;
import Tjibbe_2007.com.raidingGens.Logic.Utils.Repository.RepositoryInterface;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class RaidingGens extends JavaPlugin {
    private final List<RepositoryInterface> repositories = List.of(
        new CustomPlayerRepository(),
        new GeneratorRepository()
    );

    @Override
    public void onEnable() {
        registerEvents();
        registerCommands();
        Bukkit.getScheduler().runTaskLater(this, this::loadRepositories, 10L);
        registerClasses();
        getLogger().info("Plugin enabled successfully.");
    }

    @Override
    public void onDisable() {
        saveRepositories();
        getLogger().info("Plugin disabled successfully.");
    }

    private void registerClasses() {
        new GeneratorDrop(this).drop();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new BlockHandler(this), this);
        getServer().getPluginManager().registerEvents(new ConnectionHandler(), this);
        getServer().getPluginManager().registerEvents(new InteractionHandler(), this);
    }

    private void registerCommands() {
        getCommand("generator").setExecutor(new GeneratorCommand());
        getCommand("createmap").setExecutor(new MapCreateCommand(this));
    }

    private void loadRepositories() {
        for (RepositoryInterface repository : repositories) {
            String name = repository.getClass().getSimpleName();
            if (repository.load()) {
                getLogger().info("Successfully loaded: " + name);
            } else getLogger().warning("Failed to load: " + name);
        } getLogger().info("All repositories loaded.");
    }

    private void saveRepositories() {
        for (RepositoryInterface repository : repositories) {
            String name = repository.getClass().getSimpleName();
            if (repository.save()) {
                getLogger().info("Successfully saved: " + name);
            } else getLogger().warning("Failed to save: " + name);
        } getLogger().info("All repositories saved.");
    }
}
