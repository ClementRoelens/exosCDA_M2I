import { useRef, useState } from "react";
import { Product } from "../models/Product";
import { useAppDispatch } from "../redux-config/hooks";
import { deleteProduct, editProduct } from "./ProductsSlice";

function ProductItem(props: ProductItemInterface) {
    const [editMode, setEditMode] = useState<boolean>(false);

    const nameRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const priceRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const dispatch = useAppDispatch();

    function editProductForm() {
        const editedProduct = new Product(nameRef.current.value, +priceRef.current.value, props.product.id);
        dispatch(editProduct({ id: props.product.id, product: editedProduct }));
        setEditMode(false);
    }

    return (<>
        {editMode ?
            <>
                <td><input type="text" className="form-control w-50" defaultValue={props.product.name} ref={nameRef} required /></td>
                <td><input type="number" className="form-control w-50" defaultValue={props.product.price} ref={priceRef} required /></td>
            </>
            :
            <>
                <td>{props.product.name}</td>
                <td>{props.product.price}</td>

            </>
        }
        <td>
            {editMode ?
                <button className="btn btn-success me-1" onClick={editProductForm}>Valider</button>
                :
                <button className="btn btn-success me-1" onClick={() => setEditMode(true)}>Modifier le produit</button>
            }
            <button className="btn btn-danger ms-1" onClick={() => dispatch(deleteProduct(props.product.id))}>Supprimer</button>
        </td>
    </>);
}

interface ProductItemInterface {
    product: Product;
}

export default ProductItem;