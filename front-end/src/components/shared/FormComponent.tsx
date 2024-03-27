import { FormEvent, ReactNode } from "react";

function FormComponent(props: FormProps) {

    function onSubmit(e: FormEvent) {
        e.preventDefault();
        props.submitFunction();
    }

    return (
        <form onSubmit={onSubmit} className="w-50 mx-auto">
            {props.children}
            <button className="btn btn-outline-dark mt-4 d-block mx-auto" type="submit">Valider</button>
        </form>
    );
}

interface FormProps {
    children: ReactNode;
    submitFunction: () => void;
}

export default FormComponent;