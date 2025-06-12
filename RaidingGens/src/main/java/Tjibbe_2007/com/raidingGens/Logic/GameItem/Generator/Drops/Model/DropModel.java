package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Model;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter
public class DropModel {
    // Fields
    private final Integer tier;
    private final String name;
    private final List<String> lore;
    private final Integer exp;
    private final Float worth;

    // Constructor
    public DropModel(Integer tier, String name, List<String> lore, Integer exp, Float worth) {
        this.tier = tier;
        this.name = name;
        this.lore = lore;
        this.exp = exp;
        this.worth = worth;
    }
    
    public ItemStack create() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return item;
    }
}
