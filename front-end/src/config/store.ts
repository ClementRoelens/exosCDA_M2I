import { configureStore } from "@reduxjs/toolkit";
import authSlice from "../components/auth/authSlice";
import todoSlice from "../components/todo/todoSlice";

export const store = configureStore({
    reducer : {
        auth : authSlice,
        todo : todoSlice
    }
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;