import { useState } from "react";
import AddArticleSecure from "./AddArticleSecure";

function Administration() {
    const [addMode, setAddMode] = useState<boolean>(false);

    return (<>
        <h2 className="mt-20 mx-auto text-center" >Bienvenue ô grand administrateur</h2>
        <div className="flex justify-around w-3/12 mx-auto mt-10">
            <button className="bg-zinc-300 px-3 py-2 border rounded-md" onClick={() => setAddMode(true)}>Ajouter un article</button>
            <button className="bg-zinc-300 px-3 py-2 border rounded-md">Se déconnecter</button>
        </div>
        {addMode && <AddArticleSecure />}
    </>);
}

export default Administration;