import { Link } from "react-router-dom";

function Navbar() {
    return (
        <header>
            <nav className="d-flex justify-content-around w-50 mx-auto mt-3">
                <Link className="btn btn-dark" to="/">Accueil</Link>
                <Link className="btn btn-dark" to="/add">Ajouter une tâche</Link>
            </nav>
        </header>
    );
}

export default Navbar;