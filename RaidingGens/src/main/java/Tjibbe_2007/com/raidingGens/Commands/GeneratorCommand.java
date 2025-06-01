package Tjibbe_2007.com.raidingGens.Commands;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.Generator;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GeneratorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            player.getInventory().addItem(new Generator(
                1,
                Material.WHITE_STAINED_GLASS,
                player.getUniqueId(),
                "§aGenerator",
                List.of("§7This is a generator", "§7that generates resources"),
                100.0f,
                10.0f,
                50.0f,
                1
            ).create());

            player.sendMessage("§8[§a?§8] §aYou received a generator");
            return true;
        } else commandSender.sendMessage("§8[§c!§8] §cThis command can only be used by players.");
        return false;
    }
}
