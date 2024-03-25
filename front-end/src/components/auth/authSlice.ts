import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { User } from "../../models/user";
import { Credentials } from "../../models/credentials";
import axios from "axios";
import api from "../../config/api-route.json";
import { getHeaders } from "../helpers/jwtHeadersProvider";
import { RootState } from "../../config/store";

async function getUser(email: string): Promise<User> {
    return (await axios.get<User>(`${api.baseUrl}/user/getByMail/${email}`, { headers: getHeaders() })).data;
}

export const getStoredUser = createAsyncThunk(
    "auth/checkStoreUser",
    async () => {
        const token = localStorage.getItem("token");
        if (token){
            const base64Url = token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(c => {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));
            const decoded = JSON.parse(jsonPayload);
            return await getUser(decoded.sub);
        }
        return null;
    }
);

export const signup = createAsyncThunk(
    "auth/signup",
    async (user: User) => {
        await axios.post(`${api.baseUrl}/user/signup`, user);
    }
);

export const signin = createAsyncThunk(
    "auth/signin",
    async (credentials: Credentials) => {
        const response = (await axios.post(`${api.baseUrl}/user/signin`, credentials)).data;
        localStorage.setItem("token", JSON.stringify(response.data.token));
        return await getUser(credentials.email);
    }
);

const initialState = {
    user: null
} as {
    user: User | null
};

const authSlice = createSlice({
    name: "auth",
    initialState: initialState,
    reducers: {
    },
    extraReducers: (builder) => {
        builder.addCase(signin.fulfilled, (state, action: PayloadAction<User>) => {
            state.user = action.payload;
        }),
        builder.addCase(signin.rejected, (state,action) => {
            console.error(action.error);
        }),
        builder.addCase(signup.fulfilled, () => {
            console.log("Enregistrement réussi");
        }),
        builder.addCase(signup.rejected, (state,action) => {
            console.error(action.error);
        }),
        builder.addCase(getStoredUser.fulfilled, (state, action:PayloadAction<User | null>) => {
            state.user = action.payload;
        }),
        builder.addCase(getStoredUser.rejected, (state,action) => {
            console.error(action.error);
        })
    }
});

export default authSlice.reducer;
export const authSelector = (state:RootState) => state.auth;