package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class Generator {
    // Fields
    Integer tier;
    Material material;

    String name;
    List<String> lore;
    Float cost;
    Float exp;
    Float worth;
    Integer requirement;

    UUID owner;

    // Constructor
    public Generator(
            @NotNull Integer tier,
            @NotNull Material material,
            @NotNull String name,
            @NotNull List<String> lore,
            @NotNull Float cost,
            @NotNull Float exp,
            @NotNull Float worth,
            @NotNull Integer requirement
    ) {
        this.tier = tier;
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.cost = cost;
        this.exp = exp;
        this.worth = worth;
        this.requirement = requirement;
    }

    public ItemStack create() {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return item;
    }

    // Getters
    public Integer getTier() { return tier; }
    public Material getMaterial() { return material; }
    public UUID getOwner() { return owner; }
    public String getName() { return name; }
    public List<String> getLore() { return lore; }
    public Float getCost() { return cost; }
    public Float getExp() { return exp; }
    public Float getWorth() { return worth; }
    public Integer getRequirement() { return requirement; }

    // Setters
    public void setTier(Integer tier) { this.tier = tier; }
    public void setMaterial(Material material) { this.material = material; }
    public void setOwner(UUID owner) { this.owner = owner; }
    public void setName(String name) { this.name = name; }
    public void setLore(List<String> lore) { this.lore = lore; }
    public void setCost(Float cost) { this.cost = cost; }
    public void setExp(Float exp) { this.exp = exp; }
    public void setWorth(Float worth) { this.worth = worth; }
    public void setRequirement(Integer requirement) { this.requirement = requirement; }
}
