package com.example.pokedex.controller;

import com.example.pokedex.exception.UnknownErrorException;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import com.example.pokedex.model.dto.CreatePokemonRequest;
import com.example.pokedex.model.dto.PokemonDto;
import com.example.pokedex.model.dto.Response;
import com.example.pokedex.service.PokedexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokedex")
public class PokedexController {
    private final PokedexService service;
    private static final Logger log = LoggerFactory.getLogger(PokedexController.class);

    public PokedexController(PokedexService service) {
        this.service = service;
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<Response> getPokemon(@PathVariable Long id) {
        if (id == null) {
            return createBadRequestResponse();
        }
        try {
            List<PokemonDto> pokemonResponse = mapToDto(service.findPokemonById(id));
            if (pokemonResponse.isEmpty()) {
                return createNotFoundResponse();
            }
            return createOkResponse(pokemonResponse, 200);
        } catch (UnknownErrorException e) {
            return createUnknownErrorResponse();
        }
    }

    @GetMapping("/pokemon")
    public ResponseEntity<Response> getAllPokemon() {
        try {
            List<PokemonDto> pokemonResponse = mapToDto(service.findAllPokemon());
            if (pokemonResponse.isEmpty()) {
                return createNotFoundResponse();
            }
            return createOkResponse(pokemonResponse, 200);
        } catch (UnknownErrorException e) {
            return createUnknownErrorResponse();
        }
    }

    @GetMapping("/pokemon/type/{type}")
    public ResponseEntity<Response> getPokemonByType(@PathVariable String type) {
        if (type == null || !PokemonType.containsType(type)) {
            return createBadRequestResponse();
        }
        try {
            PokemonType.Type pokemonType = PokemonType.getEnumFromString(type);
            List<PokemonDto> pokemonResponse = mapToDto(service.findPokemonByType(pokemonType));
            if (pokemonResponse.isEmpty()) {
                return createNotFoundResponse();
            }
            return createOkResponse(pokemonResponse, 200);
        } catch (UnknownErrorException e) {
            return createUnknownErrorResponse();
        }
    }

    @GetMapping("/types")
    public ResponseEntity<Response> getAllTypes() {
        try {
            List<PokemonType> types = service.findAllTypes();
            return createOkResponse(200, types);
        } catch (UnknownErrorException e) {
            return createUnknownErrorResponse();
        }
    }

    @PostMapping("/pokemon")
    public ResponseEntity<Response> createPokemon(@RequestBody CreatePokemonRequest request) {
        if (isInvalidCreateRequest(request)) {
            return createBadRequestResponse();
        }
        try {
            Optional<Pokemon> created = service.createPokemon(request);
            return createOkResponse(mapToDto(created), 201);
        } catch (UnknownErrorException e) {
            return createUnknownErrorResponse();
        }
    }

    private List<PokemonDto> mapToDto(Optional<Pokemon> pokemonOptional) {
        if (pokemonOptional.isEmpty()) return List.of();

        Pokemon pokemon = pokemonOptional.get();
        var response = new PokemonDto.Builder()
                .id(pokemon.getId())
                .pokedexNumber(pokemon.getPokedexNumber())
                .name(pokemon.getName())
                .hp(pokemon.getHp())
                .def(pokemon.getDef())
                .atk(pokemon.getAtk())
                .spd(pokemon.getSpd())
                .spatk(pokemon.getSpatk())
                .spdef(pokemon.getSpdef())
                .types(pokemon.getTypes())
                .evolvesFrom(pokemon.getEvolvesFrom());

        return List.of(response.build());
    }

    private List<PokemonDto> mapToDto(List<Pokemon> pokemonList) {
        if (pokemonList.isEmpty()) return List.of();

        List<PokemonDto> pokemonDtos = new ArrayList<>();

        pokemonList.forEach((pokemon) -> {
            PokemonDto dto = new PokemonDto.Builder()
                    .id(pokemon.getId())
                    .pokedexNumber(pokemon.getPokedexNumber())
                    .name(pokemon.getName())
                    .hp(pokemon.getHp())
                    .def(pokemon.getDef())
                    .atk(pokemon.getAtk())
                    .spd(pokemon.getSpd())
                    .spatk(pokemon.getSpatk())
                    .spdef(pokemon.getSpdef())
                    .types(pokemon.getTypes())
                    .evolvesFrom(pokemon.getEvolvesFrom())
                    .build();
            pokemonDtos.add(dto);
        });

        return pokemonDtos;
    }

    private ResponseEntity<Response> createBadRequestResponse() {
        Response response = new Response("Request contains invalid or missing values", List.of(), List.of());
        return ResponseEntity.badRequest().body(response);
    }

    private ResponseEntity<Response> createNotFoundResponse() {
        Response response = new Response("", List.of(), List.of());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(404));
    }

    private ResponseEntity<Response> createUnknownErrorResponse() {
        Response response = new Response("Encountered internal error", List.of(), List.of());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
    }

    private ResponseEntity<Response> createOkResponse(List<PokemonDto> pokemon, Integer httpCode) {
        Response response = new Response("", pokemon, List.of());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(httpCode));
    }

    private ResponseEntity<Response> createOkResponse(Integer httpCode, List<PokemonType> pokemonTypes) {
        Response response = new Response("", List.of(), pokemonTypes);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(httpCode));
    }

    private boolean isInvalidCreateRequest(CreatePokemonRequest request) {
        // Request requires pokedex number, name, types, and it can't have both evolution id and name
        return request.getPokedexNumber() == null || request.getName().isBlank()
                || request.getTypes() == null || request.getTypes().isEmpty()
                || (request.getEvolvesFromId() != null && !request.getEvolvesFrom().isBlank());
    }
}
