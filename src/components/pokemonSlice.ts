/* eslint-disable @typescript-eslint/no-explicit-any */
import { Pokemon } from './../models/Pokemon';
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { RootState } from "../config/store";
import axios from "axios";

const POKEMON_URL = "https://pokeapi.co/api/v2/pokemon?limit=300";

async function extractPokemonFromRawData(pokemonUrl:string) : Pokemon{
    const response = await axios.get(pokemonUrl);
    const pokemon:Pokemon = {
        name : response.data.name,
        height: response.data.height,
        weight: response.data.weight,
        base_experience: response.data.base_expercience,
        abilities : response.data.abilities.map((ability:any) => ability.name),
        type: response.data.types.map((type:any) => type.type.name),
        moves : response.data.moves.map((move:any) => move.move.name),
        sprites : []
    };
    for (const key in response.data.sprites){
        if (response.data.sprites[key]){
            pokemon.sprites.push(response.data.sprites[key]);
        }
    }
    return pokemon;
}

export const fetchPokemons = createAsyncThunk(
    "pokemon/get300",
    async () => {
        const response = await axios.get(POKEMON_URL);
        const fetchedPokemons:Pokemon[]  = [];
        response.data.results.forEach( (result:{name:string,url:string}) => {
            fetchedPokemons.push(extractPokemonFromRawData(result.url));
        })
    }
);

export const getOnePokemon = createAsyncThunk(
    "pokemon/getOne",
    async (url:string) => {
       
    }
)

const initialState = {
    pokemons : [],
    selectedPokemon : null
} as {
    pokemons : Pokemon[],
    selectedPokemon : Pokemon | null
};

const pokemonSlice = createSlice({
    name: "pokemon",
    initialState : initialState,
    reducers : {},
    extraReducers : (builder) => {

    }
});

export default pokemonSlice.reducer;
export const pokemonSelector = (state:RootState) => state.pokemon