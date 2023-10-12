import { useEffect, useState, useContext } from "react";
import { Article } from "../models/Article";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import apiURL from "../api-url";
import CartContext from "../contexts/CartContext";
import { CartArticle } from "../models/Cart";

function DetailledArticle() {
    const [article, setArticle] = useState<Article | null>(null);
    const { setCart } = useContext(CartContext);
    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        if (id && !isNaN(+id)) {
            axios.get<Article>(apiURL + id)
                .then(res => {
                    setArticle(new Article(res.data.id, res.data.name, res.data.hardware, res.data.imagePath));
                })
                .catch(error => {
                    console.error("Erreur lors de la récupération de l'article " + id, error);
                    setTimeout(() => navigate("/"), 2000);
                });
        } else {
        }
    }, [id])

    function addToCart(): void {
        setCart((prevCart: CartArticle[]) => [...prevCart, { article: article!, quantity: 1 }]);
    }

    return (
        <>
            {article ?
                <div>
                    <h2>{article.name}</h2>
                    <p>Disponible sur {article.hardware}</p>
                    <img src={article.imagePath} alt={article.name} />
                    <button onClick={addToCart}>Ajouter au panier</button>
                </div>
                :
                <h2>Il semble que cet article n'existe pas</h2>
            }
        </>
    );
}

export default DetailledArticle;