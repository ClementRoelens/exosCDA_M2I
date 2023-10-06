import { createContext } from "react";
import { Task } from "../models/Task";

export const TaskContext = createContext<TaskContext>({
    tasks:[],
    setTasks : () => {}
});

interface TaskContext {
    tasks : Task[];
    setTasks : React.Dispatch<React.SetStateAction<Task[]>>;
}

