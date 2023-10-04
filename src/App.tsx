import './App.css'
import TableComponent from './components/TableComponent'
import { FormComponent, Person } from './components/FormComponent'
import { useEffect, useState } from 'react'

function App() {
  const [personArray, setPersonArray] = useState<Person[]>([])

  return (
    <>
      <FormComponent transmitPerson={(newPerson:Person) => setPersonArray(prevState => [...prevState,newPerson])}/>
      <TableComponent transmittedPersonArray={personArray}/>
    </>
  )
}

export default App
