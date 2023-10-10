import { Outlet } from "react-router-dom";
import NavBarComponent from "./NavBarComponent";

function ContainerComponent() {
    return (
        <>
            <NavBarComponent />
            <main className="container bg-dark text-light rounded p-4 mt-4">
                <Outlet />
            </main>
        </>
    )
}

export default ContainerComponent;