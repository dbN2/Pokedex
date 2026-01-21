package com.example.pokedex.model.dto;

import java.util.List;

public record Response(
    String body,
    List<PokemonDto> pokemon
) {}
