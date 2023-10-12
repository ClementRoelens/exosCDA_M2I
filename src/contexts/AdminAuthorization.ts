import { createContext } from "react";

const AdminAuthorizationContext = createContext<AuthorizationInterface>({
    authorization:false,
    setAuthorization:() => {}
});

interface AuthorizationInterface {
    authorization : boolean;
    setAuthorization : React.Dispatch<React.SetStateAction<boolean>>
}

export default AdminAuthorizationContext;