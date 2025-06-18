package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import Tjibbe_2007.com.raidingGens.Logic.Player.Config.CustomPlayerConfig;
import Tjibbe_2007.com.raidingGens.Logic.Player.Scoreboard.Manager.ScoreboardManager;
import Tjibbe_2007.com.raidingGens.Logic.Utils.ValueValidator;
import lombok.Getter;

@Getter
public class PlayerLevel {
    private int level = 1;
    private float exp = 0;
    CustomPlayer customPlayer;

    public PlayerLevel(CustomPlayer customPlayer) {
        this.customPlayer = customPlayer;
    }

    public void levelUp() {
        while (this.exp >= CustomPlayerConfig.getExpFormula().apply(this.level)) {
            this.level++;
            this.exp -= CustomPlayerConfig.getExpFormula().apply(this.level);
            this.customPlayer.setMaxGenerators(CustomPlayerConfig.getGeneratorFormula().apply(this.level));
            ScoreboardManager.updateScoreboard(this.customPlayer);
        }
    }

    public void addLevel(int amount) {
        ValueValidator.validSumPositive(amount);
        this.level += amount;
        ScoreboardManager.updateScoreboard(this.customPlayer);
    }

    public void addExp(float amount) {
        ValueValidator.validSumPositive(amount);
        this.exp += amount;
        ScoreboardManager.updateScoreboard(this.customPlayer);
    }

    public void setLevel(int level) {
        ValueValidator.validSumPositive(level);
        this.level = level;
        ScoreboardManager.updateScoreboard(this.customPlayer);
    }

    public void setExp(float exp) {
        ValueValidator.validSumPositive(exp);
        this.exp = exp;
        ScoreboardManager.updateScoreboard(this.customPlayer);
    }

    public void resetLevel() {
        this.level = 1;
        ScoreboardManager.updateScoreboard(this.customPlayer);
    }
    public void resetExp() {
        this.exp = 0;
        ScoreboardManager.updateScoreboard(this.customPlayer);
    }
}
