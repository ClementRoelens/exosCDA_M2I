import { ReactNode } from "react";
import { useAppSelector } from "../../config/hook";
import { Navigate } from "react-router-dom";

function AdminGuard(props: AdminGuardProps) {
  const user = useAppSelector(state => state.auth.user);

  if (user?.role === "ADMIN") {
    return props.children;
  }
  return <Navigate to={"/"} />
}

interface AdminGuardProps {
  children : ReactNode;
}

export default AdminGuard;