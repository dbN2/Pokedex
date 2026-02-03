package com.example.pokedex.repository;

import com.example.pokedex.model.PokemonTypeAssignment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PokemonTypeAssignmentRepositoryImpl implements PokemonTypeAssignmentsRepository {

    private final JdbcTemplate jdbc;
    private static final String CREATE_SQL = "INSERT INTO pokemon_types (pokemon_id, type_id) VALUES (?, ?)";

    public PokemonTypeAssignmentRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void createPokemonTypeAssignment(PokemonTypeAssignment assignment) {
        jdbc.update(CREATE_SQL);
    }
}
