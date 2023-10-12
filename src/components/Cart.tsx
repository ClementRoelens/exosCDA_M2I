import { useContext } from "react";
import CartContext from "../contexts/CartContext";
import { CartArticle } from "../models/Cart";

function Cart() {
    const { cart, setCart } = useContext(CartContext);

    function removeFromCart(id: number) {
        const newCart: CartArticle[] = [...cart];
        const index:number = newCart.findIndex((cartArticle:CartArticle) => cartArticle.article.id === id);
        (newCart[index].quantity > 1) ? newCart[index].quantity-- : newCart.splice(index,1);
        setCart(newCart);
    }

    return (<>
        {cart.length > 0 ?
            <ul>
                {cart.map((cartArticle: CartArticle) =>
                    <li key={cartArticle.article.id}>
                        <span>{cartArticle.article.name}</span>
                        <span>{cartArticle.quantity}</span>
                        <button onClick={() => removeFromCart(cartArticle.article.id)}>{cartArticle.quantity > 1 ? "-" : "X"}</button>
                    </li>
                )}
            </ul>
            :
            <h2>Votre panier est vide</h2>
        }
    </>);
}

export default Cart;