## API Documentation

Base URL: `http://localhost:8080`

### Endpoints

#### Get All Pokemon
```
GET /pokedex/pokemon
```
Returns a list of all Pokemon.

**Response:**
```json
{
  "body": "string",
  "pokemon": [
    {
      "id": 0,
      "pokedexNumber": 0,
      "name": "string",
      "hp": 0,
      "atk": 0,
      "def": 0,
      "spd": 0,
      "spatk": 0,
      "spdef": 0,
      "types": [
        {
          "id": 0,
          "type": "FIRE"
        }
      ],
      "evolvesFrom": "string"
    }
  ],
  "types": []
}
```

---

#### Get Pokemon by ID
```
GET /pokedex/pokemon/{id}
```
Returns a specific Pokemon by ID.

**Parameters:**
- `id` (path, required): Pokemon ID

**Response:** Same as Get All Pokemon

---

#### Get Pokemon by Type
```
GET /pokedex/pokemon/type/{type}
```
Returns all Pokemon of a specific type.

**Parameters:**
- `type` (query, required): Pokemon type (FIRE, WATER, ICE, MAGMA, ELECTRIC, FIGHTING, GRASS, DARK, GROUND, FLYING, PSYCHIC, UNKNOWN)

**Response:** Same as Get All Pokemon

---

#### Get All Types
```
GET /pokedex/types
```
Returns all available Pokemon types.

**Response:**
```json
{
  "body": "string",
  "pokemon": [],
  "types": [
    {
      "id": 0,
      "type": "FIRE"
    }
  ]
}
```

---

#### Create Pokemon
```
POST /pokedex/pokemon
```
Creates a new Pokemon.

**Request Body:**
```json
{
  "id": 0,
  "pokedexNumber": 0,
  "hp": 0,
  "atk": 0,
  "def": 0,
  "spd": 0,
  "spatk": 0,
  "spdef": 0,
  "types": [
    {
      "id": 0,
      "type": "FIRE"
    }
  ],
  "evolvesFrom": "string"
}
```

**Response:** Same as Get All Pokemon

---

### Available Pokemon Types
- FIRE
- WATER
- ICE
- MAGMA
- ELECTRIC
- FIGHTING
- GRASS
- DARK
- GROUND
- FLYING
- PSYCHIC
- UNKNOWN
