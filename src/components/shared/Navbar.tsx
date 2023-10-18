import { Link } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../config/hooks";
import { removeUser } from "../users/authSlice";

function Navbar() {
    const user = useAppSelector(state => state.users.user);
    const dispatch = useAppDispatch();

    return (
        <header>
            <nav className="navbar bg-dark">
                <div className="container-fluid">
                    <Link className="navbar-brand text-light" to="/"><i className="bi bi-globe"></i> {user ? user.email : "Visiteur"}</Link>
                    <ul className="navbar nav">
                        {user ?
                            <li className="nav-item"><button className="nav-link text-light" onClick={() => dispatch(removeUser())}>Se déconnecter</button></li>
                            :
                            <>
                                <li className="nav-item"><Link className="nav-link text-light" to="/sign?mode=signin">Se connecter</Link></li>
                                <li className="nav-item"><Link className="nav-link text-light" to="/sign?mode=signup">S'inscrire</Link></li>
                            </>
                        }
                    </ul>
                </div>
            </nav>
        </header>
    );
}

export default Navbar;