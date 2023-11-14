import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";

const BASE_URL = "https://pokeapi.co/api/v2/pokemon";

async function parsePokemon(rawPokemon) {
    let types = [];
    rawPokemon.types.forEach(type => {
        types.push(type.type.name);
    });
    try {
        const speciesInfos = await axios.get(rawPokemon.species.url);
        const description = speciesInfos.data.flavor_text_entries.find(flavor_text => flavor_text.language.name === "en");
        const pokemon = {
            id: rawPokemon.id,
            name: rawPokemon.name,
            types: types,
            sprite: rawPokemon.sprites.front_default,
            height: rawPokemon.height,
            weight: rawPokemon.weight,
            description: description.flavor_text.split("\n").join(" "),
            color: speciesInfos.data.color.name,
            evolutionChainUrl: speciesInfos.data.evolution_chain.url
        };
        return pokemon;
    } catch (error) {
        console.error("Erreur lors du parse des Pokémons", error);
        return error;
    }
}

async function getOnePokemon(url) {
    try {
        const rawPokemon = await axios.get(url);
        const pokemon = await parsePokemon(rawPokemon.data);
        return pokemon;
    }
    catch (error) {
        console.error("Erreur lors de la récupération du Pokémondepuis " + url, error);
    }
}

export const getAllPokemons = createAsyncThunk(
    "pokemon/get",
    async () => {
        try {
            const pokemonInfos = await axios.get(BASE_URL + "?offset=0&limit=251");
            const pokemonUrls = pokemonInfos.data.results.map(pokemon => pokemon.url);
            const pokemons = await Promise.all(pokemonUrls.map(async url => {
                const rawPokemon = await axios.get(url);
                return await parsePokemon(rawPokemon.data);
            }));
            return pokemons
        } catch (error) {
            console.error("Erreur lors de la récupération des Pokemons", error)
        }
    }
);

export const getFamily = createAsyncThunk(
    "pokemon/getFamily",
    async (url) => {
        try {
            const evolutionChain = await axios.get(url);

            const firstName = evolutionChain.data.chain.species.name
            const familyNames = [firstName];

            if (evolutionChain.data.chain.evolves_to.length > 0) {
                familyNames.push(evolutionChain.data.chain.evolves_to[0].species.name);
                if (evolutionChain.data.chain.evolves_to[0].evolves_to.length > 0) {
                    familyNames.push(evolutionChain.data.chain.evolves_to[0].evolves_to[0].species.name);
                }
            }

            const family = await Promise.all(familyNames.map(async name => {
                return await getOnePokemon(`${BASE_URL}/${name}`);
            }));

            return family;
        } catch (error) {
            console.error("Erreur lors du getFamily", error)
        }
    }
);

const pokemonSlice = createSlice({
    name: "pokemon",
    initialState: {
        pokemons: [],
        selectedFamily: [],
        favedPokemons : []
    },
    reducers: {
        resetFamily: (state, action) => {
            state.selectedFamily = [];
        },
        addToFavs : (state,action) => {
            state.favedPokemons.push(action.payload);
        },
        removeFromFavs : (state,action) => {
            const index = state.favedPokemons.findIndex(pokemon => pokemon.id === action.payload.id);
            state.favedPokemons.splice(index,1);
        }
    },
    extraReducers: (builder) => {
        builder.addCase(getAllPokemons.fulfilled, (state, action) => {
            state.pokemons = action.payload;
        }),
            builder.addCase(getAllPokemons.rejected, (state, action) => {
                console.error("Erreur lors de la récupération des pokémons", action.error);
            }),
            builder.addCase(getFamily.fulfilled, (state, action) => {
                state.selectedFamily = action.payload;
            }),
            builder.addCase(getFamily.rejected, (state, action) => {
                console.error("Erreur lors de la récupération d'une famille", action.error);
            })
    }
});

export const { resetFamily } = pokemonSlice.actions;
export default pokemonSlice.reducer;