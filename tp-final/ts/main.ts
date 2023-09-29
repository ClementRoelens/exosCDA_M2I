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
    const ingredients: string[] = [];
    recipes.forEach(recipe => {
        const ingredientsNames: string[] = recipe.ingredients.map(ingredient => ingredient.name);
        ingredientsNames.forEach((ingredient: string) => {
            if (!ingredients.includes(ingredient)) {
                ingredients.push(ingredient);
            }
        });
    });
    ingredients.sort();
    return ingredients;
}

function createIngredientOptions(ingredientsList: string[]): void {
    ingredientsList.forEach((ingredient: string) => {
        const option: HTMLOptionElement = document.createElement("option");
        option.innerHTML = ingredient;
        select.appendChild(option);
    });
}

// La liste des recettes sera regénérée à chaque application de filtres, en lui passant la liste à afficher
function renderRecipeListElement(renderedRecipes: Recipe[]): void {
    recipesListElement.innerHTML = "";
    renderedRecipes.forEach((recipe: Recipe) => {
        // <li>
        const liElement: HTMLLIElement = document.createElement("li");
        liElement.classList.add("list-group-item", "bg-dark", "border-0");
        //  <button>
        const recipeButton: HTMLButtonElement = document.createElement("button");
        recipeButton.classList.add("btn", "btn-outline-light", "p-3", "mb-3", "col-12");
        recipeButton.setAttribute("data-id", recipe.id);
        recipeButton.setAttribute("data-bs-toggle", "modal");
        recipeButton.setAttribute("data-bs-target", "#recipeModal");
        //      <h5>
        const h5: HTMLHeadingElement = document.createElement("h5");
        h5.classList.add("text-center");
        h5.innerHTML = recipe.name;
        //      <hr>
        const hr: HTMLHRElement = document.createElement("hr");
        //      <div>
        const times: HTMLDivElement = document.createElement("div");
        times.classList.add("times", "d-flex", "justify-content-around");
        //          <div>
        const cookingDiv: HTMLDivElement = document.createElement("div");
        //              <i>
        const iCooking: HTMLElement = document.createElement("i");
        iCooking.classList.add("bi", "bi-cake");
        //              <span>
        const spanCooking: HTMLSpanElement = document.createElement("span");
        spanCooking.textContent = " " + recipe.cookTime;
        //          <div>
        const prepDiv: HTMLDivElement = document.createElement("div");
        //              <i>
        const iPrep: HTMLElement = document.createElement("i");
        iPrep.classList.add("bi", "bi-fire");
        //              <span>
        const spanPrep: HTMLSpanElement = document.createElement("span");
        spanPrep.textContent = " " + recipe.prepTime;

        recipeButton.appendChild(h5);
        recipeButton.appendChild(hr);
        cookingDiv.appendChild(iCooking);
        cookingDiv.appendChild(spanCooking);
        prepDiv.appendChild(iPrep);
        prepDiv.appendChild(spanPrep);
        times.appendChild(cookingDiv);
        times.appendChild(prepDiv);
        recipeButton.appendChild(times);

        liElement.append(recipeButton);
        recipesListElement.appendChild(liElement);

        recipeButton.addEventListener("click", displayRecipe);
    });
}

// La structure de la modal est déjà prête dans le HTML, on la personnalise au clic et on remplit les listes
function displayRecipe(e: MouseEvent) {
    const clickedRecipeElement = e.currentTarget as HTMLButtonElement;
    const targetRecipe = recipes.find((recipe: Recipe) => recipe.id === clickedRecipeElement.dataset.id) as Recipe;
    prepTimeModalElement.innerHTML = targetRecipe.prepTime;
    cookTimeModalElement.innerHTML = targetRecipe.cookTime;
    servingsModalElement.innerHTML = targetRecipe.servings.toString() + " servings";
    targetRecipe.ingredients.forEach((ingredient: { name: string, amount: string }) => {
        const li: HTMLLIElement = document.createElement("li");
        const ingredientName: HTMLSpanElement = document.createElement("span");
        ingredientName.innerHTML = ingredient.name;
        const ingredientAmount: HTMLSpanElement = document.createElement("span");
        ingredientAmount.innerHTML = ` (${ingredient.amount})`;
        li.appendChild(ingredientName);
        li.appendChild(ingredientAmount);
        ingredientsModalElement.appendChild(li);
    });
    nameModalElement.innerHTML = targetRecipe.name;
    targetRecipe.instructions.forEach((instruction: string) => {
        const li: HTMLLIElement = document.createElement("li");
        li.innerHTML = instruction;
        instructionsModalElement.appendChild(li);
    });
}

// Plutôt que de laisser des range avec des valeurs par défaut, on choisit les bornes
// Borne inférieure = temps minimum parmi les recettes récupérées, et inversement
function settingMinMaxAndDefaultRanges(): void {
    let prepMin: number = Number.MAX_VALUE;
    let prepMax: number = 0;
    let cookingMin: number = Number.MAX_VALUE;
    let cookingMax: number = 0;
    recipes.forEach((recipe: Recipe) => {
        // Conversion de "xx mins" en xx:number
        const recipePrepTime: number = +recipe.prepTime.split(' ')[0];
        prepMin = (recipePrepTime < prepMin) ? recipePrepTime : prepMin;
        prepMax = (recipePrepTime > prepMax) ? recipePrepTime : prepMax;
        const recipeCookingTime: number = +recipe.cookTime.split(' ')[0];
        cookingMin = (recipeCookingTime < cookingMin) ? recipeCookingTime : cookingMin;
        cookingMax = (recipeCookingTime > cookingMax) ? recipeCookingTime : cookingMax;
    });
    preparationTimeRangeElement.min = prepMin.toString();
    preparationTimeRangeElement.max = prepMax.toString();
    preparationTimeRangeElement.value = prepMax.toString();
    updateTimeOnRangeChange(preparationTimeRangeElement, spanPreparationTime);
    cookingTimeRangeElement.min = cookingMin.toString();
    cookingTimeRangeElement.max = cookingMax.toString();
    cookingTimeRangeElement.value = cookingMax.toString();
    updateTimeOnRangeChange(cookingTimeRangeElement, spanCookingTime);
}

