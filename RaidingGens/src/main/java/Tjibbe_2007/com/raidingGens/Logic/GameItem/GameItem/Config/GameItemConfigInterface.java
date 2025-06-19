package Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Config;

import org.bukkit.Material;

import java.util.List;

public interface GameItemConfigInterface {
    boolean isValidMaterial(Material material);
    int getTier(Material material);
    String getName(Material material);
    List<String> getLore(Material material);
    float getCost(Material material);
    int getExp(Material material);
    float getWorth(Material material);
    int getRequirement(Material material);
    Material getMaterial(Integer tier);
}
