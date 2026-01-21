CREATE TABLE IF NOT EXISTS pokemon (
                                       id SERIAL PRIMARY KEY,
                                       pokedex_number INTEGER UNIQUE NOT NULL,
                                       name VARCHAR(25) NOT NULL,
    hp INTEGER,
    atk INTEGER,
    spd INTEGER,
    def INTEGER,
    spatk INTEGER,
    spdef INTEGER,
    evolves_from_id INT REFERENCES pokemon_species(species_id),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS types (
                                        id SERIAL PRIMARY KEY,
                                        pokemon_type VARCHAR(20) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS pokemon_types (
                                     pokemon_id INTEGER NOT NULL REFERENCES pokemon(id) ON DELETE CASCADE,
    type_id INTEGER NOT NULL REFERENCES types(id),
    PRIMARY KEY (pokemon_id, type_id),
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
