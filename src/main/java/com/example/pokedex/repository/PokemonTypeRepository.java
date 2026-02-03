package com.example.pokedex.repository;

import com.example.pokedex.model.PokemonType;

import java.util.List;

public interface PokemonTypeRepository {
    List<PokemonType> findAll();
}
