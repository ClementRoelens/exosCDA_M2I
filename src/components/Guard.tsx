import { ReactNode, useContext } from "react";
import AdminAuthorizationContext from "../contexts/AdminAuthorization";
import { Navigate } from "react-router-dom";

function Guard(props:GuardInterface) {
    const {authorization} = useContext(AdminAuthorizationContext);
   
    if (authorization){
        return (props.children);
    } else {
        return <Navigate to="/authentification"></Navigate>
    }
}

interface GuardInterface {
    children: ReactNode;
}

export default Guard;