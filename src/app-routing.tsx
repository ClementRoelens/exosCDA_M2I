import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import RecipeList from "./components/RecipeList";
import SignForm from "./components/SignForm";
import RecipeForm from "./components/RecipeForm";

const router = createBrowserRouter([
    {
        path: "/", element: <App />, children: [
            { path: "/", element: <RecipeList /> },
            { path: "/add", element: <RecipeForm /> },
            { path: "/signin", element: <SignForm /> },
            { path: "/signup", element: <SignForm /> }
        ]
    }
]);

export default router;