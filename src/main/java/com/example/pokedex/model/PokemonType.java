package com.example.pokedex.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class PokemonType {
    private static final Logger log = LoggerFactory.getLogger(PokemonType.class);
    private Long id;
    private Type type;

    public enum Type {
        FIRE,
        WATER,
        ICE,
        MAGMA,
        ELECTRIC,
        FIGHTING,
        GRASS,
        DARK,
        GROUND,
        FLYING,
        PSYCHIC,
        UNKNOWN;
    }

    public PokemonType() {

    }

    public PokemonType(String type) {
        this.id = id;
        this.type = PokemonType.getEnumFromString(type);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static Type getEnumFromString(String type) {
        try {
            return Type.valueOf(type);
        } catch (Exception e) {
            log.error("Invalid type encountered when parsing PokemonType");
            return Type.UNKNOWN;
        }
    }

    public static boolean containsType(String type) {
        List<Type> types = Arrays.stream(Type.values()).toList();
        for (Type t: types) {
            if (t.toString().equalsIgnoreCase(type)) {
                return true;
            }
        };
        return false;
    }
}

