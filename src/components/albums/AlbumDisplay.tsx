import { useNavigate } from "react-router-dom";
import { Album } from "../../models/Album";

function AlbumDisplay(props: AlbumProps) {
    const navigate = useNavigate();

    return (
            <div className="card bg-dark text-light rounded col-3 border mx-2 my-3 p-0 clickable" onClick={() => navigate("/edit/"+props.album.id)}>
                <div className="card-header d-flex align-items-center border border-0">
                    <h5 className="my-2">{props.album.title}</h5>
                </div>
                <img src={props.album.coverURL} alt={`Couverture de ${props.album.title}`} className="border" />
                <div className="card-body border border-0">
                    <div className="d-flex justify-content-between">
                        <span className="fw-bold">Artiste : </span>
                        <span>{props.album.artist}</span>
                    </div>
                    <hr />
                    <div className="d-flex justify-content-between">
                        <span className="fw-bold">Date de sortie : </span>
                        <span>{new Date(props.album.releaseDate).toLocaleDateString()}</span>
                    </div>
                    <hr />
                    <div className="d-flex justify-content-between">
                        <span className="fw-bold">Score : </span>
                        <span>{props.album.score} étoiles sur 5</span>
                    </div>
                </div>
            </div>
    );
}

interface AlbumProps {
    album: Album;
}

export default AlbumDisplay;