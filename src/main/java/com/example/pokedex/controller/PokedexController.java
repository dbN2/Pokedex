package com.example.pokedex.controller;

import com.example.pokedex.exception.UnknownErrorException;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.PokemonType;
import com.example.pokedex.model.dto.PokemonDto;
import com.example.pokedex.model.dto.Response;
import com.example.pokedex.service.PokedexService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PokedexController {
    private final PokedexService service;

    public PokedexController(PokedexService service) {
        this.service = service;
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<Response> getPokemon(@PathVariable Long id) {
        Response response;
        if (id == null) {
            return createBadRequestResponse();
        }

        try {
            List<PokemonDto> pokemonResponse = mapToDto(service.findPokemonById(id));
            if (pokemonResponse.isEmpty()) {
                return createNotFoundResponse();
            }
            return createOkResponse(pokemonResponse);
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
            return createOkResponse(pokemonResponse);
        } catch (UnknownErrorException e) {
            return createUnknownErrorResponse();
        }
    }

    @GetMapping("/pokemon/type/{type}")
    public ResponseEntity<Response> getAllPokemon(String type) {
        if (!PokemonType.containsType(type)) {
            return createBadRequestResponse();
        }

        try {
            //TODO findByType
            List<PokemonDto> pokemonResponse = mapToDto(service.findAllPokemon());
            if (pokemonResponse.isEmpty()) {
                return createNotFoundResponse();
            }
            return createOkResponse(pokemonResponse);
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
        Response response = new Response("Request contains invalid or missing values", List.of());
        return ResponseEntity.badRequest().body(response);
    }

    private ResponseEntity<Response> createNotFoundResponse() {
        Response response = new Response("", List.of());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(404));
    }

    private ResponseEntity<Response> createUnknownErrorResponse() {
        Response response = new Response("Encountered unknown error", List.of());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
    }

    private ResponseEntity<Response> createOkResponse(List<PokemonDto> pokemon) {
        Response response = new Response("", pokemon);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }
}
