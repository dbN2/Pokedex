package com.example.pokedex.model.dto;

import com.example.pokedex.model.PokemonType;

import java.util.List;

public class CreatePokemonRequest {
    private Long id;
    private Integer pokedexNumber;
    private Integer hp;
    private Integer atk;
    private Integer def;
    private Integer spd;
    private Integer spatk;
    private Integer spdef;
    private List<PokemonType> types;
    private String evolvesFrom;

    public CreatePokemonRequest() {

    }

    public Long getId() {
        return id;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
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
