import { FormEvent, useRef, useState, useContext } from "react";
import FormArticleSecure from "./FormArticleSecure";
import axios from "axios";
import apiURL from "../api-url";
import { useNavigate } from "react-router-dom";
import AdminAuthorizationContext from "../contexts/AdminAuthorization";

function Administration() {
    const [addMode, setAddMode] = useState<boolean>(false);
    const [editPasswordMode, setEditPasswordMode] = useState<boolean>(false);
    const {setAuthorization} = useContext(AdminAuthorizationContext);

    const navigate = useNavigate();

    const passwordRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    function editPassword(e: FormEvent) {
        e.preventDefault();
        axios.patch<{ admin: string, password: string }>(`${apiURL}adminCredentials`, {password : passwordRef.current.value})
        .then(() => navigate("/"))
        .catch(error => console.error("Erreur lors de la modification du mot de passe",error))
    }

    function logout(){
        setAuthorization(false);
        navigate("/");
    }

    return (<>
        <h2 className="mt-20 mx-auto text-center" >Bienvenue ô grand administrateur</h2>
        {editPasswordMode &&
            <form className="bg-zinc-300 p-2 w-2/6 mx-auto rounded flex flex-col align-center" action="#" onSubmit={editPassword}>
                <input className="rounded p-1 ps-2" type="password" ref={passwordRef} required />
                <button className="bg-zinc-300 px-3 py-2 border rounded-md mt-2 w-3/6 mx-auto" type="submit">Valider</button>
            </form>}
        <div className="flex justify-around w-3/6 mx-auto mt-10">
            <button className="bg-zinc-300 px-3 py-2 border rounded-md" onClick={() => setAddMode(true)}>Ajouter un article</button>
            <button className="bg-zinc-300 px-3 py-2 border rounded-md" onClick={() => setEditPasswordMode(true)}>Modifier mon mot de passe</button>
            <button className="bg-zinc-300 px-3 py-2 border rounded-md" onClick={logout}>Se déconnecter</button>
        </div>
        {addMode && <FormArticleSecure />}
    </>);
}

export default Administration;