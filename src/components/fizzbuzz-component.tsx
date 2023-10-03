import { useState } from "react";

function FizzBuzzComponent(props: FizzBuzzInterface) {
    const [counter, setCounter] = useState<number>(0);
    const [message, setMessage] = useState<string>("0");
    // Les noms sont ignobles, l'usage est d'utiliser un set mais je me demande si je devrais pas en mettre un autre
    const [isIncrementationDisabled,setIsIncrementationDisabled] = useState<boolean>(false) ;
    const [isDesincrementationDisabled,setIsDesincrementationDisabled] = useState<boolean>(true) ;

    function incrementCounter() {
        changeCounter(1);
        setIsIncrementationDisabled(counter + 1 === props.maxValue);
        setIsDesincrementationDisabled(counter - 1 === 0);
    }

    function decrementCounter() {
        changeCounter(-1);
        setIsIncrementationDisabled(counter + 1 === props.maxValue);
        setIsDesincrementationDisabled(counter - 1 === 0);
    }

    function changeCounter(changeValue: number) {
        setCounter((prevState: number) => {
            prevState += changeValue;
            let message;
            switch (true) {
                case ((prevState % 3 === 0) && (prevState % 5 === 0) && (prevState !== 0)):
                    message = "FizzBuzz";
                    break;
                case ((prevState % 3 === 0) && (prevState !== 0)):
                    message = "Fizz";
                    break;
                case ((prevState % 5 === 0) && (prevState !== 0)):
                    message = "Buzz";
                    break;
                default:
                    message = prevState.toString();
            }
            setMessage(message);
            return prevState;
        });
    }

    return (
        <>
            <button onClick={incrementCounter} disabled={isIncrementationDisabled}>+</button>
            <button onClick={decrementCounter} disabled={isDesincrementationDisabled}>-</button>
            <p>{message}</p>
        </>
    );
}

interface FizzBuzzInterface {
    maxValue: number;
}

export default FizzBuzzComponent;