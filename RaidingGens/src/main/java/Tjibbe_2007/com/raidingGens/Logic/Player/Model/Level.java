package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import java.util.UUID;

public class Level {
    private final UUID uuid;

    private int level;
    private float exp;

    Level(UUID uuid) {
        this.uuid = uuid;
    }

    public void levelUp() {
        this.level++;
        this.exp = 0;
    }

    public int getLevel() { return level; }
    public float getExp() { return exp; }
}
