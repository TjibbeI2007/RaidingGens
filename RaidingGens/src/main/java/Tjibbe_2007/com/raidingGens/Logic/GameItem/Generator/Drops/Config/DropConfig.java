package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Config;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import org.bukkit.Material;

import java.util.List;

public class DropConfig {
    private final GeneratorConfig generatorConfig = GeneratorConfig.getInstance();
    private final List<String> names = generatorConfig.getNames().stream()
            .map(name -> name.replace("Generator", "Drop"))
            .toList();
    private final List<Material> materials = generatorConfig.getMaterials();


    private final List<List<String>> loreList = names.stream()
        .map(name -> List.of(
                "§7This is a drop",
                "§7sell to make money",
                "",
                "§8» §7Note Worth: §f" + generatorConfig.getWorth(materials.get(getTier(name)-1)),
                "§8» §7Note Exp: §f" + generatorConfig.getExp(materials.get(getTier(name)-1)),
                "§8» §7Tier: §f" + generatorConfig.getTier(materials.get(getTier(name)-1)),
                "",
                "§8§o(Right click to sell)"
        ))
        .toList();

    public static DropConfig getInstance() { return new DropConfig(); }
    public static long getDelay() { return 60L; }
    public int getExp(String name) { return generatorConfig.getExp(materials.get(names.indexOf(name))); }
    public float getWorth(String name) { return generatorConfig.getWorth(materials.get(names.indexOf(name))); }
    public int getTier(String name) { return generatorConfig.getTier((materials.get(names.indexOf(name)))); }
    public String getName(int tier) { return names.get(tier - 1); }
    public List<String> getLore(int tier) { return loreList.get(tier - 1); }
}
