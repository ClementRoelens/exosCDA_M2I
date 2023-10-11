import { createContext } from "react";
import { Task } from "../models/Task";

export const TaskContext = createContext<TaskContextInterface>({
    tasks : [],
    setTasks : () => {}
});

interface TaskContextInterface {
    tasks : Task[];
    setTasks : React.Dispatch<React.SetStateAction<Task[]>>;
}