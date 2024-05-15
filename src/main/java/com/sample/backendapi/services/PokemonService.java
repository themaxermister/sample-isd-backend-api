package com.sample.backendapi.services;

import com.sample.backendapi.models.Pokemon;
import com.sample.backendapi.repositories.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;


@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Iterable<Pokemon> list() {
        return pokemonRepository.findAll();
    }

    public Pokemon save(Pokemon pokemon) {
        Objects.requireNonNull(pokemon, "Pokemon cannot be null");

        int id = pokemon.getId();

        if (pokemonRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Pokemon with ID " + id + " already exists");
        }

        return pokemonRepository.save(pokemon);
    }

    public void save(List<Pokemon> pokemons) {
        pokemonRepository.saveAll(pokemons);
    }

    public Pokemon get(int id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Optional<Pokemon> pokemonOptional = pokemonRepository.findById(id);
        if (pokemonOptional.isPresent()) {
            pokemonRepository.delete(pokemonOptional.get());
        } else {
            throw new NoSuchElementException("Pokemon with ID " + id + " not found");
        }
    }
}