import { configureStore } from "@reduxjs/toolkit";
import userSlice from "../components/auth/authSlice";
import albumSlice from "../components/albums/albumSlice";

export const store = configureStore({
    reducer: {
        users: userSlice,
        albums: albumSlice
    }
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;