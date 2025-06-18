package Tjibbe_2007.com.raidingGens.Listeners;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Manager.DropManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractionHandler implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        DropManager.sell(event);
    }
}
