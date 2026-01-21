package com.example.pokedex.repository;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PokemonRepository {
    Pokemon findById(Long id) throws DataAccessException;
    List<Pokemon> findAll() throws DataAccessException;
    List<Pokemon> findByType(PokemonType type) throws DataAccessException;
    void save(Pokemon pokemon) throws DataAccessException;
}

