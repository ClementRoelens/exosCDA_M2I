import { Link } from "react-router-dom";

function NavBarComponent() {
    return (<>
        <nav className="navbar navbar-expand-lg bg-dark w-100">
            <div className="container-fluid ">
                <a className="navbar-brand text-light" href="#">Navbar</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link active text-light" to="/">Home</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link active text-light" to="/projects">Projets</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link active text-light" to="/contact">Contactez-moi</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link active text-light" to="/about">À propos</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </>);
}

export default NavBarComponent;