package com.sample.backendapi.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.backendapi.models.Pokemon;
import com.sample.backendapi.services.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    CommandLineRunner runner(PokemonService pokemonService) {
        return args -> {
            // read json and write to repo
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Pokemon>> typeReference = new TypeReference<>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/pokedex.json");
            try {
                List<Pokemon> pokemons = mapper.readValue(inputStream, typeReference);
                pokemonService.save(pokemons);
                LOGGER.info("Pokemons Saved!");
            } catch (IOException e) {
                LOGGER.error("Unable to save pokemons: " + e.getMessage());
            }
        };
    }
}