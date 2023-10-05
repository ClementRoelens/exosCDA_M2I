import { useContext } from "react";
import { FilmContext } from "../contexts/FilmContext";
import { Film } from "../models/Film";

function ButtonComponent(props:ButtonProps){
    const {filmsState} = useContext(FilmContext);

    function logFilm(){
        const loggedFilm = filmsState.find((film:Film) => film.id === props.id);
        console.log(loggedFilm);
    }

    return (
        <>
        <button onClick={logFilm} className="btn btn-outline-dark">Logger le film</button>
        </>
    );
}

interface ButtonProps {
    id:number;
}

export default ButtonComponent;