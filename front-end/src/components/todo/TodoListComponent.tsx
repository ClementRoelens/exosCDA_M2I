import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../../config/hook"
import { Todo } from "../../models/Todo";
import TodoThumbComponent from "./TodoThumbComponent";
import { getAllTodos, getTheirTodos } from "./todoSlice";

function TodoListComponent() {
  const todos = useAppSelector(state => state.todo.todos);
  const user = useAppSelector(state => state.auth.user);
  const dispatch = useAppDispatch();

  useEffect(() => { 
    if (user && user.role === "ROLE_ADMIN"){
      dispatch(getTheirTodos(user.id!));
    } else {
      dispatch(getAllTodos());
    }
   },[user]);

  return (
    <>
    <h2 className="text-center">Voici les tâches sélectionnées :</h2>
      <ul className="list-group">
        {todos.map((todo: Todo, index: number) =>
          <li className="list-group-item" key={index}><TodoThumbComponent todo={todo}/></li>)}
      </ul>
    </>
  )
}

export default TodoListComponent