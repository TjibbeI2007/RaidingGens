package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Model;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Config.DropConfig;

import java.util.List;

public class DropBuilder {
    private Integer tier;
    private String name;
    private List<String> lore;
    private Integer exp;
    private Float worth;

    public DropBuilder (Integer tier) {
        this.tier = tier;
        this.name = DropConfig.getName(tier);
        this.lore = DropConfig.getLore(tier);
        this.exp = DropConfig.getExp(name);
        this.worth = DropConfig.getWorth(name);
    }

    public DropBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DropBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public DropBuilder setExp(Integer exp) {
        this.exp = exp;
        return this;
    }

    public DropBuilder setWorth(Float worth) {
        this.worth = worth;
        return this;
    }

    public DropModel createDropModel() {
        return new DropModel(tier, name, lore, exp, worth);
    }
}