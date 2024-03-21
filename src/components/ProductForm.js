import React, { useEffect, useRef, useState } from 'react'
import { productService } from '../services/productService';
import { useNavigate, useParams } from 'react-router-dom';

function ProductForm() {
  const nameRef = useRef();
  const priceRef = useRef();
  const navigate = useNavigate();
  const { id } = useParams();
  const [product, setProduct] = useState();

  async function onValidateProduct(e) {
    e.preventDefault();
    const name = nameRef.current.value;
    const price = priceRef.current.value;

    if (name !== "" && price > 0) {
      if (!product) {
        await productService.addProduct(name, price);
      } else {
        await productService.editProduct(id, name, price);
      }
      navigate("/products");
    }
  };

  async function getProduct() {
    try {
      const response = await productService.getOneProduct(id);
      setProduct(response.data);
    } catch {
      navigate('/products');
    }
  }

  useEffect(() => {
    if (id) {
      if (isNaN(parseInt(id))){
        navigate('/products');
      }
      getProduct();
    }
  }, [id]);

  return (
    <div className="container">
      <div className="mx-auto w-50 mt-5">
        <h2 className="text-center my-3">Ajout de produit</h2>
        <form>
          <div className="my-3">
            <label htmlFor="name" className="form-label mb-2">Nom du produit</label>
            <input type="text" className="form-control" id="name" ref={nameRef} required defaultValue={product ? product.name : ''} />
          </div>
          <div className="my-3">
            <label htmlFor="price" className="form-label mb-2">Prix (en €)</label>
            <input type="number" className="form-control" min={0} step={0.01} id="price" ref={priceRef} required defaultValue={product ? product.price : ''} />
          </div>
          <button className="btn btn-success d-block mx-auto mt-4" onClick={onValidateProduct}>Valider</button>
        </form>
      </div>
    </div>
  )
}

export default ProductForm;
