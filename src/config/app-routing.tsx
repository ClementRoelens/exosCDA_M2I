import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import AlbumList from "../components/albums/AlbumList";
import SignUpOrIn from "../components/users/SignUpOrIn";
import AddAlbum from "../components/albums/AddAlbum";

export const router = createBrowserRouter([
    {
        path: "/", element: <App />, children: [
            { path: "/", element: <AlbumList /> },
            { path: "/sign", element: <SignUpOrIn /> },
            { path: "addAlbum", element: <AddAlbum /> }
        ]
    },
]);