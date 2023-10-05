import { ChangeEvent, FormEvent, useContext, useRef, useState } from "react";
import { FilmContext } from "../contexts/FilmContext";
import { Film } from "../models/Film";
import defaultImage from "../assets/default.jpg";

function FormComponent() {
    const { setFilms } = useContext(FilmContext);

    const titleRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const directorRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const releaseDateRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const imageRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const [isFormValid, setIsFormValid] = useState<boolean>(false);
    const [imagePath, setImagePath] = useState<string>(defaultImage);

    function sendFilms(e: FormEvent) {
        e.preventDefault();
        setFilms((prevFilms: Film[]) => [...prevFilms,
        new Film(
            titleRef.current.value,
            directorRef.current.value,
            new Date(releaseDateRef.current.value),
            imagePath)]);
    }

    function validateForm() {
        if (titleRef.current.value !== "" && directorRef.current.value !== "" && releaseDateRef.current.value !== "" && imageRef.current.value !== "") {
            setIsFormValid(true);
        } else {
            setIsFormValid(false);
        }
    }

    function imageUpdate(e:ChangeEvent<HTMLInputElement>) {
        if (e.target.files){
            if (e.target.files[0])
            setImagePath(URL.createObjectURL(e.target.files[0]));
        }
    }

    return (
        <form action="#" onSubmit={sendFilms} className="col-6">
            <img className="img-fluid h-25" src={imagePath} alt="Image du film" />
            <div className="d-flex flex-column justify-content-start">
                <label htmlFor="title" className="form-label text-start mx-3 mt-4">Titre : </label>
                <input type="text" id="title" placeholder="Titre" className="form-control" ref={titleRef} onInput={validateForm} />
            </div>
            <div className="d-flex flex-column justify-content-start">
                <label htmlFor="director" className="form-label text-start mx-3 mt-4">Réalisateur : </label>
                <input type="text" id="director" placeholder="Réalisateur" className="form-control" ref={directorRef} onInput={validateForm} />
            </div>
            <div className="d-flex flex-column justify-content-start">
                <label htmlFor="releaseDate" className="form-label text-start mx-3 mt-4">Date de sortie :</label>
                <input type="date" id="releaseDate" className="form-control" ref={releaseDateRef} onInput={validateForm} />
            </div>
            <div className="d-flex flex-column justify-content-start">
                <label htmlFor="imagePath" className="form-label text-start mx-3 mt-4">Image : </label>
                <input type="file" accept="image/*" id="imagePath" className="form-control" ref={imageRef} onChange={imageUpdate} onInput={validateForm}/>
            </div>
            <button type="submit" disabled={!isFormValid} className="btn btn-outline-dark mt-5">Envoyer</button>
        </form>
    );
}

export default FormComponent;