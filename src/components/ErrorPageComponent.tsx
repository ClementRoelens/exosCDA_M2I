import { useRouteError } from "react-router-dom";

function ErrorPageComponent() {
    const error = useRouteError() as Error;
    console.log(error);

    return (<>
        <h1>Erreur : {error ? error.name : "Une erreur s'est produite"}</h1>
        <p>{error ? error.message : "C'est dommage"}</p>
    </>);
}

export default ErrorPageComponent;