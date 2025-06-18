package Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Config;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Config.GameItemConfig;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneratorConfig implements GameItemConfig {
    @Getter
    private final List<Material> materials = List.of(
                Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS,
                Material.LIGHT_BLUE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS,
                Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS,
                Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS, Material.BLUE_STAINED_GLASS,
                Material.BROWN_STAINED_GLASS, Material.GREEN_STAINED_GLASS, Material.RED_STAINED_GLASS,
                Material.BLACK_STAINED_GLASS,
                Material.WHITE_GLAZED_TERRACOTTA, Material.ORANGE_GLAZED_TERRACOTTA, Material.MAGENTA_GLAZED_TERRACOTTA,
                Material.LIGHT_BLUE_GLAZED_TERRACOTTA, Material.YELLOW_GLAZED_TERRACOTTA, Material.LIME_GLAZED_TERRACOTTA,
                Material.PINK_GLAZED_TERRACOTTA, Material.GRAY_GLAZED_TERRACOTTA, Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
                Material.CYAN_GLAZED_TERRACOTTA, Material.PURPLE_GLAZED_TERRACOTTA
    );

    @Getter
    private final List<String> names = List.of(
            "§8[§x§E§9§E§C§E§CTier I§8] §7Generator",
            "§8[§x§F§0§7§6§1§3Tier II§8] §7Generator",
            "§8[§x§B§D§4§4§B§3Tier III§8] §7Generator",
            "§8[§x§3§A§A§F§D§9Tier IV§8] §7Generator",
            "§8[§x§F§8§C§6§2§7Tier V§8] §7Generator",
            "§8[§x§7§0§B§9§1§9Tier VI§8] §7Generator",
            "§8[§x§E§D§8§D§A§CTier VII§8] §7Generator",
            "§8[§x§3§E§4§4§4§7Tier VIII§8] §7Generator",
            "§8[§x§8§E§8§E§8§6Tier IX§8] §7Generator",
            "§8[§x§1§5§8§9§9§1Tier X§8] §7Generator",
            "§8[§x§7§9§2§A§A§CTier XI§8] §7Generator",
            "§8[§x§3§5§3§9§9§DTier XII§8] §7Generator",
            "§8[§x§7§2§4§7§2§8Tier XIII§8] §7Generator",
            "§8[§x§5§4§6§D§1§BTier XIV§8] §7Generator",
            "§8[§x§A§1§2§7§2§2Tier XV§8] §7Generator",
            "§8[§x§1§4§1§5§1§9Tier XVI§8] §7Generator",
            "§8[§x§E§9§E§C§E§CTier XVII§8] §7Generator",
            "§8[§x§F§0§7§6§1§3Tier XVIII§8] §7Generator",
            "§8[§x§B§D§4§4§B§3Tier XIX§8] §7Generator",
            "§8[§x§3§A§A§F§D§9Tier XX§8] §7Generator",
            "§8[§x§F§8§C§6§2§7Tier XXI§8] §7Generator",
            "§8[§x§7§0§B§9§1§9Tier XXII§8] §7Generator",
            "§8[§x§E§D§8§D§A§CTier XXIII§8] §7Generator",
            "§8[§x§3§E§4§4§4§7Tier XXIV§8] §7Generator",
            "§8[§x§8§E§8§E§8§6Tier XXV§8] §7Generator",
            "§8[§x§1§5§8§9§9§1Tier XXVI§8] §7Generator",
            "§8[§x§7§9§2§A§A§CTier XXVII§8] §7Generator"
    );

    private final HashMap<Material, Integer> tiersMap = new HashMap<>() {{
        IntStream.range(0, materials.size()).forEach(i -> put(materials.get(i), (i+1)));
    }};

    private final HashMap<Material, String> namesMap = new HashMap<>() {{
        IntStream.range(0, materials.size()).forEach(i -> put(materials.get(i), names.get(i)));
    }};

    private final HashMap<Material, Float> costsMap = new HashMap<>() {{
        IntStream.range(0, materials.size()).forEach(i -> put(materials.get(i), (i+1)*75.0f));
    }};

    private final HashMap<Material, Integer> expMap = new HashMap<>() {{
        IntStream.range(0, materials.size()).forEach(i -> put(materials.get(i), (i+1)));
    }};

    private final HashMap<Material, Float> worthMap = new HashMap<>() {{
        IntStream.range(0, materials.size()).forEach(i -> put(materials.get(i), (i+1)*1.1f));
    }};

    private final HashMap<Material, Integer> requirementsMap = new HashMap<>() {{
        IntStream.range(0, materials.size()).forEach(i -> put(materials.get(i), (i)*2));
    }};

    private final HashMap<Material, List<String>> loreMap = materials.stream()
            .collect(Collectors.toMap(material -> material,
                material -> List.of(
                    "§7This is a generator",
                    "§7that generates resources",
                    "",
                    "§8» §7Note Worth: §f" + worthMap.get(material),
                    "§8» §7Note Exp: §f" + expMap.get(material),
                    "§8» §7Tier: §f" + tiersMap.get(material),
                    "§8» §7Cost: §f" + costsMap.get(material),
                    "§8» §7Requirement: §bLevel §f" + requirementsMap.get(material),
                    "",
                    "§8§o(Place down to start)"
                ),
                (a, b) -> b, HashMap::new
            ));

    public static GeneratorConfig getInstance() { return new GeneratorConfig(); }
    public boolean isValidMaterial(Material material) { return materials.contains(material); }
    public int getTier(Material material) { return tiersMap.getOrDefault(material, 1); }
    public String getName(Material material) { return namesMap.getOrDefault(material, "§8[§fTier I§8] §fGenerator"); }
    public float getCost(Material material) { return costsMap.getOrDefault(material, 100.0f); }
    public int getExp(Material material) { return expMap.getOrDefault(material, 10); }
    public float getWorth(Material material) { return worthMap.getOrDefault(material, 1.0f); }
    public int getRequirement(Material material) { return requirementsMap.getOrDefault(material, 1); }
    public List<String> getLore(Material material) { return loreMap.getOrDefault(material, new ArrayList<>()); }
    public Material getMaterial(Integer tier) {
        if (tier < 1 || tier > materials.size()) {
            throw new IllegalArgumentException("Tier must be between 1 and " + materials.size() + ". Provided: " + tier);
        }
        return materials.get(tier - 1);
    }
}
