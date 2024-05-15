package com.sample.backendapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Embeddable
public class BaseStats {

    @JsonProperty("HP")
    private int hp;

    @JsonProperty("Attack")
    private int attack;

    @JsonProperty("Defense")
    private int defense;

    @JsonProperty("Sp. Attack")
    private int spAttack;

    @JsonProperty("Sp. Defense")
    private int spDefense;

    @JsonProperty("Speed")
    private int speed;
}
