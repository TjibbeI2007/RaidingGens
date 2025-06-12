package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import Tjibbe_2007.com.raidingGens.Logic.Utils.ValueValidator;
import lombok.Getter;

@Getter
public class PlayerLevel {
    private int level = 1;
    private float exp = 0;

    public PlayerLevel(CustomPlayer customPlayer) { }

    public void levelUp() {
        this.level++;
        this.exp = 0;
    }

    public void addLevel(int amount) {
        ValueValidator.validSumPositive(amount);
        this.level += amount;
    }

    public void addExp(float amount) {
        ValueValidator.validSumPositive(amount);
        this.exp += amount;
    }

    public void setLevel(int level) {
        ValueValidator.validSumPositive(level);
        this.level = level;
    }

    public void setExp(float exp) {
        ValueValidator.validSumPositive(exp);
        this.exp = exp;
    }

    public void resetLevel() { this.level = 1; }
    public void resetExp() { this.exp = 0; }
}
