import { FormEvent, MutableRefObject, useContext, useRef } from "react";
import { TaskContext } from "../context/TaskContext";
import { Task } from "../models/Task";

function FormComponent() {
    const nameRef = useRef() as MutableRefObject<HTMLInputElement>;
    const dateRef = useRef() as MutableRefObject<HTMLInputElement>;

    const {setTasks} = useContext(TaskContext);

    function submitHandler(e:FormEvent){
        e.preventDefault();
        const task:Task = new Task(nameRef.current.value,new Date(dateRef.current.value));
        setTasks((prevTasks:Task[]) => [...prevTasks,task ])
        console.log("task créée : ");
        console.log(task);
    }

    return (
        <form action="#" onSubmit={submitHandler} className="col-6">
            <div>
                <label htmlFor="name" className="form-label">Nom de la tâche : </label>
                <input type="text" id="name" className="form-control" ref={nameRef} />
            </div>
            <div>
                <label htmlFor="deadline" className="form-label">Deadline</label>
                <input type="date" id="deadline" className="form-control" ref={dateRef} />
            </div>
            <button type="submit" className="btn btn-outline-dark">Ajouter tâche</button>
        </form>
    );
}

export default FormComponent;