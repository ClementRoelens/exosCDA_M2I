import { ReactNode } from "react";
import { useAppSelector } from "../../config/hooks";
import { Navigate } from "react-router-dom";

function Guard(props: GuardProps) {
    const user = useAppSelector(state => state.users.user);

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