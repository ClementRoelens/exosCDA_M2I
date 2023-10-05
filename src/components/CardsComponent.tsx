import { useContext } from "react";
import { FilmContext } from "../contexts/FilmContext";
import { Film } from "../models/Film";
import ButtonComponent from "./ButtonComponent";

function CardsComponent(){
    const {filmsState} = useContext(FilmContext);

    if (filmsState.length > 0){
        return (
            <div className="col-6">
            <ul className="list-group row">
                {filmsState.map((film:Film,index:number) => 
                <li key={index} className="list-group-item col-6 h-25 border border-0">
                    <div className="card">
                        <img className="card-img-top h-25" src={film.posterPath} alt={`Affiche de ${film.title}`} />
                        <div className="card-body">
                            <h5 className="card-title">{film.title}</h5>
                            <p>Réalisé par <b>{film.director}</b> et sorti en <span className="fst-italic">{film.releaseDate.toLocaleDateString()}</span></p>
                            <ButtonComponent id={film.id}/>
                        </div>
                    </div>
                </li>)}
            </ul>
            </div>
        );
    }
    return <p className="col-6 display-5 d-flex align-self-center">Tu n'as pas encore ajouté de films</p>
}

export default CardsComponent;