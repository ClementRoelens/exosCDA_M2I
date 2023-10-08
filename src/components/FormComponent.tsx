import { FormEvent, MutableRefObject, useContext, useRef, useState } from "react";
import { TaskContext } from "../context/TaskContext";
import { Task } from "../models/Task";

function FormComponent() {
    const nameRef = useRef() as MutableRefObject<HTMLInputElement>;
    const dateRef = useRef() as MutableRefObject<HTMLInputElement>;

    const {setTasks} = useContext(TaskContext);

    const [ isFormValid, setIsFormValid ] = useState<boolean>(false);

    function submitHandler(e:FormEvent){
        e.preventDefault();
        const task:Task = new Task(nameRef.current.value,new Date(dateRef.current.value));
        setTasks((prevTasks:Task[]) => [...prevTasks,task ]);
    }

    function validate(){
        if (nameRef.current.value !== "" && dateRef.current.value !== ""){
            setIsFormValid(true);
        } else {
            setIsFormValid(false);
        }
    }

    return (
        <form action="#" onSubmit={submitHandler} className="">
            <div>
                <label htmlFor="name" className="">Nom de la tâche : </label>
                <input type="text" id="name" className="" ref={nameRef} onInput={validate}/>
            </div>
            <div>
                <label htmlFor="deadline" className="">Deadline</label>
                <input type="date" id="deadline" className="" ref={dateRef} onInput={validate}/>
            </div>
            {isFormValid && <button type="submit" className="">Ajouter tâche</button>}
            
        </form>
    );
}

export default FormComponent;