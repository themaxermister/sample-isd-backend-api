package com.sample.backendapi.models;

public record BaseStats(int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {

    @Override
    public String toString() {
        return "BaseStats{" +
                "hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", specialAttack=" + specialAttack +
                ", specialDefense=" + specialDefense +
                ", speed=" + speed +
                '}';
    }
}
