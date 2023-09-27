import { Recipe } from "./classes/recipe.js";
import { recipesData } from "./data.js";

function fetchRecipes(data: any): Recipe[] {
    const fetchedRecipes: Recipe[] = [];
    for (const key in data) {
        const id: string = key;
        const dataObject = data[key];
        const newRecipe: Recipe = { id, ...dataObject };
        fetchedRecipes.push(newRecipe);
    }
    return fetchedRecipes;
}

function createIngredientsList(recipes: Recipe[]): string[] {
    let ingredients: string[] = [];
    recipes.forEach(recipe => {
        let ingredientsNames: string[] = recipe.ingredients.map(ingredient => ingredient.name);
        ingredientsNames.forEach((ingredient: string) => {
            if (ingredients.includes(ingredient)) {
                ingredients.push(ingredient);
            }
        });
    });
    return ingredients;
}

function createIngredientOptions(ingredientsList: string[]): void {
    ingredientsList.forEach((ingredient: string) => {
        const option:HTMLOptionElement = document.createElement('option');
        option.innerHTML = ingredient;
        select.appendChild(option);
    });
}

function createRecipesListElements(recipes:Recipe[]) : void{
    const recipeElement = document.createElement('div') as HTMLDivElement;
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
const select = document.querySelector('select') as HTMLSelectElement;
const recipesListElement = document.querySelector('#recipesList') as HTMLUListElement;

const data: any = recipesData;
let recipes: Recipe[] = fetchRecipes(data);
let ingredientsList: string[] = createIngredientsList(recipes);
createIngredientOptions(ingredientsList);









