package Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Config;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Config.GameItemConfigInterface;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefenseConfig implements GameItemConfigInterface {
    @Getter
    private final List<Material> materials = List.of(
            Material.MUD_BRICKS, Material.STONE_BRICKS, Material.POLISHED_BLACKSTONE_BRICKS,
            Material.NETHER_BRICKS, Material.CHISELED_NETHER_BRICKS
    );

    @Getter
    private final List<String> names = List.of(
            "§8[§x§8§4§5§4§3§2Tier I§8] §7Defense",
            "§8[§x§A§A§A§A§A§ATier II§8] §7Defense",
            "§8[§x§2§2§2§2§2§2Tier III§8] §7Defense",
            "§8[§x§8§0§0§0§0§0Tier IV§8] §7Defense",
            "§8[§x§F§F§C§C§C§CTier V§8] §7Defense"
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

    private final HashMap<Material, Integer> requirementsMap = new HashMap<>() {{
        IntStream.range(0, materials.size()).forEach(i -> put(materials.get(i), (i)*2));
    }};

    private final HashMap<Material, List<String>> loreMap = materials.stream()
            .collect(Collectors.toMap(material -> material,
                material -> List.of(
                    "§7This is defense place",
                    "§7down to protect your base",
                    "",
                    "§8» §7Tier: §f" + tiersMap.get(material),
                    "§8» §7Cost: §f" + costsMap.get(material),
                    "§8» §7Requirement: §bLevel §f" + requirementsMap.get(material),
                    "",
                    "§8§o(Place down to start)"
                ),
                (a, b) -> b, HashMap::new
            ));

    @Getter
    private final static DefenseConfig instance = new DefenseConfig();
    public float getSilkTouchChance() { return 0.1f; }
    public int getExp(Material material) { return 0; }
    public float getWorth(Material material) { return 0; }
    public boolean isValidMaterial(Material material) { return materials.contains(material); }
    public int getTier(Material material) { return tiersMap.getOrDefault(material, 1); }
    public String getName(Material material) { return namesMap.getOrDefault(material, "§8[§fTier I§8] §fDefense"); }
    public float getCost(Material material) { return costsMap.getOrDefault(material, 100.0f); }
    public int getRequirement(Material material) { return requirementsMap.getOrDefault(material, 1); }
    public List<String> getLore(Material material) { return loreMap.getOrDefault(material, new ArrayList<>()); }
    public Material getMaterial(Integer tier) {
        if (tier < 1 || tier > materials.size()) { throw new IllegalArgumentException("Tier must be between 1 and " + materials.size() + ". Provided: " + tier); }
        return materials.get(tier - 1);
    }
}
