package com.example.pokedex.repository;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import com.example.pokedex.model.dto.PokemonDto;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PokemonRepository {
    Pokemon findById(Long id) throws DataAccessException;
    List<Pokemon> findAll() throws DataAccessException;
    List<Pokemon> findByType(PokemonType.Type type) throws DataAccessException;
    Pokemon createPokemon(PokemonDto pokemon) throws DataAccessException;
}

