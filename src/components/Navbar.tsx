import { Link } from "react-router-dom";

function Navbar() {
    return (
        <header>
            <nav className="w-3/5 bg-slate-500 rounded-2xl text-lg text-white py-3 px-12 flex justify-around mx-auto">
                <Link to="/" className="bg-slate-700 rounded-xl py-1 px-2">Accueil</Link>
                <Link to="/card" className="bg-slate-700 rounded-xl py-1 px-2">Panier</Link>
                <Link to="/authentification" className="bg-slate-700 rounded-xl py-1 px-2">Se connecter</Link>
            </nav>
        </header>
    );
}

export default Navbar;