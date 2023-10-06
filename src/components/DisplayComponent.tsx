import { useContext } from "react";
import { TaskContext } from "../context/TaskContext";
import { Task } from "../models/Task";
import ButtonComponent from "./ButtonComponent";

function DisplayComponent() {
    const { tasks, setTasks } = useContext(TaskContext);

    function removeTask(id: number) {
        setTasks((prevTasks: Task[]) => prevTasks.filter((task: Task) => task.id !== id))
    }

    return (
        <ul className="col-6 list-group">
            {tasks.map((task: Task, index: number) =>
                <li key={index} className="list-group-item">
                    <h3>{task.name}</h3>
                    <p>à réaliser avant le <b>{task.deadline.toLocaleDateString()}</b></p>
                    <ButtonComponent task={task} />
                    <button className="btn" onClick={() => removeTask(task.id)}>X</button>
                </li>)}
        </ul>
    );
}

export default DisplayComponent;