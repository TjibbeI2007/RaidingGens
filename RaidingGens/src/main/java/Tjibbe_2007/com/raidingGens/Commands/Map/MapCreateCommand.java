package Tjibbe_2007.com.raidingGens.Commands.Map;

import Tjibbe_2007.com.raidingGens.Logic.Map.Manager.MapLoadManager;
import Tjibbe_2007.com.raidingGens.Logic.Map.Manager.MapPlaceManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MapCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            Location location = player.getLocation();
            MapLoadManager mapLoadManager = new MapLoadManager(location);
            mapLoadManager.create();
            mapLoadManager.loadQueue();
            return true;
        } return false;
    }
}
