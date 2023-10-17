import { Ingredient } from "./Ingredient";

export interface Recipe {
    id: string;
    title: string;
    instructions: string;
    cookTime: number;
    prepTime: number;
    ingredients: Ingredient[]
}