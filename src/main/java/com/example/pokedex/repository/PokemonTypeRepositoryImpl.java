package com.example.pokedex.repository;

import com.example.pokedex.mapper.PokemonTypeMapper;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private final JdbcTemplate jdbc;
    private final PokemonTypeMapper mapper;

    private static final String FIND_BY_NAME_SQL = "SELECT * FROM types where pokemon_type = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM types";

    public PokemonTypeRepositoryImpl(JdbcTemplate jdbc, PokemonTypeMapper mapper) {
        this.jdbc = jdbc;
        this.mapper = mapper;
    }

    @Override
    public PokemonType findByName(String name) throws DataAccessException {
        return jdbc.queryForObject(FIND_BY_NAME_SQL, mapper, name);
    }

    @Override
    public List<PokemonType> findAll() {
        return jdbc.query(FIND_ALL_SQL, mapper);
    }
}
