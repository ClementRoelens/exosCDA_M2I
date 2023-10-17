import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { User } from "../models/User";
import { RootState } from "../store";

const initialState = {
    user: null,
    authMode: ""
} as {
    user: User | null,
    authMode: string
};

const authSlice = createSlice({
    name: "auth",
    initialState: initialState,
    reducers: {
        setUser: (state, action: PayloadAction<User>) => {
            state.user = action.payload;
            localStorage.setItem("user", JSON.stringify(state.user));
        },
        removeUser: (state) => {
            state.user = null;
            localStorage.removeItem("user");
        },
        setAuthMode: (state, action: PayloadAction<string>) => {
            state.authMode = action.payload;
        }
    }
});

export const { setUser, removeUser, setAuthMode } = authSlice.actions;
export default authSlice.reducer;
export const authSelector = (state: RootState) => state.auth;