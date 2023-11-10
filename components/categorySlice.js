const { createSlice } = require("@reduxjs/toolkit");
const { CATEGORIES } = require("../data");

const categorySlice = createSlice({
    name: "category",
    initialState: {
        categories: []
    },
    reducers: {
        getCategories: (state) => {
            state.categories = [];
            const categories = CATEGORIES;
            categories.forEach(categorie => {
                state.categories.push({ id: categorie.id, title: categorie.title, color: categorie.color });
            })
        }
    }
});

export const { getCategories } = categorySlice.actions;
export default categorySlice.reducer;