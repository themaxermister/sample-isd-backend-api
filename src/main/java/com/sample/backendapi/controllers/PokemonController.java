package com.sample.backendapi.controllers;

import com.sample.backendapi.models.Pokemon;
import com.sample.backendapi.services.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonController.class);

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Pokemon>> list() {
        LOGGER.info("Returning all pokemons");
        return ResponseEntity.ok(pokemonService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> get(@PathVariable("id") Integer id) {
        LOGGER.info("Returning pokemon with id: " + id + " if exists");
        return ResponseEntity.ok(pokemonService.get(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Pokemon> save(@RequestBody Pokemon pokemon) {
        LOGGER.info("Saving pokemon: " + pokemon);
        try {
            pokemonService.save(pokemon);
            return ResponseEntity.ok(pokemon);
        }
        catch (IllegalArgumentException e) {
            LOGGER.error("Error saving pokemon: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        try {
            LOGGER.info("Deleting pokemon with id: " + id);
            pokemonService.delete(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            LOGGER.error("Error deleting pokemon: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}