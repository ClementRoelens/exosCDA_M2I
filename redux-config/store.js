import { configureStore } from "@reduxjs/toolkit";
import recipeSlice from "../components/recipeSlice";
import categorySlice from "../components/categorySlice";

export const store = configureStore({
    reducer : {
        recipe:recipeSlice,
        category:categorySlice
    }
});