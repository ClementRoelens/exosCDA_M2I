// import React from 'react';
// import logo from './logo.svg';
import './App.css';
import AlertComponent from './components/AlertComponent';

function App() {
  return (
    <div className="App">
      <AlertComponent text="Je ne sais pas quoi en penser" color="info" icon="info"/>
      <AlertComponent text="Quelque chose s'est mal passé" color="danger" icon="danger"/>
      <AlertComponent text="Tout a réussi" color="success" icon="success"/>
      <AlertComponent/>
    </div>
  );
}

export default App;
