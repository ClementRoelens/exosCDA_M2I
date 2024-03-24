import { useState } from "react";
import { Todo } from "../../models/todo"
import TodoIsCompletedComponent from "./TodoIsCompletedComponent"
import { Alert } from "antd";

function TodoThumbComponent(props: ThumbProps) {
  const [isRejected, setIsRejected] = useState(false);

  return (
    <>
      <div>
        <p>{props.todo.title}</p>
        <TodoIsCompletedComponent todo={props.todo} setter={setIsRejected} />
      </div>
      {isRejected && <Alert message="Erreur" description="La tâche n'a pas pu être complétée" type="error" />}
    </>
  )
}

interface ThumbProps {
  todo:Todo;
}

export default TodoThumbComponent