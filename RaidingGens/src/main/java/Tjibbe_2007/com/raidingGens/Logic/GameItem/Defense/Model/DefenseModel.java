package Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Model;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilderInterface;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public record DefenseModel(int tier, Material material, String name, List<String> lore, Float cost, Integer requirement, UUID owner, Location location) implements GameItemBuilderInterface {
    public ItemStack create() {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return item;
    }
}