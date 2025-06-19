package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import Tjibbe_2007.com.raidingGens.Logic.GameItem.Defense.Model.DefenseModel;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilder;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.GameItem.Model.GameItemBuilderInterface;
import Tjibbe_2007.com.raidingGens.Logic.GameItem.Generator.Model.GeneratorModel;
import Tjibbe_2007.com.raidingGens.Logic.Player.Scoreboard.Manager.ScoreboardManager;
import Tjibbe_2007.com.raidingGens.Logic.Utils.ValueValidator;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

import java.util.HashMap;

public class PlayerDefense {
    @Getter @Setter
    private HashMap<Location, GameItemBuilderInterface> placedDefense = new HashMap<>();
    private final CustomPlayer customPlayer;

    public PlayerDefense(CustomPlayer customPlayer) { this.customPlayer = customPlayer; }

    public void addPlacedDefense(Location location, GameItemBuilderInterface model) {
        placedDefense.put(location, model);
        ScoreboardManager.updateScoreboard(customPlayer);
    }
    public void removePlacedDefense(Location location) {
        placedDefense.remove(location);
        ScoreboardManager.updateScoreboard(customPlayer);
    }
    public void resetPlacedDefense() {
        placedDefense.clear();
        ScoreboardManager.updateScoreboard(customPlayer);
    }
}
