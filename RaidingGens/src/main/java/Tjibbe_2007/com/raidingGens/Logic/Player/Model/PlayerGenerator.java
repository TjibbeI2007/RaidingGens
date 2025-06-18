package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Manager.GeneratorManager;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.Player.Scoreboard.Manager.ScoreboardManager;
import Tjibbe_2007.com.raidingGens.Logic.Utils.ValueValidator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.HashMap;

public class PlayerGenerator {
    private final CustomPlayer customPlayer;

    @Getter
    @Setter
    private HashMap<Location, GeneratorModel> placedGenerators = new HashMap<>();
    @Getter
    private int maxGenerators = 10;

    public PlayerGenerator(CustomPlayer customPlayer) {
        this.customPlayer = customPlayer;
    }

    public void addPlacedGenerators(Location location, GeneratorModel generatorModel) {
        placedGenerators.put(location, generatorModel);
        ScoreboardManager.updateScoreboard(customPlayer);
    }
    public void removePlacedGenerators(Location location) {
        placedGenerators.remove(location);
        ScoreboardManager.updateScoreboard(customPlayer);
    }
    public void resetPlacedGenerators() {
        placedGenerators.clear();
        ScoreboardManager.updateScoreboard(customPlayer);
    }

    public void addMaxGenerators(int maxGenerators) {
        ValueValidator.validSumPositive(this.maxGenerators + maxGenerators);
        this.maxGenerators += maxGenerators;
        ScoreboardManager.updateScoreboard(customPlayer);
    }

    public void setMaxGenerators(int maxGenerators) {
        ValueValidator.validSumPositive(maxGenerators);
        this.maxGenerators = maxGenerators;
        ScoreboardManager.updateScoreboard(customPlayer);
    }
}
