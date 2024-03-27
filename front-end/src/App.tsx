import { Outlet } from 'react-router-dom'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/js/bootstrap.min.js";
import HeaderComponent from './components/shared/HeaderComponent'

function App() {

  return (
    <>
      <HeaderComponent />
      <main className="container pt-3">
        <Outlet />
      </main>
    </>
  )
}

export default App
