import { MutableRefObject, useRef, useState } from 'react';
import './App.css'
import TimerComponent from './components/TimerComponent'
import { Timer } from './types/TimerInterface';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  const [timers, setTimers] = useState<Timer[]>([]);
  const minutesRef = useRef() as MutableRefObject<HTMLInputElement>;
  const secondsRef = useRef() as MutableRefObject<HTMLInputElement>;
  const titleRef = useRef() as MutableRefObject<HTMLInputElement>;

  function newTimer() {
    const minutesValue = minutesRef.current.value;
    const secondsValue = secondsRef.current.value;
    const titleValue = titleRef.current.value;
    if (timers.length < 5 && minutesValue && secondsValue && titleValue) {
      setTimers((prevState: Timer[]) => [...prevState, {
        title: titleValue,
        minutes: +minutesValue,
        seconds: +secondsValue
      }]);
    }
  }

  return (
    <><main className="container row">
      <div className="setting-area col-6">
        <input type="text" id="name" placeholder="Nom du timer" ref={titleRef} className="form-control" />
        <div className="input-group">
          <input type="number" id="minutes" placeholder="Minutes" ref={minutesRef} className="form-control" />
          <span className='input-group-text'>:</span>
          <input type="number" id="seconds" placeholder="Secondes" ref={secondsRef} className="form-control" />
        </div>
        <button className="btn btn-outline-dark" onClick={newTimer}>Lancer</button>
      </div>
      <div className="timers-area col-6">
      {timers.length === 0 ? <h2 className="">Pas encore de timer</h2> :
        <ul className='list-group'>
          {timers.map((timer: Timer, index: number) => <li key={index} className='list-group-item'><TimerComponent timer={timer} /></li>)}
        </ul>
      }
      </div>
    </main>
    </>
  )
}



export default App;
