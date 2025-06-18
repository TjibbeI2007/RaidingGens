package Tjibbe_2007.com.raidingGens.Logic.Player.Config;

import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

public class CustomPlayerConfig {
    public static IntFunction<Integer> getGeneratorFormula() { return level -> 5 + (level - 1) * 2; }
    public static IntFunction<Float> getExpFormula() { return level -> 100 * (1 + (level - 1) * 0.1f); }
}
