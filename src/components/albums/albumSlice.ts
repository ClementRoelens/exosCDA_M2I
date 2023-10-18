import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Album } from "../../models/Album";
import axios from "axios";
import { BASE_DB_URL } from "../../config/firebaseConfig";
import { User } from "../../models/User";

export const addAlbum = createAsyncThunk(
    "album/add",
    async (album:Album) => {
        let token = "";
        const storedUser = localStorage.getItem("user");
        if (storedUser){
            const user = JSON.parse(storedUser) as User;
            token = user.token;
        }
        const response = await axios.post<Album>(`${BASE_DB_URL}/albums.json?auth=${token}`,album);
        return response.data;
    }
);

const initialState = { albums: [] } as { albums: Album[] };

const albumSlice = createSlice({
    name: "album",
    initialState: initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder.addCase(addAlbum.fulfilled, (state,action:PayloadAction<Album>) => {
            state.albums.push(action.payload);
        }),
        builder.addCase(addAlbum.rejected, (state,action) => {
            console.error(action.error);
        })
    }
});