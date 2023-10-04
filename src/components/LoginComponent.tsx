import { MutableRefObject, useRef } from "react";
import { LoginType } from "../types/LoginType";
import classes from "./LoginComponent.module.css";

function LoginComponent(props:LoginProps){
    const emailRef = useRef() as MutableRefObject<HTMLInputElement>;
    const passwordRef = useRef() as MutableRefObject<HTMLInputElement>;

    function sendLoginInputs(){
        props.transmitInputs({email:emailRef.current.value , password : passwordRef.current.value});
    }

    return (
        <form action="#" onSubmit={sendLoginInputs}>
            <label htmlFor="email" className={classes.block}>E-mail : </label>
            <input type="text" id="email" ref={emailRef} className={classes.block}/>
            <label htmlFor="password" className={classes.block}>Mot de passe : </label>
            <input type="password" id="password" ref={passwordRef} className={classes.block}/>
            <button type="submit" className={classes.block}>Envoyer</button>
        </form>
    );
}

interface LoginProps {
    transmitInputs :(inputs:LoginType) => void;
}

export default LoginComponent;