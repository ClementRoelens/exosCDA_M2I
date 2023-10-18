import './App.css'
import { Outlet } from 'react-router-dom'
import Navbar from './components/shared/Navbar'
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap-icons/font/bootstrap-icons.css";

function App() {

  return (
    <>
      <Navbar />
      <main className="container bg-dark text-light rounded mt-2">
        <Outlet />
      </main>
    </>
  )
}

export default App
