import { Link } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../hooks";
import { removeUser } from "./authSlice";

function Navbar() {
    const user = useAppSelector(state => state.auth.user);
    const dispatch = useAppDispatch();

    return (
        <header>
            <nav className="navbar bg-dark">
                <div className="container-fluid">
                    <Link className="navbar-brand text-light" to="/"><i className="bi bi-globe"></i> eRecipes</Link>
                <ul className="navbar nav">
                {user ?
                    <li className="nav-item"><button className="nav-link text-light" onClick={() => dispatch(removeUser())}>Se déconnecter</button></li>
                    :
                    <>
                        <li className="nav-item"><Link className="nav-link text-light" to="/signin?mode=signin">Se connecter</Link></li>
                        <li className="nav-item"><Link className="nav-link text-light" to="/signup?mode=signup">S'inscrire</Link></li>
                    </>
                }
                </ul>
                </div>
            </nav>
        </header>
    );
}

export default Navbar;