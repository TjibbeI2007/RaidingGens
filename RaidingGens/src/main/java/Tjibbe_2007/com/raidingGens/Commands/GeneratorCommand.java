package Tjibbe_2007.com.raidingGens.Commands;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilder;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GeneratorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            GeneratorConfig.getInstance().getMaterials().forEach(material -> player.getInventory().addItem(new GameItemBuilder(material).build().create()));

            player.sendMessage("§8[§a?§8] §aYou received a generator");
            return true;
        } else commandSender.sendMessage("§8[§c!§8] §cThis command can only be used by players.");
        return false;
    }
}
