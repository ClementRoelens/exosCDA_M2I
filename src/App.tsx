import 'bootstrap/dist/css/bootstrap.min.css';
import NavBarComponent from './components/NavBarComponent';
import { Outlet } from 'react-router-dom';

function App() {

  return (
    <>
      <NavBarComponent />
      <div className='bg-dark rounded mt-3 mx-auto p-4 w-50 text-light'>
        <Outlet />
      </div>
    </>
  )
}

export default App
