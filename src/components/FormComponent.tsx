import { FormEvent, ReactNode, useState } from "react";


function FormComponent(props:FormComponentProps) {

    function submit(e:FormEvent){
        e.preventDefault();
        props.submitPerson(props.person);
    }
 

    return (
        <form onSubmit={submit}>
          {props.children}
        </form>
    );
}

interface FormComponentProps {
    children:ReactNode;
    person:Person;
    submitPerson:Function;
}

interface Person {
    firstname: string;
    lastname: string;
    age:number;
}

export { FormComponent };
export type { Person };