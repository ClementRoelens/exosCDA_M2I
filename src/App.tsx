import './App.css'
import TableComponent from './components/TableComponent'
import { FormComponent, Person } from './components/FormComponent'
import { useEffect, useState } from 'react'
import FormStyles from "./components/FormComponent.module.css";

function App() {
  const [personArray, setPersonArray] = useState<Person[]>([])
  const [firstname,setFirstname] = useState<string>("");
  const [lastname,setLastname] = useState<string>("");
  const [age,setAge] = useState<number>(0);

  function formSubmit(person:Person){
    setPersonArray((prevState:Person[]) => [...prevState,person]);
    setFirstname("");
    setLastname("");
    setAge(0);
  }

  return (
    <>
      <FormComponent person={{firstname:firstname,lastname:lastname,age:age}} submitPerson={(person:Person) => formSubmit(person)}>
            <label htmlFor="firstname" className={FormStyles.label}>Prénom :</label>
            <input type="text" id="firstname"onInput={e => setFirstname((e.target as HTMLInputElement).value)} value={firstname}/>
            <label htmlFor="lastname" className={FormStyles.label}>Nom : </label>
            <input type="text" id="lastname" onInput={e => setLastname((e.target as HTMLInputElement).value)} value={lastname}/>
            <label htmlFor="age" className={FormStyles.label}>Âge : </label>
            <input type="number" onInput={e => setAge(+(e.target as HTMLInputElement).value)} value={age}/>
            <button type="submit" className={FormStyles.submit}>Envoyer</button>
      </FormComponent>
      <TableComponent transmittedPersonArray={personArray}/>
    </>
  )
}

export default App
