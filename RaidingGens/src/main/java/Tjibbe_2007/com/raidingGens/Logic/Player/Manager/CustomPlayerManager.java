package Tjibbe_2007.com.raidingGens.Logic.Player.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Player.Model.CustomPlayer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CustomPlayerManager {
    @Getter
    private static final HashMap<UUID, CustomPlayer> customPlayers = new HashMap<>();

    public void register(Player player) {
        player.stopAllSounds();

        customPlayers.putIfAbsent(player.getUniqueId(), new CustomPlayer(player.getUniqueId()));
        customPlayers.get(player.getUniqueId()).setPlayer(player);
    }

    public void add(UUID uuid, CustomPlayer customPlayer) {
        customPlayers.putIfAbsent(uuid, customPlayer);
        customPlayers.get(uuid).reloadPlayer();
    }
}
