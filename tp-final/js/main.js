import { recipesData } from "./data.js";
function fetchRecipes(data) {
    const fetchedRecipes = [];
    for (const key in data) {
        const id = key;
        const dataObject = data[key];
        const newRecipe = Object.assign({ id }, dataObject);
        fetchedRecipes.push(newRecipe);
    }
    return fetchedRecipes;
}
function createIngredientsList(recipes) {
    let ingredients = [];
    recipes.forEach(recipe => {
        let ingredientsNames = recipe.ingredients.map(ingredient => ingredient.name);
        ingredientsNames.forEach((ingredient) => {
            if (ingredients.includes(ingredient)) {
                ingredients.push(ingredient);
            }
        });
    });
    return ingredients;
}
function createIngredientOptions(ingredientsList) {
    ingredientsList.forEach((ingredient) => {
        const option = document.createElement('option');
        option.innerHTML = ingredient;
        select.appendChild(option);
    });
}
function createRecipesListElements(recipes) {
    const recipeElement = document.createElement('div');
    //     <li class="list-group-item bg-dark text-light p-3 mb-3 border-light border-top rounded">
    //     <h5 class="text-center">Tomato Basil Pasta</h5>
    //     <hr>
    //     <div class="times d-flex justify-content-around">
    //         <div><i class="bi bi-cake"></i> <span>15 mins</span></div>
    //         <div><i class="bi bi-fire"></i> <span>20 mins</span></div>
    //     </div>
    // </li>
}
// Récupération des éléments HTML
const select = document.querySelector('select');
const recipesListElement = document.querySelector('#recipesList');
const data = recipesData;
let recipes = fetchRecipes(data);
let ingredientsList = createIngredientsList(recipes);
createIngredientOptions(ingredientsList);
