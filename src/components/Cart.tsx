import { useContext } from "react";
import CartContext from "../contexts/CartContext";
import { CartArticle } from "../models/Cart";

function Cart() {
    const { cart, setCart } = useContext(CartContext);

    function removeFromCart(id: number) {
        const newCart: CartArticle[] = [...cart];
        const index: number = newCart.findIndex((cartArticle: CartArticle) => cartArticle.article.id === id);
        (newCart[index].quantity > 1) ? newCart[index].quantity-- : newCart.splice(index, 1);
        setCart(newCart);
    }

    function getTotal() : number {
        let total:number = 0;
        cart.forEach((cartArticle:CartArticle) => {
            console.log("Article : " + cartArticle.article.name, "Prix : " + cartArticle.article.price, "Quantité : " + cartArticle.quantity);
            total += cartArticle.article.price*cartArticle.quantity;
        })

        return total;
    }

    return (<>
        {cart.length > 0 ?
            <>
                <table className="table-auto border border-black mx-auto w-3/6">
                    <thead className="border-b-2">
                        <tr>
                            <th className="py-2 text-center">Nom</th>
                            <th className="py-2 text-center">Prix</th>
                            <th className="py-2 text-center">Quantité</th>
                            <th className="py-2 text-center">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {cart.map((cartArticle: CartArticle) =>
                            <tr key={cartArticle.article.id}>
                                <td className="py-1 text-center">{cartArticle.article.name}</td>
                                <td className="py-1 text-center">{cartArticle.article.price}€</td>
                                <td className="py-1 text-center">{cartArticle.quantity}</td>
                                <td className="py-1 text-center">
                                    <button className="rounded-3xl bg-slate-300 py-1 px-3" onClick={() => removeFromCart(cartArticle.article.id)}>{cartArticle.quantity > 1 ? "-" : "X"}</button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
                <h5 className="text-center mx-auto mt-10 text-xl">Total : {getTotal()}€</h5>
            </>
            :
            <h2 className="text-center mx-auto mt-12 text-xl ">Votre panier est vide</h2>
        }
    </>);
}

export default Cart;