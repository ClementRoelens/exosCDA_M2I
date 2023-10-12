import axios from "axios";
import { useContext, useRef } from "react";
import { Article } from "../models/Article";
import apiURL from "../api-url";
import ArticleContext from "../contexts/ArticleContext";

function AddArticleSecure() {
    const nameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const hardwareRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const imageRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const { setArticles } = useContext(ArticleContext);

    function addArticle() {
        axios.post<Article>(apiURL, {
            name: nameRef.current.value,
            hardware: hardwareRef.current.value,
            imagePath: imageRef.current.value
        }).then(res => {
            setArticles((prevArticles: Article[]) => [...prevArticles, new Article(res.data.id, res.data.name, res.data.hardware, res.data.imagePath)]);
            nameRef.current.defaultValue = "";
            hardwareRef.current.defaultValue = "";
        })
            .catch(error => console.error("Une erreur est survenue lors de l'ajout d'un article", error));
    }

    return (
        <form action="#" onSubmit={addArticle}>
            <label htmlFor="name">Nom du jeu :</label>
            <input type="text" id="name" ref={nameRef} required />
            <label htmlFor="hardware">Plate-forme : </label>
            <input type="text" id="hardware" ref={hardwareRef} required />
            <label htmlFor="imagePath">Jaquette : </label>
            <input type="file" id="imagePath" ref={imageRef} required />
            <button type="submit">Ajouter</button>
        </form>
    );
}

export default AddArticleSecure;