import { useNavigate, useSearchParams } from "react-router-dom";

function Error() {
    const [params] = useSearchParams();
    const message = params.get("message");
    const navigate = useNavigate();

    setTimeout(() => navigate("/"), 3000);

    return (<>
        <h1 className="text-center mt-3">Oups, quelque chose s'est mal passé</h1>
        <p className="text-center mt-5 fs-4">{message ? message : "Dommage"}</p>
    </>
    );
}

export default Error;