function updateTimeOnRangeChange(rangeElement: HTMLInputElement, targetToUpdate: HTMLSpanElement): void {
    targetToUpdate.innerHTML = rangeElement.value;
}

// Tous les filtres passeront par cette fonction qui filtrera selon les conditions données, et retournera la liste filtrée
function filterRecipesOnOneFilter(predicate: (item: Recipe) => boolean): Recipe[] {
    const filteredResults = recipes.filter(predicate);
    return filteredResults;
}

// Retourne les éléments présents dans les différents filtres appliqués
function filterRecipesOverall() {
    filteredRecipes = recipes.filter(recipe => {
        return (
            fileteredByPrepTime.includes(recipe)
            && fileteredByCookTime.includes(recipe)
            && fileteredByName.includes(recipe)
            && fileteredByIngredients.includes(recipe));
    });
}

// Récupération des éléments HTML
const select = document.querySelector("#ingredientsList") as HTMLSelectElement;
const recipesListElement = document.querySelector("#recipesList") as HTMLUListElement;
const preparationTimeRangeElement = document.querySelector("#preparationRange") as HTMLInputElement;
const spanPreparationTime = document.querySelector("#preparationTimeValue") as HTMLSpanElement;
const cookingTimeRangeElement = document.querySelector("#cookingRange") as HTMLInputElement;
const spanCookingTime = document.querySelector("#cookingTimeValue") as HTMLSpanElement;
const nameFilterElement = document.querySelector("#nameFilter") as HTMLInputElement;
const prepTimeModalElement = document.querySelector("#prepTimeModal") as HTMLDivElement;
const cookTimeModalElement = document.querySelector("#cookTimeModal") as HTMLDivElement;
const servingsModalElement = document.querySelector("#servingsModal") as HTMLDivElement;
const ingredientsModalElement = document.querySelector("#ingredientsModal") as HTMLDivElement;
const nameModalElement = document.querySelector("#nameModal") as HTMLDivElement;
const instructionsModalElement = document.querySelector("#instructionsModal") as HTMLDivElement;
const resetFiltersButton = document.querySelector("#resetFilters") as HTMLButtonElement;

// Récupération et initialisation des données
const data: any = recipesData;
const recipes: Recipe[] = fetchRecipes(data);
// Les données affichées le seront toujours par rapport à ce tableau
let filteredRecipes: Recipe[] = [...recipes];
// Et il y a un tableau par filtre possible
let fileteredByPrepTime: Recipe[] = [...recipes];
let fileteredByCookTime: Recipe[] = [...recipes];
let fileteredByName: Recipe[] = [...recipes];
let fileteredByIngredients: Recipe[] = [...recipes];
const ingredientsList: string[] = createIngredientsList(recipes);
createIngredientOptions(ingredientsList);
renderRecipeListElement(recipes);
settingMinMaxAndDefaultRanges();

// Mise en place des événements
preparationTimeRangeElement.addEventListener("input", () => {
    updateTimeOnRangeChange(preparationTimeRangeElement, spanPreparationTime);
    fileteredByPrepTime = filterRecipesOnOneFilter((recipe: Recipe) => {
        const selectedValue = parseInt(preparationTimeRangeElement.value);
        const recipeValue = parseInt(recipe.prepTime.split(' ')[0]);
        return selectedValue >= recipeValue;
    });
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});

cookingTimeRangeElement.addEventListener("input", () => {
    updateTimeOnRangeChange(cookingTimeRangeElement, spanCookingTime);
    fileteredByCookTime = filterRecipesOnOneFilter((recipe: Recipe) => {
        const selectedValue = parseInt(cookingTimeRangeElement.value);
        const recipeValue = parseInt(recipe.cookTime.split(' ')[0]);
        return selectedValue >= recipeValue;
    });
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});

nameFilterElement.addEventListener("input", () => {
    fileteredByName = filterRecipesOnOneFilter((recipe: Recipe) => recipe.name.toLowerCase().includes(nameFilterElement.value.toLowerCase()));
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});

select.addEventListener("change", () => {
    const optionNames: string[] = Array.from(select.selectedOptions).map(option => option.innerHTML);
    if (optionNames[0].toLowerCase() !== "all") {
        fileteredByIngredients = filterRecipesOnOneFilter((recipe: Recipe) => {
            const ingredientsName: string[] = recipe.ingredients.map(ingredient => ingredient.name);
            return optionNames.every(optionName => ingredientsName.includes(optionName.toLocaleLowerCase()));
        });
    } else {
        fileteredByIngredients = [...recipes];
    }
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});

resetFiltersButton.addEventListener("click", () => {
    fileteredByPrepTime = [...recipes];
    fileteredByCookTime = [...recipes];
    fileteredByName = [...recipes];
    fileteredByIngredients = [...recipes];
    filteredRecipes = [...recipes];
    renderRecipeListElement(filteredRecipes);
    preparationTimeRangeElement.value = preparationTimeRangeElement.max;
    updateTimeOnRangeChange(preparationTimeRangeElement,spanPreparationTime);
    cookingTimeRangeElement.value = cookingTimeRangeElement.max;
    updateTimeOnRangeChange(cookingTimeRangeElement,spanCookingTime);
    nameFilterElement.value = "";
    Array.from(select.options).forEach((option: HTMLOptionElement) => {
        option.selected = false;
    });
});






