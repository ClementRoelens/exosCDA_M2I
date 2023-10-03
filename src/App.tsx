import FizzBuzzComponent from './components/fizzbuzz-component'
import './App.css'

function App() {
  const maxValue:number = 35;
  return (
    <>
      <FizzBuzzComponent maxValue={maxValue}/>
    </>
  )
}

export default App
