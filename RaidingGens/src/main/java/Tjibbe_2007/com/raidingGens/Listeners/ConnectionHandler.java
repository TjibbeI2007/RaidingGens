package Tjibbe_2007.com.raidingGens.Listeners;

import Tjibbe_2007.com.raidingGens.Logic.Player.Manager.CustomPlayerManager;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionHandler implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.stopAllSounds();

        CustomPlayerManager customPlayerManager = new CustomPlayerManager();
        customPlayerManager.register(player);
    }
}
