import { Link } from "react-router-dom";

function NavBarComponent() {
    return (
        <header>
            <nav className="navbar navbar-expand-lg bg-dark">
                <div className="container-fluid">
                    <Link className="navbar-brand text-light" to="/">eWebsite</Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                           <li className="nav-item">
                            <Link className="nav-link text-light" to="/">Accueil</Link>
                           </li>
                           <li className="nav-item">
                            <Link className="nav-link text-light" to="/contacts">Contacts</Link>
                           </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
}

export default NavBarComponent;