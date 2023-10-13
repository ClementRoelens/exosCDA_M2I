import { Link } from "react-router-dom";
import AdminAuthorizationContext from "../contexts/AdminAuthorization";
import { useContext } from "react";

function Navbar() {
    const {authorization, setAuthorization} = useContext(AdminAuthorizationContext);

    return (
        <header>
            <nav className="w-3/5 bg-slate-500 rounded-2xl text-lg text-white py-3 px-12 flex justify-around mx-auto">
                <Link to="/" className="bg-slate-700 rounded-xl py-1 px-4">Accueil</Link>
                <Link to="/card" className="bg-slate-700 rounded-xl py-1 px-4">Panier</Link>
                {authorization ?
                <button className="bg-slate-700 rounded-xl py-1 px-4" onClick={() => setAuthorization(false)}>Se déconnecter</button>
                :
                 <Link to="/authentification" className="bg-slate-700 rounded-xl py-1 px-4">Se connecter</Link>}
            </nav>
        </header>
    );
}

export default Navbar;