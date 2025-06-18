package Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Model;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public class DefenseModel {
    // Fields
    private final Integer tier;
    private final Material material;
    private final String name;
    private final List<String> lore;
    private final Float cost;
    private final Integer requirement;

    private final UUID owner;
    private final Location location;

    // Constructor
    public DefenseModel(Integer tier, Material material, String name, java.util.List<String> lore, Float cost, Integer requirement, UUID owner, Location location) {
        this.tier = tier;
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.cost = cost;
        this.requirement = requirement;
        this.owner = owner;
        this.location = location;
    }

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
