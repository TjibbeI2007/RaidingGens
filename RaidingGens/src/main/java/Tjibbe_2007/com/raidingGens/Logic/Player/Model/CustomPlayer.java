package Tjibbe_2007.com.raidingGens.Logic.Player.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CustomPlayer {
    // THIS
    @Getter
    @Setter
    private Player player;
    @Getter
    @Setter
    private UUID uuid;
    @Delegate
    private final PlayerLevel classPlayerLevel;
    @Delegate
    private final PlayerGenerator playerGenerator;
    @Delegate
    private final PlayerCurrency playerCurrency;

    public CustomPlayer(UUID uuid) {
        this.uuid = uuid;
        this.classPlayerLevel = new PlayerLevel(this);
        this.playerGenerator = new PlayerGenerator(this);
        this.playerCurrency = new PlayerCurrency(this);
    }

    public void reloadPlayer() {
        player = Bukkit.getPlayer(uuid);
    }
}
