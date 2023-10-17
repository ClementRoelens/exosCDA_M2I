import { Link } from "react-router-dom";
import { useAppSelector } from "../hooks";
import { Recipe } from "../models/Recipe";
import RecipeDisplay from "./RecipeDisplay";

function RecipeList() {
    const recipes = useAppSelector(state => state.recipe.recipes);

    return (
        <>
            <div className="header-recipes-list row p-3">
                <h2 className="col-6">Liste des recettes</h2>
                <Link className="btn btn-success ms-auto col-1" to="/add"><i className="bi-bi-plus"> Ajouter</i></Link>
            </div>
            <hr />
            <ul className="list-group">
                {recipes.map((recipe: Recipe) =>
                    <li className="list-group-item" key={recipe.id}><RecipeDisplay recipe={recipe} /></li>
                )}
            </ul>
        </>
    );
}

export default RecipeList;