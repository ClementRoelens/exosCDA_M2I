import { FormEvent, useRef } from "react";

function RecipeForm() {
    const titleRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const cooktimeRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const preptimeRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const instructionsRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const ingredientsRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    function addRecipe(e: FormEvent) {
        e.preventDefault();

    }

    return (
        <form action="#" onSubmit={addRecipe}>
            <div>
                <label htmlFor="title" className="form-label">Titre : </label>
                <input type="text" className="form-control" id="title" ref={titleRef} required />
            </div>
            <div>
                <label htmlFor="cookTime" className="form-label">Temps de préparation : </label>
                <input type="number" className="form-control" id="cookTime" ref={cooktimeRef} required />
            </div>
            <div>
                <label htmlFor="prepTime" className="form-label">Temps de cuisson : </label>
                <input type="number" className="form-control" id="prepTime" ref={preptimeRef} required />
            </div>
            <div>
                <label htmlFor="instructions" className="form-label">Instructions : </label>
                <input type="text" className="form-control" id="instructions" ref={instructionsRef} required />
            </div>
            <div>
                <label htmlFor="ingredients" className="form-label">Ingrédients : </label>
                <input type="text" className="form-control" id="ingredients" ref={ingredientsRef} required />
            </div>
            <button className="btn btn-outline-light mt-2 d-block mx-auto" type="submit">Valider</button>
        </form>
    );
}

export default RecipeForm;