import { useRef, useState } from "react"
import FormComponent from "../shared/FormComponent"
import { useAppDispatch } from "../../config/hook";
import { createTodo } from "./todoSlice";
import { Alert } from "antd";
import { useNavigate } from "react-router-dom";

function TodoForm() {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();
  const titleRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const descriptionRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const [isRejected, setIsRejected] = useState(false);

  async function postHandle() {
    const title = titleRef.current.value;
    const description = descriptionRef.current.value;

    if (title !== "" && description !== "") {
      const res = await dispatch(createTodo({ title, description })) as any;
      if (res.error) {
        setIsRejected(true);
      } else {
        navigate("/todos");
      }
    }
  }

  return (
    <>
      <h1>Création de tâche</h1>
      <FormComponent submitFunction={postHandle}>
        <div className="my-2">
          <label htmlFor="title" className="form-label"></label>
          <input type="text" className="form-control" id="title" required ref={titleRef} />
        </div>
        <div className="my-2">
          <label htmlFor="descriptio" className="form-label"></label>
          <input type="text" className="form-control" id="descriptio" required ref={descriptionRef} />
        </div>
      </FormComponent>
      {isRejected && <Alert message="Erreur" description="La création de la tâche a échoué" type="error" />}
    </>
  )
}

export default TodoForm