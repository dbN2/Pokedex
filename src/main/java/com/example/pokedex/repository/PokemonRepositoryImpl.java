package com.example.pokedex.repository;

import com.example.pokedex.mapper.PokemonRowMapper;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import com.example.pokedex.model.dto.CreatePokemonRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonRepositoryImpl implements PokemonRepository {
    private static final String FIND_BY_ID_SQL =
            """
            SELECT      p.*,
                        array_agg(t.pokemon_type) as types,
                        evo.name as evolves_from
                    FROM pokemon p
                    LEFT JOIN pokemon_types pt ON p.id = pt.pokemon_id
                    LEFT JOIN types t ON t.id = pt.type_id
                    LEFT JOIN pokemon evo ON p.evolves_from_id = evo.id
                    WHERE p.id = ?
                    GROUP BY p.id, evo.name;
            """;
    private static final String FIND_BY_NAME_SQL =
            """
            SELECT      p.*,
                        array_agg(t.pokemon_type) as types,
                        evo.name as evolves_from
                    FROM pokemon p
                    LEFT JOIN pokemon_types pt ON p.id = pt.pokemon_id
                    LEFT JOIN types t ON t.id = pt.type_id
                    LEFT JOIN pokemon evo ON p.evolves_from_id = evo.id
                    WHERE p.name = ?
                    GROUP BY p.id, evo.name;
            """;
    private static final String FIND_ALL_SQL =
            """
                  SELECT
                              p.*,
                              array_agg(t.pokemon_type) as types,
                              evo.name as evolves_from
                          FROM pokemon p
                          LEFT JOIN pokemon_types pt ON p.id = pt.pokemon_id
                          LEFT JOIN pokemon evo ON p.evolves_from_id = evo.id
                          LEFT JOIN types t ON pt.type_id = t.id
                          GROUP BY p.id, evo.name
                          ORDER BY p.pokedex_number;
            """;
    private static final String FIND_BY_TYPE_SQL = """
            SELECT
                              p.*,
                              array_agg(t.pokemon_type) as types
                          FROM pokemon p
                          LEFT JOIN pokemon_types pt ON p.id = pt.pokemon_id
                          LEFT JOIN pokemon evo ON p.evolves_from_id = evo.id
                          LEFT JOIN types t ON pt.type_id = t.id
                          WHERE t.pokemon_type = ?
                          GROUP BY p.id
                          ORDER BY p.pokedex_number;
            """;
    private static final String CREATE_POKEMON_SQL = """
            INSERT INTO pokemon (pokedex_number, name, hp, atk, def, spd, spatk, spdef, evolves_from_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING id
            """;
    private static final String CREATE_POKEMON_TYPE_MAPPING_SQL = """
            INSERT INTO pokemon_types (pokemon_id, type_id)
            VALUES (?, ?)
            """;

    private final JdbcTemplate jdbc;
    private final PokemonRowMapper mapper;

    public PokemonRepositoryImpl(JdbcTemplate jdbc, PokemonRowMapper mapper) {
        this.jdbc = jdbc;
        this.mapper = mapper;
    }

    @Override
    public Pokemon findById(Long id) {
        return jdbc.queryForObject(FIND_BY_ID_SQL, mapper, id);
    }

    @Override
    public Pokemon findByName(String name) {
        return jdbc.queryForObject(FIND_BY_NAME_SQL, mapper, name);
    }

    @Override
    public List<Pokemon> findAll() {
        return jdbc.query(
                FIND_ALL_SQL, mapper);
    }

    @Override
    public List<Pokemon> findByType(PokemonType.Type type) {
        String pokemonType = type.toString();
        return jdbc.query(
                FIND_ALL_SQL, mapper, pokemonType);
    }

    @Override
    public Long createPokemon(CreatePokemonRequest request) {
        return jdbc.queryForObject(
                CREATE_POKEMON_SQL,
                Long.class,
                request.getPokedexNumber(),
                request.getName(),
                request.getHp(),
                request.getAtk(),
                request.getDef(),
                request.getSpd(),
                request.getSpatk(),
                request.getSpdef(),
                request.getEvolvesFromId()
                );
    }
}
