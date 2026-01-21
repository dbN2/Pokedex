package com.example.pokedex.repository;

import com.example.pokedex.model.PokemonType;

import java.util.List;

public interface PokemonTypeRepository {
    PokemonType findById(Long id);
    List<PokemonType> findAll();
    void save(PokemonType skill);
}
