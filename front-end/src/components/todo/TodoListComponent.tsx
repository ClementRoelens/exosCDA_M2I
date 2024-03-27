import { useAppSelector } from "../../config/hook"
import { Todo } from "../../models/Todo";
import TodoThumbComponent from "./TodoThumbComponent";

function TodoListComponent() {
  const todos = useAppSelector(state => state.todo.todos);

  return (
    <ul className="list-group">
      {todos.map((todo: Todo, index: number) =>
        <li className="list-group-item"><TodoThumbComponent todo={todo} key={index} /></li>)}
    </ul>
  )
}

export default TodoListComponent