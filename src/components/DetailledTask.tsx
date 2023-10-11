import axios from "axios";
import { Task } from "../models/Task";
import { url } from "../url";
import { Link, useNavigate, useParams } from "react-router-dom";
import { useEffect, useState } from "react";

function DetailledTask() {
    const [task, setTask] = useState<Task | null>(null);
    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        if (!id || isNaN(+id)) {
            navigate("/");
        } else {
            axios.get<Task>(url + id)
                .then(res => {
                    setTask(new Task(res.data.id, res.data.name, res.data.description, res.data.deadline, res.data.completed));
                })
                .catch(() => navigate("/error?message=Aucun utilisateur trouvé"));
        }
    }, [id]);

    function deleteTask() {
        axios.delete(url + id)
            .then(() => {
                navigate('/');
            })
            .catch(error => console.error("Une erreur est survenue lors de la suppression de " + id, error));
    }

    if (task) {
        return (
            <div className="text-light p-2">
                <div className="row">
                    <h1 className="col-10">{task.name}</h1>
                    <span className="col-1 align-self-center">{task.completed ? "Effectuée" : new Date(task.deadline).toLocaleDateString()}</span>
                </div>
                <p className="mt-3 w-75 mx-auto px-1">{task.description}</p>
                <div className="d-flex justify-content-around mt-3 mx-auto w-75">
                    <Link className="btn btn-warning" to="/edit" >Modifier</Link>
                    <button className="btn btn-danger" onClick={deleteTask}>Supprimer</button>
                </div>
            </div>);
    } else {
        return (<></>)
    }
}

export default DetailledTask;