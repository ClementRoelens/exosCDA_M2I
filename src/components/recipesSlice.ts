import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { Ingredient } from "../models/Ingredient";
import { Recipe } from "../models/Recipe";
import { createRecipe } from "../services/RecipeService";

const initialState = {
    formMode: "",
    recipes: [],
    selectedRecipe: null,
    ingredients: [],
    isLoading: false,
    error: null
} as {
    formMode: string,
    recipes: Recipe[],
    selectedRecipe: Recipe | null,
    ingredients: Ingredient[],
    isLoading: boolean,
    error: Error | null
};

const recipeSlice = createSlice({
    name: "recipe",
    initialState: initialState,
    reducers: {
        setRecipes: (state, action: PayloadAction<Recipe[]>) => {
            state.recipes = action.payload;
        },
        addRecipe: (state, action: PayloadAction<Recipe>) => {
            state.recipes.push(action.payload);
        },
        getAllRecipes: (state, action: PayloadAction<Recipe[]>) => {
            state.recipes = action.payload;
        },
        modifyRecipe: (state, action: PayloadAction<Recipe>) => {
            const index = state.recipes.findIndex((recipe: Recipe) => recipe.id === action.payload.id);
            state.recipes[index] = action.payload;
            state.selectedRecipe = action.payload;
        },
        deleteRecipe: (state, action: PayloadAction<string>) => {
            state.recipes = state.recipes.filter((recipe: Recipe) => recipe.id !== action.payload);
            state.selectedRecipe = (state.recipes.length > 0) ? state.recipes[0] : null;
        }
    }
});

export const { setRecipes, addRecipe, getAllRecipes, modifyRecipe, deleteRecipe } = recipeSlice.actions;
export default recipeSlice.reducer;
export const recipeSelector = (state: RootState) => state.recipe;