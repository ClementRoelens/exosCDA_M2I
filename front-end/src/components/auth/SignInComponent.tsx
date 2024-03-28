import { useRef, useState } from "react"
import FormComponent from "../shared/FormComponent";
import { useAppDispatch } from "../../config/hook";
import { signin } from "./authSlice";
import { Alert } from "antd";
import { useNavigate } from "react-router-dom";

function SignInComponent() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const emailRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const passwordRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const [isRejected, setIsRejected] = useState(false);

  async function signinHandler() {
    const email = emailRef.current.value;
    const password = passwordRef.current.value;

    if (email !== "" && password !== ""){
      const res = await dispatch(signin({email, password})) as any;
      if (res.error){
        setIsRejected(true);
      } else {
        setIsRejected(false);
        navigate("/");
      }
    }
  }

  return (
    <>
      <h1 className="text-center">Connexion</h1>
      <FormComponent submitFunction={signinHandler}>
        <div className="my-2">
          <label htmlFor="email" className="form-label"></label>
          <input type="email" className="form-control" id="email" required ref={emailRef} />
        </div>
        <div className="my-2">
          <label htmlFor="password" className="form-label"></label>
          <input type="password" className="form-control" id="password" required ref={passwordRef} />
        </div>
      </FormComponent>
      {isRejected && <Alert className="mt-4" message="Erreur" description="L'authentification a échoué" type="error"/>}
    </>
  )
}

export default SignInComponent