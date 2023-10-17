import { FormEvent, useRef } from "react";
import { signIn, signUp } from "../services/AuthService";
import { useAppDispatch } from "../hooks";
import { setUser } from "./authSlice";
import { useSearchParams } from "react-router-dom";

function SignForm() {
    const emailRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const passwordRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const [searchParams] = useSearchParams();
    const mode = searchParams.get("mode");

    const dispatch = useAppDispatch();

    async function submitHandler(e: FormEvent) {
        e.preventDefault();
        if (emailRef.current.value !== "" && passwordRef.current.value !== "") {
            if (mode === "signin"){
                console.log("Mode Signin déclenché");
                const response = await signIn({ email: emailRef.current.value, password: passwordRef.current.value });
                if (response.success) {
                    dispatch(setUser({ email: emailRef.current.value, token: response.res }));
                }
            } else if (mode === "signup"){
                console.log("Mode Signup déclenché");
                const response = await signUp({ email: emailRef.current.value, password: passwordRef.current.value });
                if (response.success) {
                    dispatch(setUser({ email: emailRef.current.value, token: response.res.idToken }));
                }
            }
        }
    }

    return (
        <form action="#" onSubmit={submitHandler}>
            <div>
                <label htmlFor="email" className="form-label">Email : </label>
                <input type="text" className="form-control" id="email" ref={emailRef} required />
            </div>
            <div>
                <label htmlFor="password" className="form-label">Password : </label>
                <input type="password" className="form-control" id="password" ref={passwordRef} required />
            </div>
            <button className="btn btn-outline-light mt-3 d-block mx-auto" type="submit">Valider</button>
        </form>
    );
}

export default SignForm;