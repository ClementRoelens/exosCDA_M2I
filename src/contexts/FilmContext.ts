import { createContext } from "react";
import { Film } from "../models/Film";

export const FilmContext = createContext<FilmContext>({
    filmsState : [],
    setFilms : () => {}
});

interface FilmContext {
    filmsState:Film[];
    setFilms: React.Dispatch<React.SetStateAction<Film[]>>;
}