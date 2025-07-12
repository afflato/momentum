package org.afflato.momentum.utils;

import lombok.Getter;

@Getter
public enum StrategyIndices {
    NIFTYALPHA50("NIFTYALPHA50", "csv"),
    MOMENTUM500_50("500MOMENTUM50", "csv"),
    MOMENTUM200_30("200MOMENTUM30", "csv"),
    MIDCAP150MOMENTUM50("MIDCAP150MOMENTUM50", "csv"),
    VALUE500_50("500VALUE50", "csv"),
    NIFTY50("NIFTY50", "json"),
    NIFTYNEXT50("NIFTYNEXT50", "json"),
    NIFTYMIDCAP150("NIFTYMIDCAP150", "json"),
    NIFTYSMALLCAP250("NIFTYSMALLCAP250", "json");

    private String name;
    private String type;

    StrategyIndices(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static StrategyIndices fromName(String name) {
        for (StrategyIndices index : StrategyIndices.values()) {
            if (index.getName().equalsIgnoreCase(name)) { // Case-insensitive comparison
                return index;
            }
        }
        throw new IllegalArgumentException("No enum constant with name " + name);
    }

}
