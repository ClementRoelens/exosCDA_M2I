import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import ArticlesList from "./components/ArticlesList";
import DetailledArticle from "./components/DetailledArticle";
import Cart from "./components/Cart";
import Authentification from "./components/Authentification";
import AdministrationSecure from "./components/AdministrationSecure";
import Guard from "./components/Guard";

const router = createBrowserRouter([
    {
        path: "/", element: <App />, children: [
            { path: "/", element: <ArticlesList /> },
            { path: "/detail/:id", element: <DetailledArticle /> },
            { path: "/card", element: <Cart /> },
            { path: "/authentification", element: <Authentification /> },
            { path: "/admin", element: <Guard><AdministrationSecure /></Guard> }
        ]
    }
]);

export default router;