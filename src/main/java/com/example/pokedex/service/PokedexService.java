package com.example.pokedex.service;

import com.example.pokedex.exception.UnknownErrorException;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import com.example.pokedex.repository.PokemonTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class PokedexService {
    private static final Logger log = LoggerFactory.getLogger(PokedexService.class);
    private PokemonRepository pokemonRepository;

    public PokedexService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Optional<Pokemon> findPokemonById(Long id) {
        try {
            return Optional.ofNullable(pokemonRepository.findById(id));
        }
        catch (DataAccessException e) {
            log.error("Encountered database exception %s", e);
            throw new UnknownErrorException("Encountered unexpected exception when getting pokemon by id");
        }
        catch (RuntimeException e) {
            log.error("Encountered unexpected exception when getting pokemon by id %s", e);
            throw new UnknownErrorException("Encountered unexpected exception when getting pokemon by id");
        }
    }

    public List<Pokemon> findAllPokemon() {
        try {
            return pokemonRepository.findAll();
        }
        catch (DataAccessException e) {
            log.error("Encountered database exception when getting pokemon %s", e);
            throw e;
        }
        catch (RuntimeException e) {
            log.error("Encountered unexpected exception when getting pokemon %s", e);
            throw e;
        }
    }


}
