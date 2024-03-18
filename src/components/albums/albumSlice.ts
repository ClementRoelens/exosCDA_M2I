import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Album } from "../../models/Album";
import axios from "axios";
import { BASE_DB_URL } from "../../config/firebaseConfig";
import { User } from "../../models/User";
import { RootState } from "../../config/store";

function getToken(): string {
    let token = "";
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
        const user = JSON.parse(storedUser) as User;
        token = user.token;
    }

    return token;
}

export const createAlbum = createAsyncThunk(
    "album/add",
    async (album: Album) => {
        const token = getToken();
        const response = await axios.post(`${BASE_DB_URL}/albums.json?auth=${token}`, album);
        return response.data;
    }
);

export const readAllAlbums = createAsyncThunk(
    "album/getAll",
    async () => {
        const response = await axios.get(`${BASE_DB_URL}/albums.json`);
        const albums: Album[] = [];
        for (const key in response.data) {
            const album = response.data[key] as Album;
            albums.push({ ...album, id: key });
        }
        return albums;
    }
);

export const readOneAlbum = createAsyncThunk(
    "album/getOne",
    async (id: string) => {
        const response = await axios.get<Album>(`${BASE_DB_URL}/albums/${id}.json`);
        return { ...response.data, id: id };
    }
);

export const updateAlbum = createAsyncThunk(
    "album/update",
    async (album: Album) => {
        const token = getToken();
        const response = await axios.put<Album>(`${BASE_DB_URL}/albums/${album.id}.json?auth=${token}`, album);
        return response.data;
    }

);

export const deleteAlbum = createAsyncThunk(
    "albums/delete",
    async (id: string) => {
        const token = getToken();
        await axios.delete(`${BASE_DB_URL}/albums/${id}.json?auth=${token}`);
        return id;
    }
);

const initialState = {
    albums: [],
    filteredAlbums: [],
    selectedAlbum: null
} as {
    albums: Album[],
    filteredAlbums: Album[],
    selectedAlbum: Album | null
};

const albumSlice = createSlice({
    name: "album",
    initialState: initialState,
    reducers: {
        search: (state, action: PayloadAction<string>) => {
            if (action.payload.trim() !== ""){
                state.filteredAlbums = state.albums.filter((album: Album) => {
                    if (
                        album.title.toLowerCase().includes(action.payload.toLowerCase()) ||
                        album.artist.toLowerCase().includes(action.payload.toLowerCase()) ||
                        album.releaseDate.includes(action.payload)
                    ) {
                        return true
                    }
                    return false
                });
            } else {
                console.log("Le champ de recherche est vide");
                state.filteredAlbums = state.albums;
            }
        },
        resetFilter : (state) => {
            state.filteredAlbums = state.albums;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(createAlbum.fulfilled, () => {
        }),
            builder.addCase(createAlbum.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(readAllAlbums.fulfilled, (state, action: PayloadAction<Album[]>) => {
                state.albums = action.payload;
                state.filteredAlbums = state.albums;
            }),
            builder.addCase(readAllAlbums.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(readOneAlbum.fulfilled, (state, action: PayloadAction<Album>) => {
                state.selectedAlbum = action.payload;
            }),
            builder.addCase(readOneAlbum.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(updateAlbum.fulfilled, (state, action: PayloadAction<Album>) => {
                const index = state.albums.findIndex((album: Album) => album.id === action.payload.id);
                state.albums[index] = action.payload;
            }),
            builder.addCase(updateAlbum.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(deleteAlbum.fulfilled, (state, action: PayloadAction<string>) => {
                const index = state.albums.findIndex((album: Album) => album.id === action.payload);
                const filteredIndex = state.filteredAlbums.findIndex((album: Album) => album.id === action.payload);
                state.albums.splice(index, 1);
                state.filteredAlbums.splice(filteredIndex, 1);
            }),
            builder.addCase(deleteAlbum.rejected, (state, action) => {
                console.error(action.error);
            })
    }
});

export const { search, resetFilter } = albumSlice.actions;
export default albumSlice.reducer;
export const albumSelector = (state: RootState) => state.albums.albums;