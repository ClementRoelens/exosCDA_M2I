import { Ingredient } from "../models/Ingredient";
import { Recipe } from "../models/Recipe";

function RecipeDisplay(props: RecipeProps) {
    return (
        <>
            <div className="recipe-header">
                <h4>{props.recipe.title}</h4>
                <div className="ms-auto align-center">
                    <span className="border border-warning"><i className="bi bi-backpack4-fill"></i> {props.recipe.prepTime}'</span>
                    <span className="border border-danger"><i className="bi bi-fire"></i> {props.recipe.prepTime}'</span>
                </div>
            </div>
            <hr />
            <div className="recipe-body">
                <div className="ingredients col-4">
                    <h5>Ingrédients :</h5>
                    <hr />
                    <ul>
                        {props.recipe.ingredients.map((ingredient: Ingredient) =>
                            <li key={ingredient.id}>{ingredient.name}</li>
                        )}
                    </ul>
                </div>
                <div className="instructions col-8">
                    <h5>Instructions : </h5>
                    <hr />
                    <span>{props.recipe.instructions}</span>
                </div>
            </div>
            <hr />
            <div className="footer-recipes-list">
                <button className="btn btn-warning">Modifier</button>
                <button className="btn btn-danger">Supprimer</button>
            </div>
        </>
    );
}

interface RecipeProps {
    recipe: Recipe;
}

export default RecipeDisplay;