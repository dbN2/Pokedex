package com.example.pokedex.model.dto;

import com.example.pokedex.model.PokemonType;

import java.util.ArrayList;
import java.util.List;

public class PokemonDto {
    private final Long id;
    private final Integer pokedexNumber;
    private final String name;
    private final Integer hp;
    private final Integer atk;
    private final Integer def;
    private final Integer spd;
    private final Integer spatk;
    private final Integer spdef;
    private final List<PokemonType> types;
    private final String evolvesFrom;

    private PokemonDto(Builder builder) {
        this.id = builder.id;
        this.pokedexNumber = builder.pokedexNumber;
        this.name = builder.name;
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
        private Long id;
        private Integer pokedexNumber;
        private String name;
        private Integer hp;
        private Integer atk;
        private Integer def;
        private Integer spd;
        private Integer spatk;
        private Integer spdef;
        private List<PokemonType> types = new ArrayList<>();
        private String evolvesFrom;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder pokedexNumber(Integer pokedexNumber) { this.pokedexNumber = pokedexNumber; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder hp(Integer hp) { this.hp = hp; return this; }
        public Builder atk(Integer atk) { this.atk = atk; return this; }
        public Builder def(Integer def) { this.def = def; return this; }
        public Builder spd(Integer spd) { this.spd = spd; return this; }
        public Builder spatk(Integer spatk) { this.spatk = spatk; return this; }
        public Builder spdef(Integer spdef) { this.spdef = spdef; return this; }
        public Builder types(List<PokemonType> types) { this.types = types; return this; }
        public Builder evolvesFrom(String evolvesFrom) { this.evolvesFrom = evolvesFrom; return this; }

        public PokemonDto build() {
            return new PokemonDto(this);
        }
    }

    public Long getId() {
        return id;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAtk() {
        return atk;
    }

    public Integer getDef() {
        return def;
    }

    public Integer getSpd() {
        return spd;
    }

    public Integer getSpatk() {
        return spatk;
    }

    public Integer getSpdef() {
        return spdef;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public String getEvolvesFrom() {
        return evolvesFrom;
    }
}
