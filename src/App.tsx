import { useState } from 'react'
import DisplayFullnameComponent from './components/DisplayFullnameComponent'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <DisplayFullnameComponent/>
    </>
  )
}

export default App
