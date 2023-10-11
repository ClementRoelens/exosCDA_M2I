import { useContext, useEffect } from "react";
import { Task } from "../models/Task";
import { TaskContext } from "../context/TaskContext";
import axios from "axios";
import { url } from "../url";
import Form from "./Form";

function TaskComponent(props: TaskProps) {
    const { setTasks } = useContext(TaskContext);

    useEffect(() => {
        console.log("UseEffect de TaskComponent lancé");
        console.log(props.task);
    }, [props.task])

    function completeTask() {
        console.log(`TaskComponent.completeTask() lancé sur ${props.task.id}`);
        axios.patch<Task>(`${url + props.task.id}`, { completed: true })
            .then(res => {
                setTasks((prevTasks: Task[]) => prevTasks.map((task: Task) => task.id === props.task.id ? res.data : task));
            })
            .catch(error => console.error("Erreur lors de TaskComponent.completeTask()", error));
    }

    function deleteTask() {
        axios.delete(url + props.task.id)
            .then(res => {
                setTasks((prevTasks: Task[]) => prevTasks.filter((task: Task) => task.id !== props.task.id));
            })
            .catch(error => console.error("Une erreur est survenue lors de la suppression de " + props.task.id, error));
    }

    return (<>
        <div className="d-flex justify-content-around">
            <h5 className="text-center">{props.task.name}</h5>
            <p>{props.task.completed ? "Effectuée" : "Deadline : " + new Date(props.task.deadline).toLocaleDateString()}</p>
        </div>
        <div className="d-flex justify-content-around mt-3 w-75 mx-auto">
            {!props.task.completed && <button className="btn btn-success" onClick={completeTask}>Finir tâche</button>}
            <button className="btn btn-warning" data-bs-toggle="modal" data-bs-target={`#editModal${props.task.id}`}>Modifier</button>
            <button className="btn btn-danger" onClick={deleteTask}>Supprimer</button>
        </div>

        <div className="modal fade" id={`editModal${props.task.id}`}>
            <div className="modal-dialog">
                <div className="modal-content bg-dark text-light">
                    <div className="modal-header">
                        <h5 className="modal-title">Modifier tâche</h5>
                        <button type="button" className="btn-close bg-light" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                        <Form task={props.task} />
                    </div>
                </div>
            </div>
        </div>
    </>);
}

interface TaskProps {
    task: Task;
}

export default TaskComponent;