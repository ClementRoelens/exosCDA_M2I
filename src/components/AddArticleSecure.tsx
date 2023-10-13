import axios from "axios";
import { FormEvent, useContext, useRef } from "react";
import { Article } from "../models/Article";
import apiURL from "../api-url";
import ArticleContext from "../contexts/ArticleContext";
import { useNavigate } from "react-router-dom";

function AddArticleSecure() {
    const nameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const hardwareRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const imageRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const priceRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const { setArticles } = useContext(ArticleContext);

    const navigate = useNavigate();

    function addArticle(e:FormEvent) {
        e.preventDefault()
     
        axios.post<Article>(apiURL, {
            name: nameRef.current.value,
            hardware: hardwareRef.current.value,
            imagePath: `/src/assets/${imageRef.current.value.split('\\')[2]}`,
            price: priceRef.current.value
        }).then(res => {
            setArticles((prevArticles: Article[]) => [...prevArticles, new Article(res.data.id, res.data.name, res.data.hardware, res.data.imagePath, res.data.price)]);
            nameRef.current.defaultValue = "";
            hardwareRef.current.defaultValue = "";
            navigate("/");
        })
            .catch(error => console.error("Une erreur est survenue lors de l'ajout d'un article", error));
    }

    return (
        <form className="bg-zinc-400 rounded-md w-2/6 mx-auto mt-8 flex flex-col p-4" action="#" onSubmit={addArticle}>
            <label className="my-2 ms-3" htmlFor="name">Nom du jeu :</label>
            <input className="rounded p-1 ps-2" type="text" id="name" ref={nameRef} required />
            <label className="my-2 ms-3" htmlFor="hardware">Plate-forme : </label>
            <input className="rounded p-1 ps-2" type="text" id="hardware" ref={hardwareRef} required />
            <label className="my-2 ms-3" htmlFor="price">prix</label>
            <input className="rounded mx-auto" type="number" id="price" />
            <label className="my-2 ms-3" htmlFor="imagePath">Jaquette : </label>
            <input className="rounded mx-auto" type="file" accept="image/*" id="imagePath" ref={imageRef} required />
            <button className="block bg-zinc-500 text-white mx-auto mt-4 px-3 py-2 rounded-md border" type="submit">Ajouter</button>
        </form>
    );
}

export default AddArticleSecure;