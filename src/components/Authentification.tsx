import { useContext, useRef, useState } from "react";
import AdminAuthorizationContext from "../contexts/AdminAuthorization";
import { useNavigate } from "react-router-dom";

function Authentification() {
    const nameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const passwordRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const { setAuthorization } = useContext(AdminAuthorizationContext);
    const [fail, setFail] = useState<boolean>(false);
    const navigate = useNavigate();

    function authentificate() {
        if (nameRef.current.value === "admin" && passwordRef.current.value === "admin") {
            setAuthorization(true);
            navigate("/");
        } else {
            setFail(true);
            setTimeout(()=>{
                navigate("/");
            },1000)
        }
    }

    return (<>
        <p>Entrez admin et admin pour vous authentifier en tant qu'admin</p>
        <form action="#" onSubmit={authentificate}>
            <label htmlFor="name">Nom :</label>
            <input type="text" id="name" ref={nameRef} required />
            <label htmlFor="password">Mot de passe :</label>
            <input type="password" id="password" ref={passwordRef} required />
            <button type="submit">Se connecter</button>
        </form>
        {fail && <p>Informations incorrectes</p>}
    </>
    );
}

export default Authentification;