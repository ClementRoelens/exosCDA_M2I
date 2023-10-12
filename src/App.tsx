import { useEffect, useState } from 'react';
import './App.css';
import { Task } from './models/Task';
import { TaskContext } from './context/TaskContext';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import axios from 'axios';
import { url } from './url';
import { Outlet } from 'react-router-dom';
import Navbar from './components/NavBar';

function App() {
  const [tasks, setTasks] = useState<Task[]>([]);

  useEffect(() => {
    console.log("useEffect de App.tsx lancé");
    axios.get<Task[]>(url)
      .then(res => {
        const tasks: Task[] = res.data.map(data => new Task(data.id, data.name, data.description, data.deadline, data.completed));
        setTasks(tasks);
      })
      .catch(error => console.error("Erreur lors de la récupération des tâches dans TaskList", error));
  }, []);

  return (
    <TaskContext.Provider value={{ tasks: tasks, setTasks: setTasks }}>
      <Navbar />
      <main className="container bg-dark text-light p-3 mt-3 rounded">
        <Outlet />
      </main>
    </TaskContext.Provider>
  )
}

export default App
