import { ReactNode } from "react";
import { Navigate } from "react-router-dom";

function Guard(props: GuardProps) {
    const user = localStorage.getItem("user");

    return (
        <>
            {user ? props.children : <Navigate to="/sign?mode=signin" />}
        </>
    );
}

interface GuardProps {
    children: ReactNode;
}

export default Guard;