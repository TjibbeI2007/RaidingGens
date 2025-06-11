package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import org.bukkit.entity.Player;

import java.util.UUID;

public class CustomPlayer {
    private final Player player;
    private final UUID uuid;
    private final Level classLevel;

    CustomPlayer(Player player) {
        this.uuid = player.getUniqueId();
        this.player = player;
        this.classLevel = new Level(uuid);
    }


    // LEVEL
    public int getLevel() { return classLevel.getLevel(); }
    public float getExp() { return classLevel.getExp(); }
    public void levelUp() { classLevel.levelUp(); }

    // THIS
    public UUID getUuid() { return uuid; }
    public Player getPlayer() { return player; }
}
