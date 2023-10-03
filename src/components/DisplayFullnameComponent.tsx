import { FormEvent, useState } from "react";

function DisplayFullnameComponent(){
    const [firstname,setFirstname] = useState<string>("John");
    const [lastname,setLastname] = useState<string>("DOE");

    function displayCapitalizedFirstname(e:FormEvent){
        const input = e.currentTarget as HTMLInputElement;
        setFirstname(input.value.substring(0,1).toUpperCase() + input.value.substring(1).toLowerCase());
    }

    return (<>
        <input type="text" onInput={displayCapitalizedFirstname} placeholder="Prénom"/>
        <input type="text" onInput={e => setLastname(e.currentTarget.value.toUpperCase())} placeholder="Nom de famille"/>
        <p>Bonjour <b>{`${firstname} ${lastname}`}</b>, bienvenue sur l'application</p>
    </>);
}

export default DisplayFullnameComponent;