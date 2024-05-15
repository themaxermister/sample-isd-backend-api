package com.sample.backendapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    private int id;
    private Map<String, String> name;

    private List<String> type;
    private BaseStats base;

    @Override
    public String toString() {
        return String.format(
                "Pokemon[id=%d, name='%s', type='%s', base='%s']", id, name, type, base);
    }
}