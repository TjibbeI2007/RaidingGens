package Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public interface GameItemBuilderInterface {
    ItemStack create();
    int tier();
    Material material();
    String name();
    List<String> lore();
    Float cost();
    Integer requirement();
    UUID owner();
    Location location();
}
