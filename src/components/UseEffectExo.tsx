import { FormEvent, useEffect, useState } from "react";

function UseEffectExo(props: UseEffectExoProps) {
    const [nbA, setNbA] = useState<number>(0);
    const [nbB, setNbB] = useState<number>(0);
    const [isMultiplicationSuccess,setIsMultiplicationSuccess] = useState<boolean>(false);

    function nbAChanged(e: FormEvent) {
        const input = e.target as HTMLInputElement;
        setNbA(+input.value);
    }

    function nbBChanged(e: FormEvent) {
        const input = e.target as HTMLInputElement;
        setNbB(+input.value);
    }

    useEffect(() => setIsMultiplicationSuccess(nbA * nbB === props.targetNumber), [nbA, nbB]);

    return (
        <>
            <input type="number" onInput={nbAChanged} />
            <input type="number" onInput={nbBChanged} />
            <p>{isMultiplicationSuccess ? `${nbA} x ${nbB} fait bien ${props.targetNumber}` : `${nbA} x ${nbB} ne fait pas ${props.targetNumber}`}</p>
        </>
    );
}

interface UseEffectExoProps {
    targetNumber: number;
}

export default UseEffectExo;