import { useState } from "react";
import AddArticleSecure from "./AddArticleSecure";

function Administration() {
    const [addMode, setAddMode] = useState<boolean>(false);

    return (<>
        <h2>Bienvenue ô grand administrateur</h2>
        <div>
            <button onClick={() => setAddMode(true)}>Ajouter un article</button>
            <button>Se déconnecter</button>
        </div>
        {addMode && <AddArticleSecure />}
    </>);
}

export default Administration;