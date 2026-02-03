package com.example.pokedex.mapper;

import com.example.pokedex.model.PokemonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

@Component
public class PokemonTypeMapper implements RowMapper<PokemonType> {

    @Override
    public PokemonType mapRow(ResultSet rs, int rowNum) throws SQLException {
        PokemonType.Type type = PokemonType.getEnumFromString(rs.getString("pokemon_type"));
        Long id = rs.getLong("id");
        Instant created = rs.getTimestamp("created").toInstant();
        Instant updated = rs.getTimestamp("updated").toInstant();

        return new PokemonType(id, type.toString(), created, updated);
    }

}
