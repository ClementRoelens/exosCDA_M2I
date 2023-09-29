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
    const ingredients = [];
    recipes.forEach(recipe => {
        const ingredientsNames = recipe.ingredients.map(ingredient => ingredient.name);
        ingredientsNames.forEach((ingredient) => {
            if (!ingredients.includes(ingredient)) {
                ingredients.push(ingredient);
            }
        });
    });
    ingredients.sort();
    return ingredients;
}
function createIngredientOptions(ingredientsList) {
    ingredientsList.forEach((ingredient) => {
        const option = document.createElement("option");
        option.innerHTML = ingredient;
        select.appendChild(option);
    });
}
// La liste des recettes sera regénérée à chaque application de filtres, en lui passant la liste à afficher
function renderRecipeListElement(renderedRecipes) {
    recipesListElement.innerHTML = "";
    renderedRecipes.forEach((recipe) => {
        // <li>
        const liElement = document.createElement("li");
        liElement.classList.add("list-group-item", "bg-dark", "border-0");
        //  <button>
        const recipeButton = document.createElement("button");
        recipeButton.classList.add("btn", "btn-outline-light", "p-3", "mb-3", "col-12");
        recipeButton.setAttribute("data-id", recipe.id);
        recipeButton.setAttribute("data-bs-toggle", "modal");
        recipeButton.setAttribute("data-bs-target", "#recipeModal");
        //      <h5>
        const h5 = document.createElement("h5");
        h5.classList.add("text-center");
        h5.innerHTML = recipe.name;
        //      <hr>
        const hr = document.createElement("hr");
        //      <div>
        const times = document.createElement("div");
        times.classList.add("times", "d-flex", "justify-content-around");
        //          <div>
        const cookingDiv = document.createElement("div");
        //              <i>
        const iCooking = document.createElement("i");
        iCooking.classList.add("bi", "bi-cake");
        //              <span>
        const spanCooking = document.createElement("span");
        spanCooking.textContent = " " + recipe.cookTime;
        //          <div>
        const prepDiv = document.createElement("div");
        //              <i>
        const iPrep = document.createElement("i");
        iPrep.classList.add("bi", "bi-fire");
        //              <span>
        const spanPrep = document.createElement("span");
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
function displayRecipe(e) {
    const clickedRecipeElement = e.currentTarget;
    const targetRecipe = recipes.find((recipe) => recipe.id === clickedRecipeElement.dataset.id);
    prepTimeModalElement.innerHTML = targetRecipe.prepTime;
    cookTimeModalElement.innerHTML = targetRecipe.cookTime;
    servingsModalElement.innerHTML = targetRecipe.servings.toString() + " servings";
    targetRecipe.ingredients.forEach((ingredient) => {
        const li = document.createElement("li");
        const ingredientName = document.createElement("span");
        ingredientName.innerHTML = ingredient.name;
        const ingredientAmount = document.createElement("span");
        ingredientAmount.innerHTML = ` (${ingredient.amount})`;
        li.appendChild(ingredientName);
        li.appendChild(ingredientAmount);
        ingredientsModalElement.appendChild(li);
    });
    nameModalElement.innerHTML = targetRecipe.name;
    targetRecipe.instructions.forEach((instruction) => {
        const li = document.createElement("li");
        li.innerHTML = instruction;
        instructionsModalElement.appendChild(li);
    });
}
// Plutôt que de laisser des range avec des valeurs par défaut, on choisit les bornes
// Borne inférieure = temps minimum parmi les recettes récupérées, et inversement
function settingMinMaxAndDefaultRanges() {
    let prepMin = Number.MAX_VALUE;
    let prepMax = 0;
    let cookingMin = Number.MAX_VALUE;
    let cookingMax = 0;
    recipes.forEach((recipe) => {
        // Conversion de "xx mins" en xx:number
        const recipePrepTime = +recipe.prepTime.split(' ')[0];
        prepMin = (recipePrepTime < prepMin) ? recipePrepTime : prepMin;
        prepMax = (recipePrepTime > prepMax) ? recipePrepTime : prepMax;
        const recipeCookingTime = +recipe.cookTime.split(' ')[0];
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
function updateTimeOnRangeChange(rangeElement, targetToUpdate) {
    targetToUpdate.innerHTML = rangeElement.value;
}
// Tous les filtres passeront par cette fonction qui filtrera selon les conditions données, et retournera la liste filtrée
function filterRecipesOnOneFilter(predicate) {
    const filteredResults = recipes.filter(predicate);
    return filteredResults;
}
// Retourne les éléments présents dans les différents filtres appliqués
function filterRecipesOverall() {
    filteredRecipes = recipes.filter(recipe => {
        return (fileteredByPrepTime.includes(recipe)
            && fileteredByCookTime.includes(recipe)
            && fileteredByName.includes(recipe)
            && fileteredByIngredients.includes(recipe));
    });
}
// Récupération des éléments HTML
const select = document.querySelector("#ingredientsList");
const recipesListElement = document.querySelector("#recipesList");
const preparationTimeRangeElement = document.querySelector("#preparationRange");
const spanPreparationTime = document.querySelector("#preparationTimeValue");
const cookingTimeRangeElement = document.querySelector("#cookingRange");
const spanCookingTime = document.querySelector("#cookingTimeValue");
const nameFilterElement = document.querySelector("#nameFilter");
const prepTimeModalElement = document.querySelector("#prepTimeModal");
const cookTimeModalElement = document.querySelector("#cookTimeModal");
const servingsModalElement = document.querySelector("#servingsModal");
const ingredientsModalElement = document.querySelector("#ingredientsModal");
const nameModalElement = document.querySelector("#nameModal");
const instructionsModalElement = document.querySelector("#instructionsModal");
const resetFiltersButton = document.querySelector("#resetFilters");
// Récupération et initialisation des données
const data = recipesData;
const recipes = fetchRecipes(data);
// Les données affichées le seront toujours par rapport à ce tableau
let filteredRecipes = [...recipes];
// Et il y a un tableau par filtre possible
let fileteredByPrepTime = [...recipes];
let fileteredByCookTime = [...recipes];
let fileteredByName = [...recipes];
let fileteredByIngredients = [...recipes];
const ingredientsList = createIngredientsList(recipes);
createIngredientOptions(ingredientsList);
renderRecipeListElement(recipes);
settingMinMaxAndDefaultRanges();
// Mise en place des événements
preparationTimeRangeElement.addEventListener("input", () => {
    updateTimeOnRangeChange(preparationTimeRangeElement, spanPreparationTime);
    fileteredByPrepTime = filterRecipesOnOneFilter((recipe) => {
        const selectedValue = parseInt(preparationTimeRangeElement.value);
        const recipeValue = parseInt(recipe.prepTime.split(' ')[0]);
        return selectedValue >= recipeValue;
    });
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});
cookingTimeRangeElement.addEventListener("input", () => {
    updateTimeOnRangeChange(cookingTimeRangeElement, spanCookingTime);
    fileteredByCookTime = filterRecipesOnOneFilter((recipe) => {
        const selectedValue = parseInt(cookingTimeRangeElement.value);
        const recipeValue = parseInt(recipe.cookTime.split(' ')[0]);
        return selectedValue >= recipeValue;
    });
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});
nameFilterElement.addEventListener("input", () => {
    fileteredByName = filterRecipesOnOneFilter((recipe) => recipe.name.toLowerCase().includes(nameFilterElement.value.toLowerCase()));
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});
select.addEventListener("change", () => {
    const optionNames = Array.from(select.selectedOptions).map(option => option.innerHTML);
    if (optionNames[0].toLowerCase() !== "all") {
        fileteredByIngredients = filterRecipesOnOneFilter((recipe) => {
            const ingredientsName = recipe.ingredients.map(ingredient => ingredient.name);
            return optionNames.every(optionName => ingredientsName.includes(optionName.toLocaleLowerCase()));
        });
    }
    else {
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
    updateTimeOnRangeChange(preparationTimeRangeElement, spanPreparationTime);
    cookingTimeRangeElement.value = cookingTimeRangeElement.max;
    updateTimeOnRangeChange(cookingTimeRangeElement, spanCookingTime);
    nameFilterElement.value = "";
    Array.from(select.options).forEach((option) => {
        option.selected = false;
    });
});
