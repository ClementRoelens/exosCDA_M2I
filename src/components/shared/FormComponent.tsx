import { FormEvent, ReactNode } from "react";

function FormComponent(props: FormProps) {

    function onSubmit(e: FormEvent) {
        e.preventDefault();
        props.submitFunction();
    }

    return (
        <form action="#" onSubmit={onSubmit} className="p-3">
            {props.children}
            <button className="btn btn-outline-light mt-3 d-block mx-auto" type="submit" disabled={props.disabled}>Valider</button>
        </form>
    );
}

interface FormProps {
    children: ReactNode;
    submitFunction: () => void;
    disabled:boolean;
}

export default FormComponent;