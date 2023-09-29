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
        recipeButton.setAttribute("data-bs-target", "#displayRecipeModal");
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
    const id = clickedRecipeElement.dataset.id as string;
    const targetRecipe = recipes.find((recipe: Recipe) => recipe.id === id) as Recipe;
    prepTimeModalElement.innerHTML = targetRecipe.prepTime;
    cookTimeModalElement.innerHTML = targetRecipe.cookTime;
    servingsModalElement.innerHTML = targetRecipe.servings.toString() + " servings";
    ingredientsModalElement.innerHTML = "";
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
    instructionsModalElement.innerHTML = "";
    targetRecipe.instructions.forEach((instruction: string) => {
        const li: HTMLLIElement = document.createElement("li");
        li.innerHTML = instruction;
        instructionsModalElement.appendChild(li);
    });
    removeButton.setAttribute("data-id", id);
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
            filteredByPrepTime.includes(recipe)
            && filteredByCookTime.includes(recipe)
            && filteredByName.includes(recipe)
            && filteredByIngredients.includes(recipe));
    });
}

function removeRecipe(e: MouseEvent) {
    const button = e.target as HTMLButtonElement;
    const id = button.dataset.id as string;
    // Il faut le supprimer de chaque liste filtrée, à moins de reset les filtres à la suppression
    recipesLists.forEach((recipeList: Recipe[]) => {
        const index = recipeList.findIndex((recipe:Recipe) => recipe.id === id);
        recipeList.splice(index,1);
    });
    renderRecipeListElement(filteredRecipes);
}

function addIngredient(e:MouseEvent){
    e.preventDefault();
    const li:HTMLLIElement = document.createElement("li");
    li.classList.add("new-ingredient","list-group-item","row","border-0");
    const div:HTMLDivElement = document.createElement("div");
    div.classList.add("input-group");
    const ingredientInput:HTMLInputElement = document.createElement("input");
    ingredientInput.classList.add("form-control","w-50");
    ingredientInput.placeholder = "Name"
    const amountInput:HTMLInputElement = document.createElement("input");
    amountInput.classList.add("form-control","w-25");
    amountInput.placeholder = "Amount";
    amountInput.type = "number";
    const unitInput:HTMLInputElement = document.createElement("input");
    unitInput.classList.add("form-control","w-25");
    unitInput.placeholder = "Unit";

    div.appendChild(ingredientInput);
    div.appendChild(amountInput);
    div.appendChild(unitInput);
    li.appendChild(div);

    ingredientsListElement.appendChild(li);
}

function addInstruction(e:MouseEvent){
    e.preventDefault();
    const li:HTMLLIElement = document.createElement("li");
    li.classList.add("list-group-item","border-0");
    const instructionInput:HTMLInputElement = document.createElement("input");
    instructionInput.classList.add("form-control","new-instruction");
    instructionInput.placeholder = "Instruction"

    li.appendChild(instructionInput);
    instructionsListElement.appendChild(li);
}

