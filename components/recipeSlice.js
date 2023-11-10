/* eslint-disable prettier/prettier */
import { createSlice } from "@reduxjs/toolkit";
import { RECIPES } from "../data";

const recipeSlice = createSlice({
    name: "recipe",
    initialState: {
        recipes: [],
        selectedRecipe: null
    },
    reducers: {
        getAllRecipes : (state) => {
            state.recipes = RECIPES;
        },
        selectOneRecipe : (state,action) => {
            state.selectedRecipe = state.recipes.find(recipe => recipe.id === action);
        }
    }
});

export const {getAllRecipes, selectOneRecipe} = recipeSlice.actions;
export default recipeSlice.reducer;