package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneratorSettingConfig {
    List<Material> generatorMaterials = new ArrayList<>(List.of(
                Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS,
                Material.LIGHT_BLUE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS,
                Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS,
                Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS, Material.BLUE_STAINED_GLASS,
                Material.BROWN_STAINED_GLASS, Material.GREEN_STAINED_GLASS, Material.RED_STAINED_GLASS,
                Material.BLACK_STAINED_GLASS,
                Material.WHITE_TERRACOTTA, Material.ORANGE_TERRACOTTA, Material.MAGENTA_TERRACOTTA,
                Material.LIGHT_BLUE_TERRACOTTA, Material.YELLOW_TERRACOTTA, Material.LIME_TERRACOTTA,
                Material.PINK_TERRACOTTA, Material.GRAY_TERRACOTTA, Material.LIGHT_GRAY_TERRACOTTA,
                Material.CYAN_TERRACOTTA, Material.PURPLE_TERRACOTTA
    ));

    HashMap<Material, Integer> generatorTiers = new HashMap<>() {{
        put(Material.WHITE_STAINED_GLASS, 1);
        put(Material.ORANGE_STAINED_GLASS, 2);
        put(Material.MAGENTA_STAINED_GLASS, 3);
        put(Material.LIGHT_BLUE_STAINED_GLASS, 4);
        put(Material.YELLOW_STAINED_GLASS, 5);
        put(Material.LIME_STAINED_GLASS, 6);
        put(Material.PINK_STAINED_GLASS, 7);
        put(Material.GRAY_STAINED_GLASS, 8);
        put(Material.LIGHT_GRAY_STAINED_GLASS, 9);
        put(Material.CYAN_STAINED_GLASS, 10);
        put(Material.PURPLE_STAINED_GLASS, 11);
        put(Material.BLUE_STAINED_GLASS, 12);
        put(Material.BROWN_STAINED_GLASS, 13);
        put(Material.GREEN_STAINED_GLASS, 14);
        put(Material.RED_STAINED_GLASS, 15);
        put(Material.BLACK_STAINED_GLASS, 16);

        put(Material.WHITE_TERRACOTTA, 17);
        put(Material.ORANGE_TERRACOTTA, 18);
        put(Material.MAGENTA_TERRACOTTA, 19);
        put(Material.LIGHT_BLUE_TERRACOTTA, 20);
        put(Material.YELLOW_TERRACOTTA, 21);
        put(Material.LIME_TERRACOTTA, 22);
        put(Material.PINK_TERRACOTTA, 23);
        put(Material.GRAY_TERRACOTTA, 24);
        put(Material.LIGHT_GRAY_TERRACOTTA, 25);
        put(Material.CYAN_TERRACOTTA, 26);
        put(Material.PURPLE_TERRACOTTA, 27);
    }};

    public boolean isValidMaterial(Material material) { return generatorMaterials.contains(material); }
    public Integer getTier(Material material) { return generatorTiers.getOrDefault(material, 1); }
}
