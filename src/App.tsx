import './App.css'
import AddProduct from './components/AddProducts'
import ProductList from './components/ProductList'
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {

  return (
    <main className="container">
      <h1 className='text-center mt-5'>Application de gestion de produits</h1>
      <ProductList />
      <AddProduct />
    </main>
  )
}

export default App
