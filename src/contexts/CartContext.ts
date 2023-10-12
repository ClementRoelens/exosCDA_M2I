import { createContext } from "react";
import { CartArticle } from "../models/Cart";

const CartContext = createContext<CartContextInterface>({
    cart: [],
    setCart: () => { }
});

interface CartContextInterface {
    cart: CartArticle[];
    setCart: React.Dispatch<React.SetStateAction<CartArticle[]>>;
}

export default CartContext;