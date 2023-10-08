import { useContext } from "react";
import { TaskContext } from "../context/TaskContext";
import { Task } from "../models/Task";
import ButtonComponent from "./ButtonComponent";

function DisplayComponent() {
    const { tasks, setTasks } = useContext(TaskContext);

    function removeTask(id: number) {
        setTasks((prevTasks: Task[]) => prevTasks.filter((task: Task) => task.id !== id))
    }

    function getPositionning(index:number) : string {
        if (index <= 4){
            return `row-start-${index-1} row-end-${index} col-start-1 col-end-2`;
        } 
        const row:number = (index%5)+1;
        const col:number = Math.floor((index-5)/5)+2;
        return `row-start-${row} row-end-${row+1} col-start-${col} col-end-${col+1}`;
    }

    return (
        <ul>
            {tasks.map((task: Task, index: number) =>
                <li key={index} className={getPositionning(index)}>
                    <h3>{task.name}</h3>
                    <p>à réaliser avant le <b>{task.deadline.toLocaleDateString()}</b></p>
                    <ButtonComponent task={task} />
                    {task.completed && <button className="btn" onClick={() => removeTask(task.id)}>X</button>}
                </li>)}
        </ul>
    );
}

export default DisplayComponent;