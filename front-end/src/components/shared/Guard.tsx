import { ReactNode } from "react";
import { useAppSelector } from "../../config/hook";
import { Navigate } from "react-router-dom";

function Guard(props: GuardProps) {
  const user = useAppSelector(state => state.auth.user);

  if (user) {
    return props.children;
  }
  return <Navigate to={"/"} />
}

interface GuardProps {
  children : ReactNode;
}

export default Guard;