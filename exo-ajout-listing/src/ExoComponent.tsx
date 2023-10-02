import { FormEvent, KeyboardEventHandler, useState } from "react";

function ExoComponent() {
    const [list, setList] = useState<string[]>([]);
    const [inputValue, setInputValue] = useState<string>("");

    function AddValue() {
        if (inputValue.trim() !== ""){
            setList([...list,inputValue]);
            setInputValue("");
        }
    }

    function ChangeValue(e:FormEvent){
        const input = e.target as HTMLInputElement;
        setInputValue(input.value);
    }

    function KeyAnalys(e:React.KeyboardEvent<HTMLInputElement>){
        if (e.key === "Enter"){
            AddValue();
        } else{
            setInputValue(e.currentTarget.value);
        }
    }

    return (
        <>
            <ul className="list-group mx-auto w-50 text-center">
                {list.map((element:string , id:number) => <li key={id} className="list-group-item">{element}</li>)}
            </ul>
            <input type="text" value={inputValue} onInput={ChangeValue} onKeyDown={KeyAnalys} className="d-block mx-auto" />
            <button className="btn btn-outline-dark mx-auto d-block" onClick={AddValue}>Ajouter</button>
        </>
    );
}

export default ExoComponent;