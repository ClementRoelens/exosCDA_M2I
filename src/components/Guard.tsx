import { ReactNode, useContext } from "react";
import AdminAuthorizationContext from "../contexts/AdminAuthorization";
import { useNavigate } from "react-router-dom";

function Guard(props:GuardInterface) {
    const {authorization} = useContext(AdminAuthorizationContext);
    const navigate = useNavigate();

    if (authorization){
        return (props.children);
    } else {
        navigate("/authentification");
    }
}

interface GuardInterface {
    children: ReactNode;
}

export default Guard;