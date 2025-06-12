package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager.GeneratorManager;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.Utils.ValueValidator;
import lombok.Getter;
import org.bukkit.Location;

import java.util.HashMap;

public class PlayerGenerator {
    private final CustomPlayer customPlayer;

    @Getter
    private final HashMap<Location, GeneratorModel> placedGenerators = new HashMap<>();
    @Getter
    private int maxGenerators = 10;

    public PlayerGenerator(CustomPlayer customPlayer) {
        this.customPlayer = customPlayer;
    }

    public void addPlacedGenerators(Location location) { new GeneratorManager().place(customPlayer, location.getBlock()); }
    public void removePlacedGenerators(Location location) { new GeneratorManager().remove(customPlayer, location.getBlock()); }

    public void addMaxGenerators(int maxGenerators) {
        ValueValidator.validSumPositive(this.maxGenerators + maxGenerators);
        this.maxGenerators += maxGenerators;
    }

    public void resetPlacedGenerators() { placedGenerators.clear(); }
    public void setMaxGenerators(int maxGenerators) {
        ValueValidator.validSumPositive(maxGenerators);
        this.maxGenerators = maxGenerators;
    }
}
