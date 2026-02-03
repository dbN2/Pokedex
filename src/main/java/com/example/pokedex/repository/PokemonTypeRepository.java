package com.example.pokedex.repository;

import com.example.pokedex.model.PokemonType;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface PokemonTypeRepository {
    PokemonType findByName(String name) throws DataAccessException;
    List<PokemonType> findAll() throws DataAccessException;
}
