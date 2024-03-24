import { useRef, useState } from "react";
import FormComponent from "../shared/FormComponent";
import { Alert } from "antd";
import { useAppDispatch } from "../../config/hook";
import { signup } from "./authSlice";

function SignUpComponent() {
  const dispatch = useAppDispatch();
  const emailRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const passwordRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const firstnameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const lastnameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const [isRejected, setIsRejected] = useState(false);

  async function signupHandler() {
    const email = emailRef.current.value;
    const password = passwordRef.current.value;
    const firstname = firstnameRef.current.value;
    const lastname = lastnameRef.current.value;

    if (email !== "" && password !== "" && firstname !== "" && lastname !== "") {
      const res = await dispatch(signup({ email, password, firstname, lastname })) as any;
      if (res.error) {
        setIsRejected(true);
      } else {
        setIsRejected(false);
      }
    }
  }

  return (
    <>
      <h1>Inscription</h1>
      <FormComponent submitFunction={signupHandler}>
        <div className="my-2">
          <label htmlFor="firstname" className="form-label"></label>
          <input type="text" className="form-control" id="firstname" required ref={firstnameRef} />
        </div>
        <div className="my-2">
          <label htmlFor="lastname" className="form-label"></label>
          <input type="text" className="form-control" id="lastname" required ref={lastnameRef} />
        </div>
        <div className="my-2">
          <label htmlFor="email" className="form-label"></label>
          <input type="email" className="form-control" id="email" required ref={emailRef} />
        </div>
        <div className="my-2">
          <label htmlFor="password" className="form-label"></label>
          <input type="password" className="form-control" id="password" required ref={passwordRef} />
        </div>
      </FormComponent>
      {isRejected && <Alert message="Erreur" description="L'authentification a échoué" type="error" />}
    </>
  )
}

export default SignUpComponent