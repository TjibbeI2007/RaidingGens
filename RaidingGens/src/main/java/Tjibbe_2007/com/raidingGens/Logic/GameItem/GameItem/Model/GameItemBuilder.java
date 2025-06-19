package Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Config.DefenseConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Model.DefenseModel;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Config.GameItemConfigInterface;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class GameItemBuilder {
    private final Integer tier;
    private final Material material;
    private final String name;
    private final List<String> lore;
    private final Float cost;
    private final Integer exp;
    private final Float worth;
    private final Integer requirement;
    private UUID owner;
    private Location location;
    private GameItemConfigInterface gameItemConfig;

    public GameItemBuilder(Material material) {
        this.material = material;
        if (GeneratorConfig.getInstance().isValidMaterial(material)) this.gameItemConfig = GeneratorConfig.getInstance();
        else if (DefenseConfig.getInstance().isValidMaterial(material)) this.gameItemConfig = DefenseConfig.getInstance();
        this.tier = gameItemConfig.getTier(material);
        this.name = gameItemConfig.getName(material);
        this.lore = gameItemConfig.getLore(material);
        this.cost = gameItemConfig.getCost(material);
        this.exp = gameItemConfig.getExp(material);
        this.worth = gameItemConfig.getWorth(material);
        this.requirement = gameItemConfig.getRequirement(material);
    }

    public GameItemBuilder setOwner(UUID owner) { this.owner = owner; return this; }
    public GameItemBuilder setLocation(Location location) { this.location = location; return this; }

    public GameItemBuilderInterface build() {
        if (gameItemConfig instanceof GeneratorConfig) return new GeneratorModel(tier, material, name, lore, cost, exp, worth, requirement, owner, location);
        else if (gameItemConfig instanceof DefenseConfig) return new DefenseModel(tier, material, name, lore, cost, requirement, owner, location);
        throw new IllegalStateException("Unsupported GameItemConfig type: " + gameItemConfig.getClass().getName());
    }
}
