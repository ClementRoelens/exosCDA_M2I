import { useState } from 'react'
import { Task } from "./models/Task";
import { TaskContext } from './context/TaskContext';

import './App.css'
import FormComponent from './components/FormComponent';
import DisplayComponent from './components/DisplayComponent';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);
  return (
    <main className="container row">
      <TaskContext.Provider value={{ tasks: tasks, setTasks: setTasks }}>
        <FormComponent />
        <DisplayComponent />
      </TaskContext.Provider>
    </main>
  )
}

export default App
