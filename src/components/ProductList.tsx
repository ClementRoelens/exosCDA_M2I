import { Product } from "../models/Product";
import { useAppSelector } from "../redux-config/hooks";
import ProductItem from "./ProductItem";

function ProductList() {
    const products = useAppSelector(state => state.product.products);

    return (
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prix</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            {products.map((product: Product) =>
                <>
                    <tr key={product.id}><ProductItem product={product} /></tr>
                </>
            )}

            </tbody>
        </table>
    );
}

export default ProductList;