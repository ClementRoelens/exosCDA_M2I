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
        recipeButton.setAttribute("data-bs-target", "#displayRecipeModal");
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
    const id = clickedRecipeElement.dataset.id;
    const targetRecipe = recipes.find((recipe) => recipe.id === id);
    prepTimeModalElement.innerHTML = targetRecipe.prepTime;
    cookTimeModalElement.innerHTML = targetRecipe.cookTime;
    servingsModalElement.innerHTML = targetRecipe.servings.toString() + " servings";
    ingredientsModalElement.innerHTML = "";
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
    instructionsModalElement.innerHTML = "";
    targetRecipe.instructions.forEach((instruction) => {
        const li = document.createElement("li");
        li.innerHTML = instruction;
        instructionsModalElement.appendChild(li);
    });
    removeButton.setAttribute("data-id", id);
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
        return (filteredByPrepTime.includes(recipe)
            && filteredByCookTime.includes(recipe)
            && filteredByName.includes(recipe)
            && filteredByIngredients.includes(recipe));
    });
}
function removeRecipe(e) {
    const button = e.target;
    const id = button.dataset.id;
    // Il faut le supprimer de chaque liste filtrée, à moins de reset les filtres à la suppression
    recipesLists.forEach((recipeList) => {
        const index = recipeList.findIndex((recipe) => recipe.id === id);
        recipeList.splice(index, 1);
    });
    renderRecipeListElement(filteredRecipes);
}
function addIngredient(e) {
    e.preventDefault();
    const li = document.createElement("li");
    li.classList.add("new-ingredient", "list-group-item", "row", "border-0");
    const div = document.createElement("div");
    div.classList.add("input-group");
    const ingredientInput = document.createElement("input");
    ingredientInput.classList.add("form-control", "w-50");
    ingredientInput.placeholder = "Name";
    const amountInput = document.createElement("input");
    amountInput.classList.add("form-control", "w-25");
    amountInput.placeholder = "Amount";
    amountInput.type = "number";
    const unitInput = document.createElement("input");
    unitInput.classList.add("form-control", "w-25");
    unitInput.placeholder = "Unit";
    div.appendChild(ingredientInput);
    div.appendChild(amountInput);
    div.appendChild(unitInput);
    li.appendChild(div);
    ingredientsListElement.appendChild(li);
}
function addInstruction(e) {
    e.preventDefault();
    const li = document.createElement("li");
    li.classList.add("list-group-item", "border-0");
    const instructionInput = document.createElement("input");
    instructionInput.classList.add("form-control", "new-instruction");
    instructionInput.placeholder = "Instruction";
    li.appendChild(instructionInput);
    instructionsListElement.appendChild(li);
}
function addRecipe(e) {
    e.preventDefault();
    // Je crée les éléments ici car on doit de toutes façons créer au clic les listes d'instructions et d'ingrédients
    const newName = document.querySelector("#nameAddModal");
    const newPrepTime = document.querySelector("#prepTimeAddModal");
    const newCookTime = document.querySelector("#cookTimeAddModal");
    const newServings = document.querySelector("#servingsAddModal");
    const newIngredientsLiElements = document.querySelectorAll(".new-ingredient");
    const newIngredients = [];
    // On doit construire chaque nouvel ingrédient à partir de chaque LI
    Array.from(newIngredientsLiElements).forEach((newIngredientElement) => {
        // Les 3 données sont dans le seul enfant de LI
        const inputs = newIngredientElement.children[0].children;
        let ingredientsInputs = [];
        Array.from(inputs).forEach((child) => {
            const input = child;
            if (input.value !== "") {
                ingredientsInputs.push(input);
            }
        });
        let newIngredient = {
            name: ingredientsInputs[0].value,
            amount: `${ingredientsInputs[1].value} ${ingredientsInputs[2].value}`
        };
        newIngredients.push(newIngredient);
    });
    // Même méthode pour les instructions
    const newInstructionsLiElements = document.querySelectorAll(".new-instruction");
    const newInstructions = [];
    Array.from(newInstructionsLiElements).forEach((input) => {
        if (input.value !== "") {
            newInstructions.push(input.value);
        }
    });
    const newRecipe = {
        id: crypto.randomUUID(),
        name: newName.value,
        servings: +newServings.value,
        prepTime: newPrepTime.value + " mins",
        cookTime: newCookTime.value + " mins",
        ingredients: newIngredients,
        instructions: newInstructions
    };
    // Comme pour la suppression, on ajoute la recette à chaque liste
    recipesLists.forEach((recipeList) => {
        recipeList.push(newRecipe);
    });
    // On met à jours les mins et max des ranges et on rerend la liste des recettes
    settingMinMaxAndDefaultRanges();
    renderRecipeListElement(filteredRecipes);
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
const removeButton = document.querySelector("#removeRecipe");
const ingredientsListElement = document.querySelector("#ingredientsAddModal");
const addIngredientButton = document.querySelector("#addIngredientButton");
const instructionsListElement = document.querySelector("#instructionsAddModal");
const addInstructionButton = document.querySelector("#addInstructionButton");
const addRecipeButton = document.querySelector("#addRecipeButton");
// Récupération et initialisation des données
const data = recipesData;
const recipes = fetchRecipes(data);
// Les données affichées le seront toujours par rapport à ce tableau
let filteredRecipes = [...recipes];
// Et il y a un tableau par filtre possible
let filteredByPrepTime = [...recipes];
let filteredByCookTime = [...recipes];
let filteredByName = [...recipes];
let filteredByIngredients = [...recipes];
// Tableau des différents tableaux, utile pour la suppression et l'ajout
const recipesLists = [recipes, filteredRecipes, filteredByPrepTime, filteredByCookTime, filteredByName, filteredByIngredients];
const ingredientsList = createIngredientsList(recipes);
createIngredientOptions(ingredientsList);
renderRecipeListElement(recipes);
settingMinMaxAndDefaultRanges();
// Mise en place des événements
preparationTimeRangeElement.addEventListener("input", () => {
    updateTimeOnRangeChange(preparationTimeRangeElement, spanPreparationTime);
    filteredByPrepTime = filterRecipesOnOneFilter((recipe) => {
        const selectedValue = parseInt(preparationTimeRangeElement.value);
        const recipeValue = parseInt(recipe.prepTime.split(' ')[0]);
        return selectedValue >= recipeValue;
    });
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});
cookingTimeRangeElement.addEventListener("input", () => {
    updateTimeOnRangeChange(cookingTimeRangeElement, spanCookingTime);
    filteredByCookTime = filterRecipesOnOneFilter((recipe) => {
        const selectedValue = parseInt(cookingTimeRangeElement.value);
        const recipeValue = parseInt(recipe.cookTime.split(' ')[0]);
        return selectedValue >= recipeValue;
    });
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});
nameFilterElement.addEventListener("input", () => {
    filteredByName = filterRecipesOnOneFilter((recipe) => recipe.name.toLowerCase().includes(nameFilterElement.value.toLowerCase()));
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});
select.addEventListener("change", () => {
    const optionNames = Array.from(select.selectedOptions).map(option => option.innerHTML);
    if (optionNames[0].toLowerCase() !== "all") {
        filteredByIngredients = filterRecipesOnOneFilter((recipe) => {
            const ingredientsName = recipe.ingredients.map(ingredient => ingredient.name);
            return optionNames.every(optionName => ingredientsName.includes(optionName.toLocaleLowerCase()));
        });
    }
    else {
        filteredByIngredients = [...recipes];
    }
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});
resetFiltersButton.addEventListener("click", () => {
    filteredByPrepTime = [...recipes];
    filteredByCookTime = [...recipes];
    filteredByName = [...recipes];
    filteredByIngredients = [...recipes];
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
removeButton.addEventListener("click", removeRecipe);
addIngredientButton.addEventListener("click", addIngredient);
addInstructionButton.addEventListener("click", addInstruction);
addRecipeButton.addEventListener("click", addRecipe);
