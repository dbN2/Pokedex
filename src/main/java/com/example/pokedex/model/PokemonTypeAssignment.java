package com.example.pokedex.model;

import java.util.Objects;

public class PokemonTypeAssignment {
    private Long pokemonId;
    private Long typeId;

    public PokemonTypeAssignment(Long pokemonId, Long typeId) {
        this.pokemonId = pokemonId;
        this.typeId = typeId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Long pokemonId) {
        this.pokemonId = pokemonId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PokemonTypeAssignment that = (PokemonTypeAssignment) o;
        return Objects.equals(pokemonId, that.pokemonId) && Objects.equals(typeId, that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonId, typeId);
    }

    @Override
    public String toString() {
        return "PokemonTypeAssignment{" +
                "pokemonId=" + pokemonId +
                ", typeId=" + typeId +
                '}';
    }
}
