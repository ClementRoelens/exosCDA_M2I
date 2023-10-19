import { useEffect, useRef, useState } from "react";
import FormComponent from "../shared/FormComponent";
import { useAppDispatch, useAppSelector } from "../../config/hooks";
import { useNavigate, useSearchParams } from "react-router-dom";
import { Alert } from "antd";
import { signIn, signUp } from "./authSlice";

function SignUpOrIn() {
    const emailRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const passwordRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const [isFail, setIsFail] = useState(false);
    const [failMessage, setFailMessage] = useState("");

    const [searchParams] = useSearchParams();
    const mode = searchParams.get("mode");

    const dispatch = useAppDispatch();
    const user = useAppSelector(state => state.users.user);

    const navigate = useNavigate();

    useEffect(() => {
        if (mode !== "signin" && mode !== "signup"){
            navigate("/");
        }
    },[mode]);

    async function submitHandler() {
        if (emailRef.current.value !== "" && passwordRef.current.value !== "") {
            if (mode === "signin") {
                await dispatch(signIn({ email: emailRef.current.value, password: passwordRef.current.value }));
                console.log("signUpOrIn.submitHandler : fonction asynchrone signin finie");
                if (!localStorage.getItem("user")) {
                    console.log("pas d'user dans le localStorage");
                    setIsFail(true);
                    setFailMessage("Vos identifiants semblent être incorrects");
                } else {
                    console.log("user dans le localStorage");
                    navigate("/");
                }
            } else if (mode === "signup") {
                await dispatch(signUp({ email: emailRef.current.value, password: passwordRef.current.value }));
                console.log("signUpOrIn.submitHandler : fonction asynchrone signin finie");
                if (user === null) {
                    console.log("user null");
                    setIsFail(true);
                    setFailMessage("Il semble que le serveur rencontre un problème");
                } else {
                    console.log("user non null");
                    navigate("/");
                }
            }
        } else {
            // Normalement avec le required pas besoin, mais bon...
            setIsFail(true);
            setFailMessage("Vous devez renseigner un email et un mot de passe");
        }
    }

    return (
        <FormComponent submitFunction={submitHandler}>
            <h1 className="text-center">{mode === "signin" ? "Connexion" : "Inscription"}</h1>
            {isFail && <Alert className="mt-2" message="Erreur" description={failMessage} type="error" />}
            <div>
                <label htmlFor="email" className="form-label mt-2">Email : </label>
                <input type="text" className="form-control" id="email" ref={emailRef} required />
            </div>
            <div>
                <label htmlFor="password" className="form-label mt-2">Password : </label>
                <input type="password" className="form-control" id="password" ref={passwordRef} required />
            </div>
        </FormComponent>
    );
}

export default SignUpOrIn;