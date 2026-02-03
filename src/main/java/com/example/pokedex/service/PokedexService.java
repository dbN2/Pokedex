package com.example.pokedex.service;

import com.example.pokedex.exception.RowAlreadyExistsException;
import com.example.pokedex.exception.RowNotFoundException;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import com.example.pokedex.model.PokemonTypeAssignment;
import com.example.pokedex.model.dto.CreatePokemonRequest;
import com.example.pokedex.repository.PokemonRepository;
import com.example.pokedex.repository.PokemonTypeAssignmentsRepository;
import com.example.pokedex.repository.PokemonTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokedexService {
    private static final Logger log = LoggerFactory.getLogger(PokedexService.class);
    private PokemonRepository pokemonRepository;
    private PokemonTypeRepository pokemonTypeRepository;
    private PokemonTypeAssignmentsRepository pokemonTypeAssignmentsRepository;

    public PokedexService(PokemonRepository pokemonRepository, PokemonTypeRepository pokemonTypeRepository, PokemonTypeAssignmentsRepository pokemonTypeAssignmentsRepository) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.pokemonTypeAssignmentsRepository = pokemonTypeAssignmentsRepository;
    }

    public Optional<Pokemon> findPokemonById(Long id) {
        try {
            return Optional.ofNullable(pokemonRepository.findById(id));
        }
        catch (EmptyResultDataAccessException e) {
            throw new RowNotFoundException();
        }
    }

    public List<Pokemon> findAllPokemon() {
        try {
            return pokemonRepository.findAll();
        }
        catch (EmptyResultDataAccessException e) {
            throw new RowNotFoundException();
        }
    }

    public List<Pokemon> findPokemonByType(PokemonType.Type type) {
        try {
            return pokemonRepository.findByType(type);
        }
        catch (EmptyResultDataAccessException e) {
            throw new RowNotFoundException();
        }
    }

    public List<PokemonType> findAllTypes() {
        try {
            return pokemonTypeRepository.findAll();
        }
        catch (EmptyResultDataAccessException e) {
            throw new RowNotFoundException();
        }
    }

    public Pokemon createPokemon(CreatePokemonRequest request) {
        try {
            // Saturate id after querying name if present
            if (request.getEvolvesFrom() != null && !request.getEvolvesFrom().isEmpty()) {
                Pokemon queried = pokemonRepository.findByName(request.getEvolvesFrom());
                if (queried != null && queried.getId() != null) {
                    request.setEvolvesFromId(queried.getId());
                }
            }
            request.setName((request.getName().toUpperCase()));
            Long createdId = pokemonRepository.createPokemon(request);
            // Create pokemon type assignments
            request.getTypes().forEach((type) -> {
                Long typeId = pokemonTypeRepository.findByName(type.toString()).getId();
                PokemonTypeAssignment assignment = new PokemonTypeAssignment(createdId, typeId);
                pokemonTypeAssignmentsRepository.createPokemonTypeAssignment(assignment);
            });

            return pokemonRepository.findById(createdId);
        } catch (EmptyResultDataAccessException e) {
            throw new RowNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new RowAlreadyExistsException();
        }
    }
}
