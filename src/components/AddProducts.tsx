import { FormEvent, useRef } from "react";
import { useAppDispatch } from "../redux-config/hooks";
import { addProduct } from "./ProductsSlice";
import { Product } from "../models/Product";

function AddProduct() {
    const nameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const priceRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const dispatch = useAppDispatch();

    function addProductForm(e: FormEvent) {
        e.preventDefault();
        const newProduct = new Product(nameRef.current.value,+priceRef.current.value);
        dispatch(addProduct(newProduct));
        nameRef.current.value = "";
        priceRef.current.value = "";
    }

    return (
        <form action="#" onSubmit={addProductForm}>
            <h2 className="text-center mt-5">Ajouter un produit</h2>
            <label htmlFor="name" className="form-label mt-3">Nom du produit</label>
            <input type="text" id="name" className="form-control" ref={nameRef} required />
            <label htmlFor="price" className="form-label mt-3">Prix du produit</label>
            <input type="number" id="price" className="form-control" ref={priceRef} required />
            <button className="btn btn-success mt-5 d-block mx-auto w-25" type="submit">Valider</button>
        </form>
    );
}

export default AddProduct;