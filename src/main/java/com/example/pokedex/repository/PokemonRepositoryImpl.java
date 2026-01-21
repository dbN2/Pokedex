package com.example.pokedex.repository;

import com.example.pokedex.mapper.PokemonRowMapper;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class PokemonRepositoryImpl implements PokemonRepository {
    private static final String FIND_ONE_SQL =
            """
            SELECT      p.*,
                        array_agg(t.pokemon_type) as types,
                        evo.name as evolves_from
                    FROM pokemon p
                    LEFT JOIN pokemon_types pt ON p.id = pt.pokemon_id
                    LEFT JOIN types t ON t.id = pt.type_id
                    LEFT JOIN pokemon evo ON p.evolves_from_id = evo.id
                    WHERE p.id = ?
                    GROUP BY p.id;
            """;
    private static final String FIND_ALL_SQL =
            """
                  SELECT
                              p.*,
                              array_agg(t.pokemon_type ORDER BY pt.slot) as types,
                              evo.name as evolves_from
                          FROM pokemon p
                          LEFT JOIN pokemon_types pt ON p.id = pt.pokemon_id
                          LEFT JOIN types t ON pt.type_id = t.id
                          LEFT JOIN pokemon evo ON p.evolves_from_id = evo.id
                          GROUP BY p.id
                          ORDER BY p.pokedex_number;
            """;
    private static final String FIND_BY_TYPE_SQL = """
            SELECT
                              p.*,
                              array_agg(t.pokemon_type ORDER BY pt.slot) as types
                          FROM pokemon p
                          LEFT JOIN pokemon_types pt ON p.id = pt.pokemon_id
                          LEFT JOIN types t ON pt.type_id = t.id
                          WHERE t.pokemon_type = ?
                          GROUP BY p.id
                          ORDER BY p.pokedex_number;
            """;
    private final JdbcTemplate jdbc;
    private final PokemonRowMapper mapper;

    public PokemonRepositoryImpl(JdbcTemplate jdbc, PokemonRowMapper mapper) {
        this.jdbc = jdbc;
        this.mapper = mapper;
    }

    @Override
    public Pokemon findById(Long id) {
        return jdbc.queryForObject(FIND_ONE_SQL, mapper, id);
    }

    @Override
    public List<Pokemon> findAll() {
        return jdbc.query(
                FIND_ALL_SQL, mapper);
    }

    @Override
    public List<Pokemon> findByType(PokemonType type) {
        String pokemonType = type.toString();
        return jdbc.query(
                FIND_ALL_SQL, mapper, pokemonType);
    }

    @Override
    public void save(Pokemon pokemon) {
        // insert logic
    }
}
