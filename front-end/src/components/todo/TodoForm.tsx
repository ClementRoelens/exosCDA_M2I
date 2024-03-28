import { useRef, useState } from "react"
import FormComponent from "../shared/FormComponent"
import { useAppDispatch, useAppSelector } from "../../config/hook";
import { createTodo } from "./todoSlice";
import { Alert } from "antd";
import { useNavigate } from "react-router-dom";

function TodoForm() {
  const dispatch = useAppDispatch();
  const user = useAppSelector(state => state.auth.user);
  const navigate = useNavigate();
  const titleRef = useRef() as React.MutableRefObject<HTMLInputElement>;
  const descriptionRef = useRef() as React.MutableRefObject<HTMLTextAreaElement>;
  const [isRejected, setIsRejected] = useState(false);


  async function postHandle() {
    const title = titleRef.current.value;
    const description = descriptionRef.current.value;

    if (title !== "" && description !== "") {
      const res = await dispatch(createTodo({ title, description, userEmail : user!.email })) as any;
      if (res.error) {
        setIsRejected(true);
      } else {
        navigate("/todos");
      }
    }
  }

  return (
    <>
      <h1 className="text-center">Création de tâche</h1>
      <FormComponent submitFunction={postHandle}>
        <div className="my-2">
          <label htmlFor="title" className="form-label">Titre : </label>
          <input type="text" className="form-control" id="title" required ref={titleRef} />
        </div>
        <div className="my-2">
          <label htmlFor="description" className="form-label">Description : </label>
          <textarea className="form-control" id="description" required ref={descriptionRef} />
        </div>
      </FormComponent>
      {isRejected && <Alert className="mt-4" message="Erreur" description="La création de la tâche a échoué" type="error" />}
    </>
  )
}

export default TodoForm