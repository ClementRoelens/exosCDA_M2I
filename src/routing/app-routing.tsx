import { Navigate, createBrowserRouter } from "react-router-dom";
import HomeComponent from "../components/HomeComponent";
import AboutComponent from "../components/AboutComponent";
import ContactComponent from "../components/ContactComponent";
import ProjectsComponent from "../components/ProjectsComponent";
import App from "../App";

const router = createBrowserRouter([
    {
        path: "/", element: <App />, children: [
            { path: "/", element: <HomeComponent /> },
            { path: "/projects", element: <ProjectsComponent /> },
            { path: "/contact", element: <ContactComponent /> },
            { path: "/about", element: <AboutComponent /> },
            { path: "*", element: <Navigate to="/" replace /> }
        ]
    },
    { path: "*", element: <Navigate to="/" replace /> }

]);

export default router;