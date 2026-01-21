package com.example.pokedex.model.dto;

import com.example.pokedex.model.PokemonType;

import java.util.ArrayList;
import java.util.List;

public class PokemonDto {
    private final long id;
    private final int pokedexNumber;
    private final int hp;
    private final int atk;
    private final int def;
    private final int spd;
    private final int spatk;
    private final int spdef;
    private final List<PokemonType> types;
    private final String evolvesFrom;

    private PokemonDto(Builder builder) {
        this.id = builder.id;
        this.pokedexNumber = builder.pokedexNumber;
        this.hp = builder.hp;
        this.atk = builder.atk;
        this.def = builder.def;
        this.spd = builder.spd;
        this.spatk = builder.spatk;
        this.spdef = builder.spdef;
        this.types = List.copyOf(builder.types);
        this.evolvesFrom = builder.evolvesFrom;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private int pokedexNumber;
        private int hp;
        private int atk;
        private int def;
        private int spd;
        private int spatk;
        private int spdef;
        private List<PokemonType> types = new ArrayList<>();
        private String evolvesFrom;

        public Builder id(long id) { this.id = id; return this; }
        public Builder pokedexNumber(int pokedexNumber) { this.pokedexNumber = pokedexNumber; return this; }
        public Builder hp(int hp) { this.hp = hp; return this; }
        public Builder atk(int atk) { this.atk = atk; return this; }
        public Builder def(int def) { this.def = def; return this; }
        public Builder spd(int spd) { this.spd = spd; return this; }
        public Builder spatk(int spatk) { this.spatk = spatk; return this; }
        public Builder spdef(int spdef) { this.spdef = spdef; return this; }
        public Builder types(List<PokemonType> types) { this.types = types; return this; }
        public Builder evolvesFrom(String evolvesFrom) { this.evolvesFrom = evolvesFrom; return this; }

        public PokemonDto build() {
            return new PokemonDto(this);
        }
    }
}
