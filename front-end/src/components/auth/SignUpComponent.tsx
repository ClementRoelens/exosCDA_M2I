import { useRef, useState } from "react";
import FormComponent from "../shared/FormComponent";
import { Alert } from "antd";
import { useAppDispatch } from "../../config/hook";
import { signup } from "./authSlice";

function SignUpComponent() {
  const dispatch = useAppDispatch();
  const firstnameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const lastnameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const emailRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const passwordRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const passwordCheckRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const roleRef = useRef() as React.MutableRefObject<HTMLSelectElement>;
  const [isRejected, setIsRejected] = useState(false);
  const [isPasswordsDifferents, setIsPasswordsDifferents] = useState(false);

  async function signupHandler() {
    const firstname = firstnameRef.current.value;
    const lastname = lastnameRef.current.value;
    const email = emailRef.current.value;
    const password = passwordRef.current.value;
    const passwordCheck = passwordCheckRef.current.value;
    const role = roleRef.current.value;

    if (email !== "" && password !== "" && firstname !== "" && lastname !== "") {
      if (password !== passwordCheck){
        setIsPasswordsDifferents(true);
      } else {
        const res = await dispatch(signup({ firstname, lastname, email, password, role })) as any;
        if (res.error) {
          setIsRejected(true);
        } else {
          setIsRejected(false);
        }
      }
    }
  }

  return (
    <>
      <h1 className="text-center">Inscription</h1>
      <FormComponent submitFunction={signupHandler}>
        <div className="my-3">
          <label htmlFor="firstname" className="form-label">Prénom :</label>
          <input type="text" className="form-control" id="firstname" required ref={firstnameRef} />
        </div>
        <div className="my-3">
          <label htmlFor="lastname" className="form-label">Nom :</label>
          <input type="text" className="form-control" id="lastname" required ref={lastnameRef} />
        </div>
        <div className="my-3">
          <label htmlFor="email" className="form-label">E-mail :</label>
          <input type="email" className="form-control" id="email" required ref={emailRef} />
        </div>
        <div className="my-3">
          <label htmlFor="password" className="form-label">Mot de passe :</label>
          <input type="password" className="form-control" id="password" required ref={passwordRef} />
        </div>
        <div className="my-3">
          <label htmlFor="passwordCheck" className="form-label">Confirmation du mot de passe :</label>
          <input type="password" className="form-control" id="passwordCheck" required ref={passwordCheckRef} />
        </div>
        {isPasswordsDifferents && <p className="text-danger">Les deux mots de passes sont différents</p>}
        <div className="my-3">
          <label htmlFor="role" className="form-label">Role :</label>
          <select className="form-select" id="role" ref={roleRef} defaultValue={"ROLE_USER"}>
            <option value="ROLE_USER">User</option>
            <option value="ROLE_ADMIN">Admin</option>
          </select>
        </div>
      </FormComponent>
      {isRejected && <Alert className="mt-4" message="Erreur" description="L'authentification a échoué" type="error" />}
    </>
  )
}

export default SignUpComponent