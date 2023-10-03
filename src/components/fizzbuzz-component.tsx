import { useState } from "react";

function FizzBuzzComponent(props: FizzBuzzPropsInterface) {
    const initialState:FizzBuzzInterface = {
        counter:0,
        message:"0",
        incrementationDisabled:false,
        decrementationDisabled:true
    };
    const [fizzbuzzObject, setFizzbuzzObject] = useState<FizzBuzzInterface>(initialState);

    function incrementCounter() {
        changeCounter(1);
    }

    function decrementCounter() {
        changeCounter(-1);
    }

    function changeCounter(changeValue: number) {
        setFizzbuzzObject((prevState: FizzBuzzInterface) => {
            const newCounter = prevState.counter + changeValue;
            let newMessage;
            switch (true) {
                case ((newCounter % 3 === 0) && (newCounter % 5 === 0) && (newCounter !== 0)):
                    newMessage = "FizzBuzz";
                    break;
                case ((newCounter % 3 === 0) && (newCounter !== 0)):
                    newMessage = "Fizz";
                    break;
                case ((newCounter % 5 === 0) && (newCounter !== 0)):
                    newMessage = "Buzz";
                    break;
                default:
                    newMessage = newCounter.toString();
            }
            return {
                counter :newCounter,
                incrementationDisabled : (newCounter+1) > props.maxValue,
                decrementationDisabled : (newCounter-1) < 0,
                message:newMessage 
            };
        });
    }

    return (
        <>
            <button onClick={incrementCounter} disabled={fizzbuzzObject.incrementationDisabled}>+</button>
            <button onClick={decrementCounter} disabled={fizzbuzzObject.decrementationDisabled}>-</button>
            <p>{fizzbuzzObject.message}</p>
        </>
    );
}

interface FizzBuzzPropsInterface {
    maxValue: number;
}

interface FizzBuzzInterface {
    counter: number;
    message: string;
    incrementationDisabled: boolean;
    decrementationDisabled: boolean;
}

export default FizzBuzzComponent;