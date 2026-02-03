package com.example.pokedex.model.dto;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class CreatePokemonRequest {
    private final Integer pokedexNumber;
    private String name;
    private final Integer hp;
    private final Integer atk;
    private final Integer def;
    private final Integer spd;
    private final Integer spatk;
    private final Integer spdef;
    private final List<PokemonType.Type> types;
    private Long evolvesFromId;
    private final String evolvesFrom;

    public CreatePokemonRequest(Integer pokedexNumber, String name, Integer hp, Integer atk, Integer def, Integer spd, Integer spatk, Integer spdef, List<PokemonType.Type> types, Long evolvesFromId, String evolvesFrom) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.spatk = spatk;
        this.spdef = spdef;
        this.types = types;
        this.evolvesFromId = evolvesFromId;
        this.evolvesFrom = evolvesFrom;
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

    public List<PokemonType.Type> getTypes() {
        return types;
    }

    public Long getEvolvesFromId() {
        return evolvesFromId;
    }

    public String getEvolvesFrom() {
        return evolvesFrom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEvolvesFromId(Long evolvesFromId) {
        this.evolvesFromId = evolvesFromId;
    }
}
