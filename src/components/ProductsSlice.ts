import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Product } from "../models/Product";
import { RootState } from "../redux-config/store";

const initialState = {products : [] as Product[]} ;

const productSlice = createSlice({
    name: "product",
    initialState: initialState,
    reducers: {
        addProduct: (state, action: PayloadAction<Product>) => {
            const newProduct = new Product(action.payload.name, action.payload.price);
            state.products.push(newProduct);
        },
        editProduct: (state, action: PayloadAction<{id:number,product:Product}>) => {
            state.products = state.products.map(product => product.id === action.payload.id ? action.payload.product : product);
        },
        deleteProduct: (state, action: PayloadAction<number>) => {
            state.products = state.products.filter(product => product.id !== action.payload);
        }
    }
});

export const { addProduct, editProduct, deleteProduct } = productSlice.actions;
export const productSelector = (state: RootState) => state.product;
export default productSlice.reducer;