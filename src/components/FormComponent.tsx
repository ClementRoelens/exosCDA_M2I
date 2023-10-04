import { FormEvent, useState } from "react";
import FormStyles from "./FormComponent.module.css";

function FormComponent(props:FormComponentProps) {
    const [firstname,setFirstname] = useState<string>("");
    const [lastname,setLastname] = useState<string>("");

    function sendPerson(e:FormEvent){
        e.preventDefault();
        const newPerson:Person = {
            firstname:firstname,
            lastname:lastname
        };
        props.transmitPerson(newPerson);
        setFirstname("");
        setLastname("");
    }
    
    return (
        <form action="#" onSubmit={sendPerson}>
            <label htmlFor="firstname" className={FormStyles.label}>Prénom :</label>
            <input type="text" id="firstname" onInput={e => setFirstname((e.target as HTMLInputElement).value)} value={firstname}/>
            <label htmlFor="lastname" className={FormStyles.label}>Nom : </label>
            <input type="text" id="lastname" onInput={e => setLastname((e.target as HTMLInputElement).value)} value={lastname}/>
            <button type="submit" className={FormStyles.submit}>Envoyer</button>
        </form>
    );
}

interface FormComponentProps {
    transmitPerson:Function;
}

interface Person {
    firstname: string;
    lastname: string;
}

export { FormComponent };
export type { Person };