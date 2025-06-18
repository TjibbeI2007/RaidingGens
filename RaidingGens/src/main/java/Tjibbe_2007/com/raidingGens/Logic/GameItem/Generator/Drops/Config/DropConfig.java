package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Config;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;
import org.bukkit.Material;

import java.util.List;

public class DropConfig {
    private static final List<String> names = GeneratorConfig.getNames().stream()
            .map(name -> name.replace("Generator", "Drop"))
            .toList();
    private static final List<Material> materials = GeneratorConfig.getMaterials();


    private static final List<List<String>> loreList = names.stream()
        .map(name -> List.of(
                "§7This is a drop",
                "§7sell to make money",
                "",
                "§8» §7Note Worth: §f" + GeneratorConfig.getWorth(materials.get(getTier(name)-1)),
                "§8» §7Note Exp: §f" + GeneratorConfig.getExp(materials.get(getTier(name)-1)),
                "§8» §7Tier: §f" + GeneratorConfig.getTier(materials.get(getTier(name)-1)),
                "",
                "§8§o(Right click to sell)"
        ))
        .toList();

    public static int getExp(String name) { return GeneratorConfig.getExp(materials.get(names.indexOf(name))); }
    public static float getWorth(String name) { return GeneratorConfig.getWorth(materials.get(names.indexOf(name))); }
    public static int getTier(String name) { return GeneratorConfig.getTier((materials.get(names.indexOf(name)))); }
    public static String getName(int tier) { return names.get(tier - 1); }
    public static List<String> getLore(int tier) { return loreList.get(tier - 1); }
}
