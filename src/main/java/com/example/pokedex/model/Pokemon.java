package com.example.pokedex.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Pokemon {
    private Long id;
    private Integer pokedexNumber;
    private String name;
    private Integer hp;
    private Integer atk;
    private Integer def;
    private Integer spd;
    private Integer spatk;
    private Integer spdef;
    private List<PokemonType> types;
    private String evolvesFrom;
    private Instant created;
    private Instant updated;

    public Pokemon() {

    }

    public Pokemon(long id, int pokedexNumber, String pokemonName, int hp) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(Integer pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> pokemonTypes) {
        this.types = pokemonTypes;
    }

    public String getEvolvesFrom() {
        return evolvesFrom;
    }

    public void setEvolvesFrom(String evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getSpd() {
        return spd;
    }

    public void setSpd(Integer spd) {
        this.spd = spd;
    }

    public Integer getSpatk() {
        return spatk;
    }

    public void setSpatk(Integer spatk) {
        this.spatk = spatk;
    }

    public Integer getSpdef() {
        return spdef;
    }

    public void setSpdef(Integer spdef) {
        this.spdef = spdef;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return hp == pokemon.hp && Objects.equals(id, pokemon.id) && Objects.equals(name, pokemon.name) && types == pokemon.types;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hp, types);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + hp +
                ", types=" + types +
                '}';
    }
}
