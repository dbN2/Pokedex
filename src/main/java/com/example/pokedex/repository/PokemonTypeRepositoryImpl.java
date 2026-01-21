package com.example.pokedex.repository;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private final JdbcTemplate jdbc;
    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM types WHERE id = ?
            """;

    public PokemonTypeRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public PokemonType findById(Long id) {
        return jdbc.queryForObject(FIND_BY_ID_SQL,
                (rs, rowNum) -> new PokemonType(
                        rs.getString("pokemon_type")
                ), id);
    }

    @Override
    public List<PokemonType> findAll() {
        return List.of();
    }

    @Override
    public void save(PokemonType skill) {

    }
}
