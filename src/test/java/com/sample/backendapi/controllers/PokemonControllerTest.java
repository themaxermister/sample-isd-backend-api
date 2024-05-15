package com.sample.backendapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.backendapi.models.BaseStats;
import com.sample.backendapi.models.Pokemon;
import com.sample.backendapi.services.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonController pokemonController;

    private Pokemon pokemon1, pokemon2, testPokemon;
    private BaseStats baseStats1, baseStats2, testBaseStats;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pokemon1 = new Pokemon();
        pokemon1.setId(1);
        pokemon1.setName(Map.of("en", "Pikachu", "ja", "ピカチュウ"));
        pokemon1.setType(List.of("Electric", "Normal"));
        baseStats1 = new BaseStats();
        baseStats1.setHp(35);
        baseStats1.setAttack(55);
        baseStats1.setDefense(40);
        baseStats1.setSpAttack(50);
        baseStats1.setSpDefense(50);
        baseStats1.setSpeed(90);
        pokemon1.setBase(baseStats1);
        pokemonService.save(pokemon1);

        pokemon2 = new Pokemon();
        pokemon2.setId(2);
        pokemon2.setName(Map.of("en", "Charmander", "ja", "ヒトカゲ"));
        pokemon2.setType(List.of("Fire", "Flying"));
        baseStats2 = new BaseStats();
        baseStats2.setHp(35);
        baseStats2.setAttack(55);
        baseStats2.setDefense(40);
        baseStats2.setSpAttack(50);
        baseStats2.setSpDefense(50);
        baseStats2.setSpeed(90);
        pokemon2.setBase(baseStats2);
        pokemonService.save(pokemon2);
    }

    @Test
    void testList() throws Exception {
        when(pokemonService.list()).thenReturn(List.of(pokemon1, pokemon2));

        mockMvc.perform(get("/api/pokemon/all"))
                // check length
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(2));

        verify(pokemonService, times(1)).list();
    }

    @Test
    void testGet() throws Exception {
        when(pokemonService.get(1)).thenReturn(pokemon1);

        mockMvc.perform(get("/api/pokemon/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name.en").value("Pikachu"));

        verify(pokemonService, times(1)).get(1);
    }

    @Test
    void testSave() throws Exception {
        testPokemon = new Pokemon();
        testPokemon.setId(3);
        testPokemon.setName(Map.of("en", "Voltorb", "ja", "ビリリダマ"));
        testPokemon.setType(List.of("Electric", "Normal"));
        testBaseStats = new BaseStats();
        testBaseStats.setHp(35);
        testBaseStats.setAttack(55);
        testBaseStats.setDefense(40);
        testBaseStats.setSpAttack(50);
        testBaseStats.setSpDefense(50);
        testBaseStats.setSpeed(90);
        testPokemon.setBase(testBaseStats);

        when(pokemonService.save(testPokemon)).thenReturn(testPokemon);

        mockMvc.perform(post("/api/pokemon/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testPokemon)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name.en").value("Voltorb"));

        verify(pokemonService, times(1)).save(testPokemon);


    }

    @Test
    void testSaveDuplicate() throws Exception {
        when(pokemonService.save(pokemon1)).thenThrow(new IllegalArgumentException("Pokemon with ID 1 already exists"));

        mockMvc.perform(post("/api/pokemon/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(pokemon1)))
                .andExpect(status().isBadRequest());

        verify(pokemonService, times(2)).save(pokemon1);
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(pokemonService).delete(3);

        mockMvc.perform(delete("/api/pokemon/3"))
                .andExpect(status().isOk());

        verify(pokemonService, times(1)).delete(3);
    }

    @Test
    void testDeleteNotExist() throws Exception {
        doThrow(new NoSuchElementException("Pokemon with ID 100 not found")).when(pokemonService).delete(100);

        mockMvc.perform(delete("/api/pokemon/100"))
                .andExpect(status().isNotFound());

        verify(pokemonService, times(1)).delete(100);
    }
}