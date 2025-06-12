package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Drops.Config;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config.GeneratorConfig;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;

public class DropConfig {
    private static final List<String> names = GeneratorConfig.getNames().stream()
            .map(name -> name.replace("Generator", "Drop"))
            .toList();
    private static final List<Material> materials = GeneratorConfig.getMaterials();


    private static final HashMap<String, List<String>> loreMap = names.stream()
            .collect(Collectors.toMap(name -> name,
                name -> List.of(
                    "§7This is a drop",
                    "§7sell to make money",
                    "",
                    "§8» §7Note Worth: §f" + GeneratorConfig.getWorth(materials.get(getTier(name)-1)),
                    "§8» §7Note Exp: §f" + GeneratorConfig.getExp(materials.get(getTier(name)-1)),
                    "§8» §7Tier: §f" + GeneratorConfig.getTier(materials.get(getTier(name)-1)),
                    "",
                    "§8§o(Right click to sell)"
                ),
                (a, b) -> b, HashMap::new
            ));

    public static int getTier(String name) { return materials.indexOf(Material.getMaterial(name)) + 1;}
    public static String getName(String name) { return names.get(getTier(name) - 1); }
    public static List<String> getLore(String name) { return loreMap.getOrDefault(name, List.of()); }
}
