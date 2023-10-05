import { MutableRefObject, useEffect, useRef, useState } from "react";
import dringSound from "../assets/sounds/explosion.mp3";
import { Timer } from "../types/TimerInterface";

const TimerComponent = (props: TimerProps) => {
    const [seconds, setSeconds] = useState<number>(props.timer.seconds);
    const secondsRef = useRef() as MutableRefObject<HTMLSpanElement>;
    const [minutes, setMinutes] = useState<number>(props.timer.minutes);
    const minutesRef = useRef() as MutableRefObject<HTMLSpanElement>;
    let interval: number | undefined;

    useEffect(() => {
        interval = setInterval(() => {
            console.log("secondes : " + seconds);
            console.log("secondes avec innerHTML: " + secondsRef.current.innerHTML);
            // seconds et minutes ne s'actualisent jamais dans ce bloc, ils gardent toujours leur valeur initiale
            // et pareil pour minutes bien sûr
            if (+secondsRef.current.innerHTML === 0) {
                if (+minutesRef.current.innerHTML === 0) {
                    clearInterval(interval);
                    playSound();
                } else {
                    setMinutes((prevState: number) => prevState - 1);
                    setSeconds(59);
                }
            } else {
                setSeconds((prevState: number) => prevState - 1);
            }
        }, 1000);
        return () => {
            if (interval) {
                clearInterval(interval);
                interval = undefined;
            }
        };
    }, [props.timer])

    function playSound() {
        const newSound = new Audio(dringSound);
        newSound.play();
    }

    return (
        <div>
            <h3>{props.timer.title}</h3>
            <span className="minutes" ref={minutesRef}>{minutes}</span>:<span className="seconds" ref={secondsRef}>{seconds}</span>
        </div>
    );
}

interface TimerProps {
    timer: Timer;
}

export default TimerComponent;