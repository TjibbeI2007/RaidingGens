package Tjibbe_2007.com.raidingGens.Logic.Utils;

public class ValueValidator {
    public static void validSumPositive(Number... values) {
        double total = 0;
        for (Number value : values) {
            if (value == null) throw new IllegalArgumentException("Values cannot be null");
            total += value.doubleValue();
        }
        if (total < 0) throw new IllegalArgumentException("value total must be positive");
    }

    public static void validSumNegative(Number... values) {
        double total = 0;
        for (Number value : values) {
            if (value == null) throw new IllegalArgumentException("Values cannot be null");
            total += value.doubleValue();
        }
        if (total > 0) throw new IllegalArgumentException("value total must be negative");
    }
}
