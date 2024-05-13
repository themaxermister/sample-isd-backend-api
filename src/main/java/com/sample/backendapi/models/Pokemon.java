package com.sample.backendapi.models;

import java.util.List;

public class Pokemon {
    private final String name;
    private final List<String> type;
    private final BaseStats baseStats;

    public Pokemon(String name,  List<String> type, BaseStats baseStats) {
        this.name = name;
        this.type = type;
        this.baseStats = baseStats;
    }

    public String getName() {
        return name;
    }

    public List<String> getType() {
        return type;
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }
}
