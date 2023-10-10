import { createBrowserRouter } from "react-router-dom";
import HomeComponent from "../components/HomeComponent";
import DisplayContactsComponent from "../components/DisplayContactsComponent";
import CreateEditOrDeleteContactComponent from "../components/CreateEditOrDeleteContactComponent";
import ErrorPageComponent from "../components/ErrorPageComponent";
import ContainerComponent from "../components/ContainerComponent";

export const router = createBrowserRouter([
    {
        path: "/", element: <ContainerComponent />, children: [
            { path: "/", element: <HomeComponent /> },
            { path: "/contacts", element: <DisplayContactsComponent /> },
            { path: "/contacts/create", element: <CreateEditOrDeleteContactComponent /> },
            { path: "/contacts/edit/:contactId", element: <CreateEditOrDeleteContactComponent /> },
            { path: "/contacts/delete/:contactId", element: <CreateEditOrDeleteContactComponent /> },
            { path: "/error", element: <ErrorPageComponent /> }
        ]
    }
]);