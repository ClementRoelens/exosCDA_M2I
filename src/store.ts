import { configureStore } from "@reduxjs/toolkit";
import recipesSlice from "./components/recipesSlice";
import authSlice from "./components/authSlice";

export const store = configureStore({
    reducer : {
        recipe : recipesSlice,
        auth : authSlice
    }
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;