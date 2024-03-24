import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import SignInComponent from "../components/auth/SignInComponent";
import SignUpComponent from "../components/auth/SignUpComponent";
import TodoListComponent from "../components/todo/TodoListComponent";
import TodoDisplayComponent from "../components/todo/TodoDisplayComponent";
import Guard from "../components/shared/Guard";
import TodoForm from "../components/todo/TodoForm";

export const router = createBrowserRouter([
    {
        path: "/", element: <App />, children: [
            { path: "/", element: <SignInComponent /> },
            { path: "/signup", element: <SignUpComponent /> },
            { path: "/signin", element: <SignInComponent /> },
            { path: "/todos", element: <Guard><TodoListComponent /></Guard> },
            { path: "/todos/:id", element: <Guard><TodoListComponent /></Guard> },
            { path: "/one-todo/:id", element: <Guard><TodoDisplayComponent /></Guard> },
            { path: "/add-todo", element: <Guard><TodoForm /></Guard> }
        ]
    }
]);