import axios from "axios";
import { FormEvent, MutableRefObject, useContext, useEffect, useRef } from "react";
import { Task } from "../models/Task";
import { url } from "../url";
import { TaskContext } from "../context/TaskContext";
import { useNavigate, useParams } from "react-router-dom";

function Form() {
    const { setTasks } = useContext(TaskContext);
    const {id} = useParams();

    const nameRef = useRef() as MutableRefObject<HTMLInputElement>;
    const dateRef = useRef() as MutableRefObject<HTMLInputElement>;
    const descriptionRef = useRef() as MutableRefObject<HTMLTextAreaElement>;
    let foundTask:Task | null;

    const navigate = useNavigate();

    useEffect(() => {
        // Si un id est passé, on pré-remplit
        if (id) {
            axios.get<Task>(url+id)
            .then(res => {
                if (res.data){
                    foundTask = res.data;
                    nameRef.current.value = foundTask.name;
                    descriptionRef.current.value = foundTask.description;
                    
                    // Je déteste travailler avec les dates
                    const dateBeforeT: string = foundTask.deadline.toString().split('T')[0];
                    const dateParts: string[] = dateBeforeT.split('-');
        
                    dateRef.current.value = `${dateParts[0]}-${dateParts[1]}-${dateParts[2]}`;

                } else {
                    navigate("/error?message=Aucun utilisateur trouvé")
                }
            })
            .catch(error => console.error("Erreur lors de la récupération dans FormComponent de la tâche " + id, error));
        }
    }, [id])

    function submitTask(e: FormEvent) {
        e.preventDefault();
        const body = {
            name: nameRef.current.value,
            description : descriptionRef.current.value,
            deadline: new Date(dateRef.current.value),
            completed: false
        };
        // Si une task est passée, c'est qu'on est en mode Modifier
        if (foundTask) {
            axios.put<Task>(`${url}/${id}`, body)
                .then(res => {
                    const newTask = new Task(res.data.id, res.data.name, res.data.description, res.data.deadline, res.data.completed);
                    setTasks((prevTasks: Task[]) => prevTasks.map((task: Task) => task.id === newTask.id ? newTask : task));
                    navigate("/");
                })
                .catch(error => console.error("Erreur lors de la modification de la tâche " + id, error));
        } else {
            axios.post<Task>(url, body)
                .then(res => {
                    const newTask = new Task(res.data.id, res.data.name, res.data.description, res.data.deadline, res.data.completed);
                    setTasks((prevTasks: Task[]) => [...prevTasks, newTask]);
                    navigate("/");
                })
                .catch(error => console.error("Erreur lors de la création d'une nouvelle tâche", error));
        }
    }

    return (
        <form action="#" className="p-2 w-50 mx-auto" onSubmit={submitTask}>
            <label htmlFor="name" className="form-label text-light mt-3">Nom de la tâche :</label>
            <input type="text" className="form-control" id="name" ref={nameRef} required />
            <label htmlFor="description" className="form-label text-light mt-3">Description : </label>
            <textarea className="form-control" id="description" ref={descriptionRef} required></textarea>
            <label htmlFor="deadline" className="form-label text-light mt-3">Deadline : </label>
            <input type="date" className="form-control" id="deadline" ref={dateRef} required />
            <button type="submit" className="btn btn-outline-light d-block mx-auto my-3">{id ? "Modifier" : "Créer tâche"}</button>
        </form>
    );
}



export default Form;