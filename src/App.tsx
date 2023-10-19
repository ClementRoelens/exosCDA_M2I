import './App.css'
import { Outlet } from 'react-router-dom'
import Navbar from './components/shared/Navbar'
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/js/bootstrap.min.js";
import { useEffect } from 'react';
import { User } from './models/User';
import { useAppDispatch } from './config/hooks';
import { authentificateUser } from './components/auth/authSlice';
import { readAllAlbums } from './components/albums/albumSlice';

function App() {
  const dispatch = useAppDispatch()

  useEffect(() => {
    dispatch(readAllAlbums());
    const storedUser = localStorage.getItem("user");
    if (storedUser){
      const parsedUser = JSON.parse(storedUser) as User;
      dispatch(authentificateUser(parsedUser));
    }
  },[])

  return (
    <>
      <Navbar />
      <main className="container bg-dark text-light rounded mt-2 p-4">
        <Outlet />
      </main>
    </>
  )
}

export default App
