import { useContext } from "react";
import {Task} from "../models/Task";
import { TaskContext } from "../context/TaskContext";
import TaskComponent from "./TaskComponent";

function TaskList(){
    const {tasks} = useContext(TaskContext);

    return (
        <ul className="list-group">
            {tasks.map((task:Task) => 
            <li key={task.id} className="component list-group-item bg-dark text-light w-75 my-2 p-3 mx-auto rounded border">
                <TaskComponent task={task}/>
            </li>)}
        </ul>
    );
}

export default TaskList;