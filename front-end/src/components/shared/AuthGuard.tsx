import { ReactNode } from "react";
import { useAppSelector } from "../../config/hook";
import { Navigate } from "react-router-dom";

function AuthGuard(props: AuthGuardProps) {
  const user = useAppSelector(state => state.auth.user);

  if (user) {
    return props.children;
  }
  return <Navigate to={"/"} />
}

interface AuthGuardProps {
  children : ReactNode;
}

export default AuthGuard;