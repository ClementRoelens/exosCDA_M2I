import { createBrowserRouter } from "react-router-dom";
import HomeComponent from "../components/HomeComponent";
import DisplayContactsComponent from "../components/DisplayContactsComponent";
import CRUDComponent from "../components/CRUDComponent";
import ErrorPageComponent from "../components/ErrorPageComponent";
import ContainerComponent from "../components/ContainerComponent";

export const router = createBrowserRouter([
    {
        path: "/", element: <ContainerComponent />, children: [
            { path: "/", element: <HomeComponent /> },
            { path: "/contacts", element: <DisplayContactsComponent /> },
            { path: "/contacts/create", element: <CRUDComponent /> },
            // { path: "/contacts/:param/:contactId", element: <CRUDComponent /> },
            { path: "/contacts/display/:contactId", element: <CRUDComponent /> },
            { path: "/contacts/edit/:contactId", element: <CRUDComponent /> },
            { path: "/contacts/delete/:contactId", element: <CRUDComponent /> },
            { path: "/error", element: <ErrorPageComponent /> }
        ], errorElement : <ErrorPageComponent />
    }
]);