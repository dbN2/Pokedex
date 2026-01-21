package com.example.pokedex.mapper;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokemonRowMapper implements RowMapper<Pokemon> {

    @Override
    public Pokemon mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pokemon pokemon = new Pokemon();

        // non null
        pokemon.setId(rs.getLong("id"));
        pokemon.setPokedexNumber(rs.getInt("pokedex_number"));
        pokemon.setName(rs.getString("pokemon_name"));

        // Handle nullable stats
        pokemon.setHp(getNullableInt(rs, "hp"));
        pokemon.setAtk(getNullableInt(rs, "atk"));
        pokemon.setDef(getNullableInt(rs, "def"));
        pokemon.setSpatk(getNullableInt(rs, "spatk"));
        pokemon.setSpdef(getNullableInt(rs, "spdef"));
        pokemon.setSpd(getNullableInt(rs, "spd"));

        pokemon.setTypes(getTypesForPokemon(rs, "types"));
        pokemon.setEvolvesFrom(rs.getString("evolves_from"));
        return pokemon;
    }

    private Integer getNullableInt(ResultSet rs, String column) throws SQLException {
        int value = rs.getInt(column);
        return rs.wasNull() ? value : null;
    }

    private List<PokemonType> getTypesForPokemon(ResultSet rs, String column) throws SQLException {
        // convert sql array to usable list
        java.sql.Array types = rs.getArray(column);
        String[] stringArray = (String []) types.getArray();
        List<String> stringList = Arrays.asList(stringArray);

        List<PokemonType> typesList = new ArrayList<>();
        stringList.forEach((k) -> {
                PokemonType pokemonType = new PokemonType();
                pokemonType.setType(PokemonType.getEnumFromString(k));
                typesList.add(pokemonType);
        });

        return typesList;
    }
}