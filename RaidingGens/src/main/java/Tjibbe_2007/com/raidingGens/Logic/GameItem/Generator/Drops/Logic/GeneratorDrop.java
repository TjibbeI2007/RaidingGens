package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Logic;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Model.DropBuilder;
import Tjibbe_2007.com.raidingGens.Logic.Player.Manager.CustomPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class GeneratorDrop {
    JavaPlugin plugin;
    public GeneratorDrop(JavaPlugin plugin) { this.plugin = plugin; }

    public void drop() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    UUID uuid = player.getUniqueId();

                    CustomPlayerManager.getCustomPlayers().get(uuid).getPlacedGenerators().forEach((location, generatorModel) -> {
                        new DropBuilder(generatorModel.getTier()).setWorth(generatorModel.getWorth()).setExp(generatorModel.getExp()).createDropModel().drop(location);
                    });
                }
            }
        }.runTaskTimer(plugin, 0, 20L);
    }
}
