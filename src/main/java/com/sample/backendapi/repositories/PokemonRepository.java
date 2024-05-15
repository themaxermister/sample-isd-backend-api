package com.sample.backendapi.repositories;

import com.sample.backendapi.models.Pokemon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PokemonRepository {
    private final List<Pokemon> list = new ArrayList<>();

    public Iterable<Pokemon> findAll () {
        return list;
    }

    public Pokemon save (Pokemon pokemon) {
        list.add(pokemon);
        return pokemon;
    }

    public Iterable<Pokemon> saveAll(List<Pokemon> pokemons) {
        list.addAll(pokemons);
        return pokemons;
    }

    public Optional<Pokemon> findById(int id) {
        return list.stream()
                .filter(pokemon -> pokemon.getId() == id)
                .findFirst();
    }

    public void delete(Pokemon pokemon) {
        list.remove(pokemon);
    }
}
