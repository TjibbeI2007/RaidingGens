package Tjibbe_2007.com.raidingGens.Logic.Player.Scoreboard.Manager;

import Tjibbe_2007.com.raidingGens.Logic.Player.Config.CustomPlayerConfig;
import Tjibbe_2007.com.raidingGens.Logic.Player.Model.CustomPlayer;
import Tjibbe_2007.com.raidingGens.Logic.Utils.Style;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {
    public static void updateScoreboard(CustomPlayer customPlayer) {
        if (customPlayer == null || customPlayer.getPlayer() == null) return;

        org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;

        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("stats", "dummy", String.format("%s§lRaiding%s§lGens", Style.PRIMARY_COLOUR, Style.SECONDARY_COLOUR));
        objective.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);

        Score filler0 = objective.getScore("§8");
        filler0.setScore(13);

        Score balance = objective.getScore(String.format(
                "%s§l| §r%sBalance§7: %s%.0f%s",
                Style.PRIMARY_COLOUR,
                Style.PRIMARY_COLOUR,
                Style.VALUE_COLOUR,
                customPlayer.getTokens(),
                Style.BALANCE_STYLE
        ));
        balance.setScore(12);

        Score mana = objective.getScore(String.format(
                "%s§l| §r%sMana§7: %s%.0f%s",
                Style.PRIMARY_COLOUR,
                Style.PRIMARY_COLOUR,
                Style.VALUE_COLOUR,
                customPlayer.getMana(),
                Style.MANA_STYLE
        ));
        mana.setScore(11);

        Score filler1 = objective.getScore("§1");
        filler1.setScore(10);

        Score level = objective.getScore(String.format(
                "%s§l| §r%sLevel§7: %s%d",
                Style.PRIMARY_COLOUR,
                Style.PRIMARY_COLOUR,
                Style.VALUE_COLOUR,
                customPlayer.getLevel()
        ));
        level.setScore(9);

        Score levelUpPercentage = objective.getScore(String.format(
                "%s§l| §r%sLevel Up§7: %s%.0f%%",
                Style.PRIMARY_COLOUR,
                Style.PRIMARY_COLOUR,
                Style.VALUE_COLOUR,
                (customPlayer.getExp() / CustomPlayerConfig.getExpFormula().apply(customPlayer.getLevel())) * 100
        ));
        levelUpPercentage.setScore(8);

        Score filler2 = objective.getScore("§9");
        filler2.setScore(7);

        Score generators = objective.getScore(String.format(
                "%s§l| §r%sGenerators§7: %s%d§7/%s%d",
                Style.PRIMARY_COLOUR,
                Style.PRIMARY_COLOUR,
                Style.VALUE_COLOUR,
                customPlayer.getPlacedGenerators().size(),
                Style.VALUE_COLOUR,
                customPlayer.getMaxGenerators()
        ));
        generators.setScore(6);

        Score footer = objective.getScore("§aRaidingGens.minehut.gg");
        footer.setScore(5);

        customPlayer.getPlayer().setScoreboard(scoreboard);
    }
}