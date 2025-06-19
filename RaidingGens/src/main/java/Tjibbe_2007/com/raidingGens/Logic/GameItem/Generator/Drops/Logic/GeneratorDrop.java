package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Logic;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Model.DropBuilder;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
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

                    CustomPlayerManager.getCustomPlayers().get(uuid).getPlacedGenerators().forEach((location, gameItemModel) -> {
                        GeneratorModel generatorModel = (GeneratorModel) gameItemModel;
                        new DropBuilder(generatorModel.tier()).setWorth(generatorModel.worth()).setExp(generatorModel.exp()).createDropModel().drop(location);
                    });
                }
            }
        }.runTaskTimer(plugin, 0, 20L);
    }
}
