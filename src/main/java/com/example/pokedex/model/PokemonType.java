package com.example.pokedex.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PokemonType {
    private static final Logger log = LoggerFactory.getLogger(PokemonType.class);
    private Long id;
    private Type type;
    private Instant created;
    private Instant updated;

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
        POISON,
        UNKNOWN;
    }

    public PokemonType() {

    }

    public PokemonType(Long id, String type, Instant created, Instant updated) {
        this.id = id;
        this.type = PokemonType.getEnumFromString(type);
        this.created = created;
        this.updated = updated;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public static Type getEnumFromString(String type) {
        try {
            return Type.valueOf(type);
        } catch (Exception e) {
            log.error("Invalid type encountered when parsing PokemonType {}", type, e);
            return Type.UNKNOWN;
        }
    }

    public static boolean containsType(String type) {
        if (Type.UNKNOWN.toString().equalsIgnoreCase(type)) return false;

            List<Type> types = Arrays.stream(Type.values()).toList();
        for (Type t: types) {
            if (t.toString().equalsIgnoreCase(type)) {
                return true;
            }
        };
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PokemonType that = (PokemonType) o;
        return Objects.equals(id, that.id) && type == that.type && Objects.equals(created, that.created) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, created, updated);
    }

    @Override
    public String toString() {
        return "PokemonType{" +
                "id=" + id +
                ", type=" + type +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}

