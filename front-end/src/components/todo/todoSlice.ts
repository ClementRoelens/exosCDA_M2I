import { Todo } from './../../models/todo';
import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import api from "../../config/api-route.json";
import { getHeaders } from "../helpers/jwtHeadersProvider";
import { RootState } from '../../config/store';


export const getAllTodos = createAsyncThunk(
    "todos/getAll",
    async () => {
        return (await axios.get<Todo[]>(`${api.baseUrl}/todos/`, { headers: getHeaders() })).data;
    }
);

export const getOneTodo = createAsyncThunk(
    "todos/getOne",
    async (id:string) => {
        return (await axios.get<Todo>(`${api.baseUrl}/todos/${id}`, {headers : getHeaders()})).data;
    }
);

export const getTheirTodos = createAsyncThunk(
    "todos/getTheirTodos",
    async (userId: string) => {
        return (await axios.get<Todo[]>(`${api.baseUrl}/todos/allFromOneUser/${userId}`, { headers: getHeaders() })).data;
    }
);

export const createTodo = createAsyncThunk(
    "todos/create",
    async (todo: {title:string,description:string}) => {
        return (await axios.post<Todo>(`${api.baseUrl}/todos`, todo, { headers: getHeaders() })).data;
    }
);

export const updateTodo = createAsyncThunk(
    "todos/update",
    async (todo: Todo) => {
        return (await axios.put<Todo>(`${api.baseUrl}>/todos`, todo, { headers: getHeaders() })).data;
    }
);

export const deleteTodo = createAsyncThunk(
    "todos/delete",
    async (id: string) => {
        await axios.delete(`${api.baseUrl}/todos/${id}`, { headers: getHeaders() });
    }
);


const initialState = {
    todos: [],
    selectedTodo: null
} as {
    todos: Todo[],
    selectedTodo: Todo | null
};

const todoSlice = createSlice({
    name: "todo",
    initialState: initialState,
    reducers: {
    },
    extraReducers: (builder) => {
        builder.addCase(getAllTodos.fulfilled, (state, action: PayloadAction<Todo[]>) => {
            state.todos = action.payload;
        }),
            builder.addCase(getAllTodos.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(getOneTodo.fulfilled, (state,action:PayloadAction<Todo>) => {
                state.selectedTodo = action.payload;
            }),
            builder.addCase(getOneTodo.rejected, (state, action) => {
                console.error(action.error);
                state.selectedTodo = null;
            }),
            builder.addCase(getTheirTodos.fulfilled, (state, action: PayloadAction<Todo[]>) => {
                state.todos = action.payload;
            }),
            builder.addCase(getTheirTodos.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(createTodo.fulfilled, (state, action: PayloadAction<Todo>) => {
                state.todos.push(action.payload);
            }),
            builder.addCase(createTodo.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(updateTodo.fulfilled, (state, action: PayloadAction<Todo>) => {
                state.selectedTodo = action.payload;
            }),
            builder.addCase(updateTodo.rejected, (state, action) => {
                console.error(action.error);
            }),
            builder.addCase(deleteTodo.fulfilled, () => {
                console.log("Tâche supprimée");
            }),
            builder.addCase(deleteTodo.rejected, (state, action) => {
                console.error(action.error);
            })
    }
});

export default todoSlice.reducer;
export const todoSelector = (state: RootState) => state.todo;