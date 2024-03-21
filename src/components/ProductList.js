import React, { useEffect, useState } from 'react';
import { productService } from '../services/productService';

function ProductList() {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    productService.getAllProducts()
      .then(response => {
        console.log(response)
        setProducts(response.data);
      })
      .catch(error => {
        setError('Erreur lors de la récupération des produits.');
      });
  }, []);

  async function onDeletingProduct(id) {
    await productService.removeProduct(id);
    const response = await productService.getAllProducts();
    setProducts(response.data);
  }

  return (
    <div className="container mt-5">
      <h2>Product List</h2>
      {error && <div className="alert alert-danger" role="alert">
        {error}
      </div>}
      <table className="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Edit</th>
            <th scope="col">Remove</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product, index) => (
            <tr key={product.id}>
              <th scope="row" style={{ verticalAlign: 'middle' }}>{index + 1}</th>
              <td style={{ verticalAlign: 'middle' }}>{product.name}</td>
              <td style={{ verticalAlign: 'middle' }}>{product.price}</td>
              <td style={{ verticalAlign: 'middle' }}>
                <a className="btn btn-warning" href={"/edit-product/"+product.id}><i className="bi bi-pencil-square"></i></a>
              </td>
              <td style={{ verticalAlign: 'middle' }}>
                <button className="btn btn-danger" onClick={() => onDeletingProduct(product.id)}><i className="bi bi-trash"></i></button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ProductList;
