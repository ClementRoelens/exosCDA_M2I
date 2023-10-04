import { useState } from 'react'
import './App.css'
import LoginComponent from './components/LoginComponent'
import { LoginType } from './types/LoginType';

function App() {

  function displayLoginInputs(loginInputs:LoginType){
    console.log(loginInputs);
  }

  return (
    <>
     <LoginComponent transmitInputs={(loginInputs:LoginType) => displayLoginInputs(loginInputs)}/>
    </>
  )
}

export default App
