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
    });
}
function settingMinMaxAndDefaultRanges() {
    let prepMin = Number.MAX_VALUE;
    let prepMax = 0;
    let cookingMin = Number.MAX_VALUE;
    let cookingMax = 0;
    recipes.forEach((recipe) => {
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
function filterRecipesOnOneFilter(predicate) {
    const filteredResults = recipes.filter(predicate);
    return filteredResults;
}
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
// Récupération et initialisation des données
const data = recipesData;
const recipes = fetchRecipes(data);
let filteredRecipes = [...recipes];
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
    renderRecipeListElement(filteredRecipes);
});
