import { useState } from 'react';
import './App.css';
import { Task } from './models/Task';
import { TaskContext } from './context/TaskContext';
import TaskList from './components/TaskList';
import Form from './components/Form';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);

  return (
    <TaskContext.Provider value={{ tasks: tasks, setTasks: setTasks }}>
      <main className="container bg-dark pt-3 mt-3 rounded">
        <TaskList />
        <Form />
      </main>
    </TaskContext.Provider>
  )
}

export default App
