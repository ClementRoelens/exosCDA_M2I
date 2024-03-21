import api from './api';
import { authHeader } from '../helpers/auth-header';

const getAllProducts = () => {
  return api.get('/products', { headers: authHeader() });
};

const getOneProduct = (id) => {
  return api.get('/products/'+id, {headers: authHeader()});
};

const addProduct = (name, price) => {
  return api.post('/products/admin/post', { name, price }, { headers: authHeader() });
};

const editProduct = (id,name,price) => {
  return api.put('/products', {id,name,price}, {headers:authHeader()});
};

const removeProduct = (id) => {
  return api.delete('/products/' + id, { headers: authHeader() });
};


export const productService = { getAllProducts, getOneProduct, addProduct, editProduct, removeProduct };
