import { BASE_DB_URL } from "../firebaseConfig";
import { Recipe } from "../models/Recipe";


export const createRecipe = async (recipe:Recipe, token:string) : Promise<boolean> => {
    try {
        const response = await fetch(`${BASE_DB_URL}/recipes.json?auth=${token}`, {
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(recipe)
        });

        if (!response.ok){
            throw new Error("Erreur pendant la création d'une recette");
        }

        const data = await response.json();

        console.log("Recette bien crée",data);

        return true;
    }
    catch (error:any) {
        console.error(error.message);

        return false;
    }
};


export const readRecipes = async () : Promise<Recipe[]> => {
    try {
        const response = await fetch(`${BASE_DB_URL}/recipes.json`, {
            method : "GET",
            headers : {
                "Content-Type" : "application/json"
            }
        });

        if (!response.ok){
            throw new Error("Erreur pendant la récupération des recettes");
        }

        const data = await response.json();     

        const recipes:Recipe[] = [];

        for (const recipe in data){
            const newRecipe = {...data[recipe], id : recipe};
            recipes.push(newRecipe);
        }

        return recipes;
    }
    catch (error:any) {
        console.error(error.message);

        return [];
    }
};