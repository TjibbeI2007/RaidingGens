package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import Tjibbe_2007.com.raidingGens.Logic.Utils.ValueValidator;
import lombok.Getter;

@Getter
public class PlayerCurrency {
    private float tokens = 75;
    private float mana = 0;

    public PlayerCurrency(CustomPlayer customPlayer) { }

    public void addTokens(float amount) {
        ValueValidator.validSumPositive(tokens, amount);
        tokens += amount;
    }
    public void addMana(float amount) {
        ValueValidator.validSumPositive(mana, amount);
        mana += amount;
    }

    public void setTokens(float tokens) {
        ValueValidator.validSumPositive(tokens);
        this.tokens = tokens;
    }

    public void setMana(float mana) {
        ValueValidator.validSumPositive(mana);
        this.mana = mana;
    }

    public void resetTokens() { this.tokens = 75; }
    public void resetMana() { this.mana = 0; }
}
