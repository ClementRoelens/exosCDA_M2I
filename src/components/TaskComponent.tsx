import { useContext } from "react";
import { Task } from "../models/Task";
import { TaskContext } from "../context/TaskContext";
import axios from "axios";
import { url } from "../url";
import { useNavigate } from "react-router-dom";

function TaskComponent(props: TaskProps) {
    const { setTasks } = useContext(TaskContext);
    const navigate = useNavigate();

    function completeTask() {
        console.log(`TaskComponent.completeTask() lancé sur ${props.task.id}`);
        axios.patch<Task>(`${url + props.task.id}`, { completed: true })
            .then(res => {
                setTasks((prevTasks: Task[]) => prevTasks.map((task: Task) => task.id === props.task.id ? res.data : task));
            })
            .catch(error => console.error("Erreur lors de TaskComponent.completeTask()", error));
    }

   

    return (<>
        <div className="d-flex justify-content-around" onClick={() => {navigate(`/${props.task.id}`)}}>
            <h5 className="text-center">{props.task.name}</h5>
            <p>{props.task.completed ? "Effectuée" : "Deadline : " + new Date(props.task.deadline).toLocaleDateString()}</p>
        </div>
        <div className="d-flex justify-content-around mt-3 w-75 mx-auto">
            {!props.task.completed && <button className="btn btn-success" onClick={completeTask}>Finir tâche</button>}

        </div>
    </>);
}

interface TaskProps {
    task: Task;
}

export default TaskComponent;