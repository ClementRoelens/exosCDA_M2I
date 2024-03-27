import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import SignInComponent from "../components/auth/SignInComponent";
import SignUpComponent from "../components/auth/SignUpComponent";
import TodoListComponent from "../components/todo/TodoListComponent";
import TodoDisplayComponent from "../components/todo/TodoDisplayComponent";
import AuthGuard from "../components/shared/AuthGuard";
import TodoForm from "../components/todo/TodoForm";
import AdminGuard from "../components/shared/AdminGuard";

export const router = createBrowserRouter([
    {
        path: "/", element: <App />, children: [
            { path: "/", element: <SignInComponent /> },
            { path: "/signup", element: <SignUpComponent /> },
            { path: "/signin", element: <SignInComponent /> },
            { path: "/todos", element: <AuthGuard><TodoListComponent /></AuthGuard> },
            { path: "/todos/:id", element: <AdminGuard><TodoListComponent /></AdminGuard> },
            { path: "/one-todo/:id", element: <AdminGuard><TodoDisplayComponent /></AdminGuard> },
            { path: "/add-todo", element: <AdminGuard><TodoForm /></AdminGuard> }
        ]
    }
]);