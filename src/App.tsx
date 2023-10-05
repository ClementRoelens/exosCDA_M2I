import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'
import FormComponent from './components/FormComponent';
import CardsComponent from './components/CardsComponent';
import { useState } from 'react';
import { Film } from './models/Film';
import { FilmContext } from './contexts/FilmContext';

function App() {
  const [films, setFilms] = useState<Film[]>([]);

  return (
    <FilmContext.Provider value={{ filmsState: films, setFilms: setFilms }}>
      <main className="container row">
        <FormComponent />
        <CardsComponent />
      </main>
    </FilmContext.Provider>
  )
}

export default App;
