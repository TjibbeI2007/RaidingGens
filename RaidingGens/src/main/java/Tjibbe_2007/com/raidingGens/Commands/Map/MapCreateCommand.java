package Tjibbe_2007.com.raidingGens.Commands.Map;

import Tjibbe_2007.com.raidingGens.Logic.Map.Logic.TestManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MapCreateCommand implements CommandExecutor {
    JavaPlugin plugin;
    public MapCreateCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            new TestManager(plugin, player.getLocation()).loadMap();
//            Location location = player.getLocation();
//
//            MapLoadManager mapLoadManager = new MapLoadManager(plugin, location);
//            mapLoadManager.create();
//            mapLoadManager.loadQueue();
            return true;
        } return false;
    }
}
