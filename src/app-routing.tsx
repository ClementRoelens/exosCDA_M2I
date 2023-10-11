import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import DetailledTask from "./components/DetailledTask";
import TaskList from "./components/TaskList";
import Form from "./components/Form";
import Error from "./components/Error";

export const router = createBrowserRouter([
    {
        path: "/", element: <App />, children: [
            { path: "/", element: <TaskList /> },
            { path: "/add", element: <Form /> },
            { path: "/edit/:id", element: <Form /> },
            { path: "/:id", element: <DetailledTask /> },
            { path: "/error", element: <Error /> }
        ]
    },

]);