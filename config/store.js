import pokemonSlice from "../components/pokemonSlice";
import { configureStore } from "@reduxjs/toolkit";

export const store = configureStore({
    reducer : {
        pokemon : pokemonSlice
    }
});