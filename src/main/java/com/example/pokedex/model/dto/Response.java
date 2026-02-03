package com.example.pokedex.model.dto;

import com.example.pokedex.model.PokemonType;

import java.util.List;

public class Response {
    private final String body;
    private final List<PokemonDto> pokemon;
    private final List<PokemonType> pokemonTypes;

    public String getBody() { return body; }
    public List<PokemonDto> getPokemon() { return pokemon; }
    public List<PokemonType> getTypes() { return pokemonTypes; }

    public Response(String body, List<PokemonDto> pokemon, List<PokemonType> pokemonTypes) {
        this.body = body;
        this.pokemon = pokemon;
        this.pokemonTypes = pokemonTypes;
    }
}
