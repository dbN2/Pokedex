package com.example.pokedex.service;

import com.example.pokedex.exception.UnknownErrorException;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import com.example.pokedex.model.dto.CreatePokemonRequest;
import com.example.pokedex.model.dto.PokemonDto;
import com.example.pokedex.repository.PokemonRepository;
import com.example.pokedex.repository.PokemonTypeAssignmentsRepository;
import com.example.pokedex.repository.PokemonTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
        catch (DataAccessException e) {
            log.error("Encountered database error", e);
            throw new UnknownErrorException();
        }
        catch (RuntimeException e) {
            log.error("Encountered unexpected error when getting pokemon by id", e);
            throw new UnknownErrorException();
        }
    }

    public List<Pokemon> findAllPokemon() {
        try {
            return pokemonRepository.findAll();
        }
        catch (DataAccessException e) {
            log.error("Encountered database error when getting all pokemon ", e);
            throw new UnknownErrorException();
        }
        catch (RuntimeException e) {
            log.error("Encountered unexpected error when getting all pokemon ", e);
            throw new UnknownErrorException();
        }
    }

    public List<Pokemon> findPokemonByType(PokemonType.Type type) {
        try {
            return pokemonRepository.findByType(type);
        }
        catch (DataAccessException e) {
            log.error("Encountered database error when getting pokemon by type", e);
            throw new UnknownErrorException();
        }
        catch (RuntimeException e) {
            log.error("Encountered unexpected error when getting pokemon by type", e);
            throw new UnknownErrorException();
        }
    }

    public List<PokemonType> findAllTypes() {
        try {
            return pokemonTypeRepository.findAll();
        }
        catch (DataAccessException e) {
            log.error("Encountered database error when getting types", e);
            throw new UnknownErrorException();
        }
        catch (RuntimeException e) {
            log.error("Encountered unexpected error when getting types", e);
            throw new UnknownErrorException();
        }
    }

    public Optional<Pokemon> createPokemon(CreatePokemonRequest request) {
        try {
            // Saturate id after querying name if present
            if (!request.getEvolvesFrom().isEmpty()) {
            Pokemon queried = pokemonRepository.findByName(request.getName());
            if (queried != null && queried.getId() != null) {
                request.setEvolvesFromId(queried.getId());
            }
            }
            Optional<Pokemon> created = Optional.ofNullable(pokemonRepository.createPokemon(request));
            if (created.isEmpty()) {
                log.error("Failed to create Pokemon with request {}", request);
                throw new UnknownErrorException();
            }
            return created;
        } catch (DataAccessException e) {
            log.error("Encountered database error when creating pokemon", e);
            throw new UnknownErrorException();
        } catch (RuntimeException e) {
            log.error("Encountered unexpected error when creating pokemon", e);
            throw new UnknownErrorException();
        }
    }
}
