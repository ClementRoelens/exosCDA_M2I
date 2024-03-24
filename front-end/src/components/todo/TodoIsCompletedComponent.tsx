import { useAppDispatch } from "../../config/hook";
import { Todo } from "../../models/todo";
import { updateTodo } from "./todoSlice";

function TodoIsCompletedComponent(props:IsCompletedProps) {
const dispatch = useAppDispatch();

    async function completeTodo() {
        const tempTodo: Todo = props.todo;
        tempTodo.isCompleted = true;
        const res = await dispatch(updateTodo(tempTodo)) as any;
        if (res.error) {
            props.setter(true);
        } else {
            props.setter(false);
          props.todo.isCompleted = true;
        }
      }
   
          if (props.todo.isCompleted){
            return <p className="btn btn-success">Tâche finie</p>;
          }
          
          return  <button className="btn btn-danger" onClick={completeTodo}>Finir tâche</button>;
}

interface IsCompletedProps {
    todo : Todo;
    setter : React.Dispatch<React.SetStateAction<boolean>>;
}

export default TodoIsCompletedComponent