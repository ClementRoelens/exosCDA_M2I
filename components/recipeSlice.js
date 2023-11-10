/* eslint-disable prettier/prettier */
import { createSlice } from "@reduxjs/toolkit";
import { RECIPES } from "../data";

function parseRecipes(recipes){
    const parsedRecipes = [];
    recipes.forEach(recipe => {
        parsedRecipes.push({
            id: recipe.id,
            categoryIds: recipe.categoryIds,
            title: recipe.title,
            affordability: recipe.affordability,
            complexity: recipe.complexity,
            imageUrl: recipe.imageUrl,
            duration: recipe.duration,
            ingredients: recipe.ingredients,
            steps: recipe.steps,
            isGlutenFree: recipe.isGlutenFree,
            isVegan: recipe.isVegan,
            isVegetarian: recipe.isVegetarian,
            isLactoseFree: recipe.isLactoseFree
        })
    });

    return parsedRecipes;
}

const recipeSlice = createSlice({
    name: "recipe",
    initialState: {
        recipes: [],
        selectedRecipe: null
    },
    reducers: {
        getAllRecipes: (state) => {
            const recipes = RECIPES;
            state.recipes = parseRecipes(recipes);
        },
        selectOneRecipe: (state, action) => {
            state.selectedRecipe = state.recipes.find(recipe => recipe.id === action.payload);
        },
        selectRecipesFromOneCategory: (state, action) => {
            const recipes = RECIPES.filter(recipe => recipe.categoryIds.includes(action.payload));
            state.recipes = parseRecipes(recipes);
        }
    }
});

export const { getAllRecipes, selectOneRecipe, selectRecipesFromOneCategory } = recipeSlice.actions;
export default recipeSlice.reducer;