function addRecipe(e:MouseEvent) {
    e.preventDefault()
    // Je crée les éléments ici car on doit de toutes façons créer au clic les listes d'instructions et d'ingrédients
    const newName = document.querySelector("#nameAddModal") as HTMLInputElement;
    const newPrepTime = document.querySelector("#prepTimeAddModal") as HTMLInputElement;
    const newCookTime = document.querySelector("#cookTimeAddModal") as HTMLInputElement;
    const newServings = document.querySelector("#servingsAddModal") as HTMLInputElement;
    const newIngredientsLiElements = document.querySelectorAll(".new-ingredient") as NodeListOf<HTMLLIElement>;
    const newIngredients:{name:string,amount:string}[] = [];
    // On doit construire chaque nouvel ingrédient à partir de chaque LI
    Array.from(newIngredientsLiElements).forEach((newIngredientElement:HTMLLIElement)=>{
        // Les 3 données sont dans le seul enfant de LI
        const inputs = newIngredientElement.children[0].children as HTMLCollection;
        let ingredientsInputs:HTMLInputElement[] = [];
        Array.from(inputs).forEach((child:Element) => {
            const input = child as HTMLInputElement;
            if (input.value !== ""){
                ingredientsInputs.push(input);
            }
        });
        let newIngredient:{name:string,amount:string} = {
            name : ingredientsInputs[0].value,
            amount : `${ingredientsInputs[1].value} ${ingredientsInputs[2].value}`
        };
        newIngredients.push(newIngredient);
    });
    // Même méthode pour les instructions
    const newInstructionsLiElements = document.querySelectorAll(".new-instruction") as NodeListOf<HTMLInputElement>;
    const newInstructions:string[] = [];
    Array.from(newInstructionsLiElements).forEach((input:HTMLInputElement)=>{
        if (input.value !== ""){
            newInstructions.push(input.value);
        }
    });
    const newRecipe:Recipe = {
        id:crypto.randomUUID(),
        name:newName.value,
        servings: +newServings.value,
        prepTime:newPrepTime.value + " mins",
        cookTime:newCookTime.value + " mins",
        ingredients:newIngredients,
        instructions:newInstructions
    };
    // Comme pour la suppression, on ajoute la recette à chaque liste
    recipesLists.forEach((recipeList:Recipe[])=>{
        recipeList.push(newRecipe);
    });
    // On met à jours les mins et max des ranges et on rerend la liste des recettes
    settingMinMaxAndDefaultRanges();
    renderRecipeListElement(filteredRecipes);
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
const ingredientsModalElement = document.querySelector("#ingredientsModal") as HTMLUListElement;
const nameModalElement = document.querySelector("#nameModal") as HTMLDivElement;
const instructionsModalElement = document.querySelector("#instructionsModal") as HTMLUListElement;
const resetFiltersButton = document.querySelector("#resetFilters") as HTMLButtonElement;
const removeButton = document.querySelector("#removeRecipe") as HTMLButtonElement;
const ingredientsListElement = document.querySelector("#ingredientsAddModal") as HTMLUListElement;
const addIngredientButton = document.querySelector("#addIngredientButton") as HTMLButtonElement;
const instructionsListElement = document.querySelector("#instructionsAddModal") as HTMLUListElement;
const addInstructionButton = document.querySelector("#addInstructionButton") as HTMLButtonElement;
const addRecipeButton = document.querySelector("#addRecipeButton") as HTMLButtonElement;

// Récupération et initialisation des données
const data: any = recipesData;
const recipes: Recipe[] = fetchRecipes(data);
// Les données affichées le seront toujours par rapport à ce tableau
let filteredRecipes: Recipe[] = [...recipes];
// Et il y a un tableau par filtre possible
let filteredByPrepTime: Recipe[] = [...recipes];
let filteredByCookTime: Recipe[] = [...recipes];
let filteredByName: Recipe[] = [...recipes];
let filteredByIngredients: Recipe[] = [...recipes];
// Tableau des différents tableaux, utile pour la suppression et l'ajout
const recipesLists: Recipe[][] = [recipes, filteredRecipes, filteredByPrepTime, filteredByCookTime, filteredByName, filteredByIngredients];
const ingredientsList: string[] = createIngredientsList(recipes);
createIngredientOptions(ingredientsList);
renderRecipeListElement(recipes);
settingMinMaxAndDefaultRanges();

// Mise en place des événements
preparationTimeRangeElement.addEventListener("input", () => {
    updateTimeOnRangeChange(preparationTimeRangeElement, spanPreparationTime);
    filteredByPrepTime = filterRecipesOnOneFilter((recipe: Recipe) => {
        const selectedValue = parseInt(preparationTimeRangeElement.value);
        const recipeValue = parseInt(recipe.prepTime.split(' ')[0]);
        return selectedValue >= recipeValue;
    });
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});

cookingTimeRangeElement.addEventListener("input", () => {
    updateTimeOnRangeChange(cookingTimeRangeElement, spanCookingTime);
    filteredByCookTime = filterRecipesOnOneFilter((recipe: Recipe) => {
        const selectedValue = parseInt(cookingTimeRangeElement.value);
        const recipeValue = parseInt(recipe.cookTime.split(' ')[0]);
        return selectedValue >= recipeValue;
    });
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});

nameFilterElement.addEventListener("input", () => {
    filteredByName = filterRecipesOnOneFilter((recipe: Recipe) => recipe.name.toLowerCase().includes(nameFilterElement.value.toLowerCase()));
    filterRecipesOverall();
    renderRecipeListElement(filteredRecipes);
});

select.addEventListener("change", () => {
    const optionNames: string[] = Array.from(select.selectedOptions).map(option => option.innerHTML);
    if (optionNames[0].toLowerCase() !== "all") {
        filteredByIngredients = filterRecipesOnOneFilter((recipe: Recipe) => {
            const ingredientsName: string[] = recipe.ingredients.map(ingredient => ingredient.name);
            return optionNames.every(optionName => ingredientsName.includes(optionName.toLocaleLowerCase()));
        });
    } else {
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
    Array.from(select.options).forEach((option: HTMLOptionElement) => {
        option.selected = false;
    });
});

removeButton.addEventListener("click", removeRecipe);
addIngredientButton.addEventListener("click",addIngredient);
addInstructionButton.addEventListener("click",addInstruction);
addRecipeButton.addEventListener("click",addRecipe);


