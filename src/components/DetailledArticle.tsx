import { useEffect, useState, useContext } from "react";
import { Article } from "../models/Article";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import apiURL from "../api-url";
import CartContext from "../contexts/CartContext";
import { CartArticle } from "../models/Cart";
import AdminAuthorizationContext from "../contexts/AdminAuthorization";
import ArticleContext from "../contexts/ArticleContext";

function DetailledArticle() {
    const [article, setArticle] = useState<Article | null>(null);
    const {setArticles} = useContext(ArticleContext);
    const { setCart } = useContext(CartContext);
    const { authorization } = useContext(AdminAuthorizationContext);
    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        console.log("detailledArticle.useEffect() lancé");
        if (id && !isNaN(+id)) {
            axios.get<Article>(apiURL + id)
                .then(res => {
                    setArticle(new Article(res.data.id, res.data.name, res.data.hardware, res.data.imagePath));
                    console.log(article);
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération de l'article " + id, error);
                    setTimeout(() => navigate("/"), 2000);
                });
        }
    }, [id])

    function addToCart(): void {
        setCart((prevCart: CartArticle[]) => {
            const index = prevCart.findIndex((cartArticle: CartArticle) => cartArticle.article.id === article?.id);
            let newCart: CartArticle[] = [];
            if (index !== -1) {
                newCart = prevCart.map((cartArticle: CartArticle) => cartArticle.article.id === article?.id ? { article: cartArticle.article, quantity: cartArticle.quantity + 1 } : cartArticle);
            } else {
                newCart = [...prevCart, { article: article!, quantity: 1 }];
            }
            localStorage.setItem("cart", JSON.stringify(newCart));
            return newCart;
        });
    }

    function removeArticle(){
        axios.delete(apiURL+id)
        .then(() => {
            setArticles((prevArticles:Article[]) => prevArticles.filter((article:Article) => article.id !== Number(id)))
            navigate("/");
        })
        .catch(error => console.error("Erreur lors de la suppression de "+id,error));
    }

    return (
        <>
            {article ?
                <div className="flex flex-col items-center text-center">
                    <h2 className="text-5xl">{article.name}</h2>
                    <p className="text-lg my-3 underline underline-offset-4">Disponible sur {article.hardware}</p>
                    <img className="w-2/12 my-3" src={article.imagePath} alt={article.name} />
                    <button className="border-2 border-black py-2 px-4 rounded-lg mt-4" onClick={addToCart}>Ajouter au panier</button>
                    {authorization && <button className="border-2 border-black py-2 px-4 rounded-lg mt-4" onClick={removeArticle}>SUPPRIMER ARTICLE</button>}
                </div>
                :
                <h2>Chargment...</h2>
            }
        </>
    );
}

export default DetailledArticle;