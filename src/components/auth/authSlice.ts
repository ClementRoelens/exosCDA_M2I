import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { User } from "../../models/User";
import { SIGN_IN_URL, SIGN_UP_URL } from "../../config/firebaseConfig";
import { RootState } from "../../config/store";

export const signIn = createAsyncThunk(
    "users/signIn",
    async (credentials: { email: string, password: string }) => {
        const response = await axios.post(SIGN_IN_URL, { ...credentials, returnSecureToken: true });
        return {
            email: response.data.email,
            token: response.data.idToken
        };
    }
);

export const signUp = createAsyncThunk(
    "users/signUp",
    async (credentials: { email: string, password: string }) => {
        const response = await axios.post(SIGN_UP_URL, { ...credentials, returnSecureToken: true });
        return {
            email: response.data.email,
            token: response.data.idToken
        };
    }
);

const initialState = {
    user: null
} as {
    user: User | null
};

const userSlice = createSlice({
    name: "user",
    initialState: initialState,
    reducers: {
        authentificateUser : (state,action:PayloadAction<User>) => {
            state.user = action.payload;
        },
        removeUser: (state) => {
            state.user = null;
            localStorage.removeItem("user");
        }
    },
    extraReducers: (builder) => {
        builder.addCase(signIn.fulfilled, (state, action: PayloadAction<User>) => {
            state.user = action.payload;
            localStorage.setItem("user", JSON.stringify(action.payload));
        }),
            builder.addCase(signIn.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(signUp.fulfilled, (state, action: PayloadAction<User>) => {
                state.user = action.payload;
                localStorage.setItem("user", JSON.stringify(action.payload));
            }),
            builder.addCase(signUp.rejected, (state, action) => {
                console.error(action.error);
            })
    }
});

export const { authentificateUser, removeUser } = userSlice.actions;
export default userSlice.reducer;
export const userSelector = (state: RootState) => state.users;