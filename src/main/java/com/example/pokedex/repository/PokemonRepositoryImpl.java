package com.example.pokedex.repository;

import com.example.pokedex.mapper.PokemonRowMapper;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import com.example.pokedex.model.dto.PokemonDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
            INSERT INTO pokemon (pokedex_number, name, hp, atk, def, spd, spatk, spdef, evo_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
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
        return jdbc.queryForObject(FIND_ONE_SQL, mapper, id);
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
    public Pokemon createPokemon(PokemonDto pokemon) {
        Long generatedId = jdbc.queryForObject(
                CREATE_POKEMON_SQL,
                Long.class,
                pokemon.getPokedexNumber(),
                pokemon.getHp(),
                pokemon.getDef(),   // must exist in abilities table
                pokemon.getAtk()    // optional, can be null
        );
        // insert logic
        return new Pokemon();
    }
}
