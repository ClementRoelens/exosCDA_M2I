import { useEffect, useState } from 'react'
import './App.css'
import Navbar from './components/Navbar'
import { Link, Outlet } from 'react-router-dom'
import ArticleContext from './contexts/ArticleContext'
import { Article } from './models/Article'
import CartContext from './contexts/CartContext'
import { CartArticle } from './models/Cart'
import AdminAuthorizationContext from './contexts/AdminAuthorization'
import axios from 'axios'
import apiURL from './api-url'

function App() {
  const [articles, setArticles] = useState<Article[]>([]);
  const [cart, setCart] = useState<CartArticle[]>([]);
  const [adminAuthorization,setAdminAuthorization] = useState<boolean>(false);

  useEffect(() => {
    console.log("app.useEffect() lancé");
    // Récupération du panier
    const rawStoredCart = localStorage.getItem("cart");
    if (rawStoredCart) {
      const parsedCart = JSON.parse(rawStoredCart) as CartArticle[];
      console.log("Test : parsedCart[0].article",parsedCart[0].article);
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      setCart(parsedCart.map((storedArticle: any) => {
        return {
          article: new Article(storedArticle.article._id, storedArticle.article._name, storedArticle.article._hardware, storedArticle.article._imagePath),
          quantity: storedArticle.quantity
        };
      }));
    }
    // Récupération des articles
    axios.get<Article[]>(apiURL)
    .then(res => {
      const fetchedArticles = res.data.map((article:Article) => new Article(article.id,article.name,article.hardware,article.imagePath));
      setArticles(fetchedArticles);
    })
    .catch();

  }, [])

  return (
    <>
      <CartContext.Provider value={{ cart: cart, setCart: setCart }}>
      <AdminAuthorizationContext.Provider value={{ authorization: adminAuthorization, setAuthorization : setAdminAuthorization}} >
        <Navbar />
        <main className="mt-7">
          <ArticleContext.Provider value={{ articles: articles, setArticles: setArticles }}>
            <Outlet />
          </ArticleContext.Provider>
        </main>
            </AdminAuthorizationContext.Provider>
        <footer className="fixed bottom-0 flex justify-center w-full">
          <Link className="bg-slate-500 rounded-2xl text-md text-white py-2 px-12 flex justify-around" to="/admin">ADMINISTRATION</Link>
        </footer>
      </CartContext.Provider>
    </>
  )
}

export default App
