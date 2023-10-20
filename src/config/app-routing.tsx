import { createBrowserRouter } from "react-router-dom";
import Navbar from "../components/Navbar";
import App from "../App";

export const router = createBrowserRouter([
    {path : "/", element : <App/>, children :[
        {path:"/", element : <Navbar/> }
    ]}
]);