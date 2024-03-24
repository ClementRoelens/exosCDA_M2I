import { useEffect, useState } from "react";
import { useAppDispatch, useAppSelector } from "../../config/hook";
import { getOneTodo } from "./todoSlice";
import { Alert, Spin } from "antd";
import { useNavigate, useParams } from "react-router-dom";
import TodoIsCompletedComponent from "./TodoIsCompletedComponent";

function TodoDisplayComponent() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const [isRejected, setIsRejected] = useState(false);
  const todo = useAppSelector(state => state.todo.selectedTodo);
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      getTodo();
    }
  }, [id]);

  async function getTodo() {
    const res = await dispatch(getOneTodo(id!)) as any;
    if (res.error) {
      navigate("/todos");
    }
  }

  return (
    <div className="mx-auto text-center">
      {todo ? <>
        <h1>{todo.title}</h1>
        <p>{todo.description}</p>
        <TodoIsCompletedComponent todo={todo} setter={setIsRejected} />
        {isRejected && <Alert message="Erreur" description="La tâche n'a pas pu être complétée" type="error" />}
      </> :
        <Spin size="large" />
      }
    </div>
  )
}

export default TodoDisplayComponent;