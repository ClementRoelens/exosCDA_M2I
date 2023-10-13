import { FormEvent, useContext, useRef, useState } from "react";
import AdminAuthorizationContext from "../contexts/AdminAuthorization";
import { useNavigate } from "react-router-dom";

function Authentification() {
    const nameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const passwordRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const { setAuthorization } = useContext(AdminAuthorizationContext);
    const [fail, setFail] = useState<boolean>(false);
    const navigate = useNavigate();

    function authentificate(e:FormEvent) {
        e.preventDefault();
        if (nameRef.current.value === "admin" && passwordRef.current.value === "admin") {
            setAuthorization(true);
            navigate("/");
        } else {
            setFail(true);
            setTimeout(()=>{
                navigate("/");
            },1500)
        }
    }

    return (<>
        <p className="text-center mx-auto text-sm my-14">Entrez admin et admin pour vous authentifier en tant qu'admin</p>
        <form className="bg-zinc-400 rounded w-2/6 mx-auto mt-4 flex flex-col p-3" action="#" onSubmit={authentificate}>
            <label htmlFor="name" className="my-2 ms-3">Nom :</label>
            <input className="rounded p-1 ps-2" type="text" id="name" ref={nameRef} required />
            <label htmlFor="password" className="my-2 ms-3">Mot de passe :</label>
            <input className="rounded p-1 ps-2" type="password" id="password" ref={passwordRef} required />
            <button className="block bg-zinc-500 text-white mx-auto mt-4 px-3 py-2 rounded-md border" type="submit">Se connecter</button>
        </form>
        {fail && <p className="text-center mx-auto mt-5 text-xl">Informations incorrectes</p>}
    </>
    );
}

export default Authentification;