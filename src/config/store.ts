import { configureStore } from "@reduxjs/toolkit";
import userSlice from "../components/users/authSlice";

export const store = configureStore({
    reducer : {
        users : userSlice
    }
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;