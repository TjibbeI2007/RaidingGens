package Tjibbe_2007.com.raidingGens.Commands;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.Player.repository.CustomPlayerRepository;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GeneratorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            GeneratorConfig.getMaterials().forEach(material -> {
                player.getInventory().addItem(new GeneratorModel(
                    GeneratorConfig.getTier(material),
                    material,
                    GeneratorConfig.getName(material),
                    GeneratorConfig.getLore(material),
                    GeneratorConfig.getCost(material),
                    GeneratorConfig.getExp(material),
                    GeneratorConfig.getWorth(material),
                    GeneratorConfig.getRequirement(material)
                ).create());
            });

            player.sendMessage("§8[§a?§8] §aYou received a generator");
            return true;
        } else commandSender.sendMessage("§8[§c!§8] §cThis command can only be used by players.");
        return false;
    }
}
