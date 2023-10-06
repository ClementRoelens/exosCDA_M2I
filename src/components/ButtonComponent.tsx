import { useContext, useState } from "react";
import { Task } from "../models/Task";
import { TaskContext } from "../context/TaskContext";


function ButtonComponent(props: ButtonProps) {
    const { setTasks } = useContext(TaskContext);
    const [completed, setCompleted] = useState<boolean>(props.task.completed);

    function endTask() {
        setTasks((prevTasks: Task[]) => [...prevTasks.map((task: Task) => {
            if (task.id === props.task.id) {
                task.completeTask();
                return task;
            }
            return task;
        })]);
        setCompleted(true);
    }

    return (
        <button className="btn btn-outline-dark" disabled={completed} onClick={endTask}>
            {completed ? "Finir tâche" : "Tâche finie"}
        </button>);
}

interface ButtonProps {
    task: Task;
}

export default ButtonComponent;