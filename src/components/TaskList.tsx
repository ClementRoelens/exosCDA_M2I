import axios from "axios";
import { useContext, useEffect, useState } from "react";
import {Task} from "../models/Task";
import { TaskContext } from "../context/TaskContext";
import TaskComponent from "./TaskComponent";
import { url } from "../url";

function TaskList(){
    const {tasks,setTasks} = useContext(TaskContext);

    useEffect(() => {
        console.log("useEffect de TaskList lancé");
        axios.get<Task[]>(url)
        .then(res => {
            const tasks:Task[] = res.data.map(data => new Task(data.id, data.name,data.deadline,data.completed));
            setTasks(tasks);
            console.log("Tasks bien récupérées");
        })
        .catch(error => console.error("Erreur lors de la récupération des tâches dans TaskList", error));
    },[]);

    return (
        <ul className="list-group">
            {tasks.map((task:Task) => 
            <li key={task.id} className="list-group-item bg-dark text-light w-50 my-2 mx-auto rounded border">
                <TaskComponent task={task}/>
            </li>)}
        </ul>
    );
}

export default TaskList;