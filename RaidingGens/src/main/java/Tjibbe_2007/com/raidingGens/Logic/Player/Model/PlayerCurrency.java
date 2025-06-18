package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import Tjibbe_2007.com.raidingGens.Logic.Player.Scoreboard.Manager.ScoreboardManager;
import Tjibbe_2007.com.raidingGens.Logic.Utils.ValueValidator;
import lombok.Getter;

@Getter
public class PlayerCurrency {
    private float tokens = 75;
    private float mana = 0;
    private final CustomPlayer customPlayer;

    public PlayerCurrency(CustomPlayer customPlayer) {
        this.customPlayer = customPlayer;
    }

    public void addTokens(float amount) {
        ValueValidator.validSumPositive(tokens, amount);
        tokens += amount;
        ScoreboardManager.updateScoreboard(customPlayer);
    }
    public void addMana(float amount) {
        ValueValidator.validSumPositive(mana, amount);
        mana += amount;
        ScoreboardManager.updateScoreboard(customPlayer);
    }

    public void setTokens(float tokens) {
        ValueValidator.validSumPositive(tokens);
        this.tokens = tokens;
        ScoreboardManager.updateScoreboard(customPlayer);
    }

    public void setMana(float mana) {
        ValueValidator.validSumPositive(mana);
        this.mana = mana;
        ScoreboardManager.updateScoreboard(customPlayer);
    }

    public void resetTokens() {
        this.tokens = 75;
        ScoreboardManager.updateScoreboard(customPlayer);
    }
    public void resetMana() {
        this.mana = 0;
        ScoreboardManager.updateScoreboard(customPlayer);
    }
}
