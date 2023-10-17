import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './components/Navbar';
import { Outlet } from 'react-router-dom';
import { useEffect } from 'react';
import { useAppDispatch } from './hooks';
import { setUser } from './components/authSlice';
import { readRecipes } from './services/RecipeService';
import { setRecipes } from './components/recipesSlice';

function App() {
  const dispatch = useAppDispatch();

  useEffect(() => {
    const user = localStorage.getItem("user");

    if (user) {
      dispatch(setUser(JSON.parse(user)));
    }

    readRecipes()
      .then(recipes => dispatch(setRecipes(recipes)))
      .catch(error => console.log(error));
  }, []);

  return (
    <>
      <Navbar />
      <main className="container bg-dark rounded mt-3 p-3 text-light">
        <Outlet />
      </main>
    </>
  )
}

export default App
