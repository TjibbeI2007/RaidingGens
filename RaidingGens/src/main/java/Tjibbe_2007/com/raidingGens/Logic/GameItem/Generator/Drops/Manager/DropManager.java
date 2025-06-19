package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Manager;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Config.DropConfig;
import Tjibbe_2007.com.raidingGens.Logic.Player.Manager.CustomPlayerManager;
import Tjibbe_2007.com.raidingGens.Logic.Player.Model.CustomPlayer;
import Tjibbe_2007.com.raidingGens.Logic.Utils.Style;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class DropManager {
    public static void sell(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if (item == null ||
            (action != Action.RIGHT_CLICK_BLOCK && action != Action.RIGHT_CLICK_AIR) ||
            item.getType() != Material.PAPER) return;

        float balance = 0, exp = 0;
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();
        CustomPlayer customPlayer = CustomPlayerManager.getCustomPlayers().get(player.getUniqueId());
        Map<Integer, ? extends ItemStack> notes = inventory.all(Material.PAPER);

        for (ItemStack itemStack : notes.values()) {
            int tier = DropConfig.getInstance().getTier(itemStack.getItemMeta().getDisplayName());

            GeneratorConfig instance = GeneratorConfig.getInstance();
            Material material = instance.getMaterial(tier);
            balance += instance.getWorth(material)*itemStack.getAmount();
            exp += instance.getExp(material)*itemStack.getAmount();

            inventory.removeItem(itemStack);
        }

        customPlayer.addExp(exp);
        customPlayer.addTokens(balance);
        customPlayer.levelUp();

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.format("§7+%s%.1f%s §8| §7+%s%.1f%s", Style.VALUE_COLOUR, balance, Style.BALANCE_STYLE, Style.VALUE_COLOUR, exp, Style.EXP_STYLE)));
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_PLACE, 10.0f, 0.1f);
    }
}
