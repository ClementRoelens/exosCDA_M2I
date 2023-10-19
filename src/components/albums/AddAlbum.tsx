import { useEffect, useRef, useState } from "react";
import FormComponent from "../shared/FormComponent";
import { useAppDispatch, useAppSelector } from "../../config/hooks";
import { Alert } from "antd";
import { Album } from "../../models/Album";
import { createAlbum, readAllAlbums, readOneAlbum, updateAlbum } from "./albumSlice";
import { useNavigate, useParams } from "react-router-dom";

function AddAlbum() {
    const titleRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const artistRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const releaseDateRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const scoreRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const coverURLRef = useRef() as React.MutableRefObject<HTMLInputElement>;

    const [isFormIncorrect, setIsFormIncorrect] = useState(false);
    const [isRequestRejected, setIsRequestRejected] = useState(false);
    const [isCoverInputNeeded, setIsCoverInputNeeded] = useState(true);
    const [album, setAlbum] = useState<Album | null>(null);
    const { id } = useParams();

    const user = useAppSelector(state => state.users.user);
    const dispatch = useAppDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        // Si on a une id, on est en mode édition
        if (id) {
            // Mais on doit vérifier qu'il y a bien un album correspondant à cette id
            dispatch(readOneAlbum(id))
                .then(res => {
                    const fetchedAlbum = res.payload as Album;
                    // Sûrement une meilleure manière de vérifier que la réponse est bien un Album
                    if ("title" in fetchedAlbum && "artist" in fetchedAlbum && "releaseDate" in fetchedAlbum && "coverURL" in fetchedAlbum && "score" in fetchedAlbum && "id" in fetchedAlbum) {
                        setAlbum(fetchedAlbum);
                        titleRef.current.defaultValue = fetchedAlbum.title;
                        artistRef.current.defaultValue = fetchedAlbum.artist;
                        releaseDateRef.current.defaultValue = fetchedAlbum.releaseDate;
                        scoreRef.current.value = fetchedAlbum.score.toString();
                        setIsCoverInputNeeded(false);
                    } else {
                        navigate("/");
                    }
                })
                .catch(error => {
                    console.error(error);
                    navigate("/");
                });
        }
    }, [id]);

    async function addOrEditAlbum() {
        // Que ce soit éditer ou ajouter, il faut être authentifié
        if (user) {
            if (titleRef.current.value.trim() !== "" &&
                artistRef.current.value.trim() !== "" &&
                releaseDateRef.current.value.trim() !== "" &&
                scoreRef.current.value.trim() !== "" &&
                (!isCoverInputNeeded || coverURLRef.current.value.trim() !== "")) {
                let newAlbum: Album = {
                    title: titleRef.current.value,
                    artist: artistRef.current.value,
                    releaseDate: releaseDateRef.current.value,
                    score: +scoreRef.current.value,
                    coverURL: isCoverInputNeeded ? `/src/assets/${coverURLRef.current.value.split('\\')[2]}` : album!.coverURL
                };
                // Si un id est présent, on est en mode édition
                if (id) {
                    newAlbum.id = id;
                    const res = await dispatch(updateAlbum(newAlbum)) as any;
                    if (res.error) {
                        setIsRequestRejected(true);
                        setTimeout(() => navigate("/"), 2000)
                    } else {
                        await dispatch(readAllAlbums());
                        navigate("/");
                    }
                } else {
                    const res = await dispatch(createAlbum(newAlbum)) as any;
                    if (res.error) {
                        setIsRequestRejected(true);
                        setTimeout(() => navigate("/"), 2000)
                    } else {
                        await dispatch(readAllAlbums());
                        navigate("/");
                    }
                }
            } else {
                setIsFormIncorrect(true);
            }
        } else {
            navigate("/sign?mode=signin");
        }
    }


    return (<>
        {isRequestRejected && <Alert message="Erreur" description="Quelque chose s'est mal passé. Votre identification a peut-être expiré" type="error" />}
        <FormComponent submitFunction={addOrEditAlbum}>
            {album && !isCoverInputNeeded &&
                <>
                    <img className="w-25 d-block mx-auto mt-3 clickable" src={album.coverURL} alt={`Couverture de ${album.title}`} onClick={() => setIsCoverInputNeeded(true)} />
                    <p className="mx-auto mt-3 text-center">Clique sur l'image si tu veux la modifier aussi</p>
                </>
            }
            {isFormIncorrect && <Alert className="mt-2" message="Erreur" description="Il faut remplir tous les champs" type="error" />}
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
            {/* En mode édition, on n'aura pas ça */}
            {isCoverInputNeeded &&
                <div>
                    <label htmlFor="cover" className="form-label mt-3">Couverture : </label>
                    <input type="file" className="form-control" id="cover" ref={coverURLRef} required />
                </div>
            }
        </FormComponent>
    </>
    );
}


export default AddAlbum;