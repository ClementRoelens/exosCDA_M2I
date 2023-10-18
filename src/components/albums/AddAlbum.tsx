import { useRef } from "react";
import FormComponent from "../shared/FormComponent";

function AddAlbum() {
    const titleRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const artistRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const releaseDateRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const scoreRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const coverURLRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    function addAlbum(){

    }

    return (
        <FormComponent submitFunction={addAlbum}>
            <div>
                <label htmlFor="title" className="form-label mt-3">Titre : </label>
                <input type="text" className="form-control" id="title" ref={titleRef} required />
                </div>
            <div>
                <label htmlFor="artist" className="form-label mt-3">Artiste : </label>
                <input type="text" className="form-control" id="artist" ref={artistRef} required />
                </div>
            <div>
                <label htmlFor="releaseDate" className="form-label mt-3">Date de sortie : </label>
                <input type="date" className="form-control" id="releaseDate" ref={releaseDateRef} required />
                </div>
            <div>
                <label htmlFor="score" className="form-label mt-3">Score : </label>
                <input type="range" className="form-range" min="1" max="5" step="1" defaultValue="3" id="score" ref={scoreRef} required />
                </div>
            <div>
                <label htmlFor="cover" className="form-label mt-3">Couverture : </label>
                <input type="file" className="form-control" id="cover" ref={coverURLRef} required />
                </div>
        </FormComponent>
    );
}

export default AddAlbum;