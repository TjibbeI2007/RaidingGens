package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.List;
import java.util.UUID;

public class GeneratorBuilder {
    private Integer tier;
    private Material material;
    private String name;
    private List<String> lore;
    private Float cost;
    private Integer exp;
    private Float worth;
    private Integer requirement;
    private UUID owner;
    private Location location;

    public GeneratorBuilder(Material material) {
        this.material = material;
        this.tier = GeneratorConfig.getTier(material);
        this.name = GeneratorConfig.getName(material);
        this.lore = GeneratorConfig.getLore(material);
        this.cost = GeneratorConfig.getCost(material);
        this.exp = GeneratorConfig.getExp(material);
        this.worth = GeneratorConfig.getWorth(material);
        this.requirement = GeneratorConfig.getRequirement(material);
    }

    public GeneratorBuilder setOwner(UUID owner) { this.owner = owner; return this; }
    public GeneratorBuilder setLocation(Location location) { this.location = location; return this; }

    public GeneratorModel build() { return new GeneratorModel(tier, material, name, lore, cost, exp, worth, requirement, owner, location); }
}