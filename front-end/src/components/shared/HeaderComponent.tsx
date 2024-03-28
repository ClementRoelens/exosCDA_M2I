import { Link } from "react-router-dom"
import { useAppDispatch, useAppSelector } from "../../config/hook"
import { getStoredUser, signout } from "../auth/authSlice";
import { useEffect } from "react";

function HeaderComponent() {
  const user = useAppSelector(state => state.auth.user);
  const dispatch = useAppDispatch();

  useEffect(() => {
    if (!user){
      dispatch(getStoredUser());
    }
   },[]);

  return (
    <header>
      <nav className="navbar bg-dark">
        <div className="container-fluid">
          <Link className="navbar-brand text-light" to="/"><i className="bi bi-globe"></i> {user ? user.firstname : "Visiteur"}</Link>
          <ul className="navbar nav">
            {user ?
              <>
                {user.role === "ROLE_ADMIN" && <li className="nav-item"><Link className="nav-link text-light" to="/add-todo">Nouvelle tâche</Link></li>}
                {user.role === "ROLE_ADMIN" && <li className="nav-item"><Link className="nav-link text-light" to={"/todos/" + user.id}>Mes tâches</Link></li>}
                <li className="nav-item"><button className="nav-link text-light" onClick={() => dispatch(signout())}>Se déconnecter</button></li>
              </>
              :
              <>
                <li className="nav-item"><Link className="nav-link text-light" to="/signin">Se connecter</Link></li>
                <li className="nav-item"><Link className="nav-link text-light" to="/signup">S'inscrire</Link></li>
              </>
            }
          </ul>
        </div>
      </nav>
    </header>
  )
}

export default HeaderComponent