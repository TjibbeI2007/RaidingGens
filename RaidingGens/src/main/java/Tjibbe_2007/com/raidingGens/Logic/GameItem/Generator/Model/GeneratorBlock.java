package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model;

import org.bukkit.Material;

import java.util.UUID;

public class GeneratorBlock {
    Integer tier;
    UUID owner;

    public GeneratorBlock(Integer tier, UUID owner) {
        this.tier = tier;
        this.owner = owner;
    }

    public Integer getTier() { return tier; }
    public void setTier(Integer tier) { this.tier = tier; }

    public UUID getOwner() { return owner; }
    public void setOwner(UUID owner) { this.owner = owner; }
}
