import { useRef } from "react";
import { useAppDispatch, useAppSelector } from "../../config/hooks";
import { Album } from "../../models/Album";
import AlbumDisplay from "./AlbumDisplay";
import { Link } from "react-router-dom";
import { search } from "./albumSlice";

function AlbumList() {
    const albums = useAppSelector(state => state.albums.filteredAlbums);
    const searchRef = useRef() as React.MutableRefObject<HTMLInputElement>;
    const dispatch = useAppDispatch();

    function triggerSearch(){
        if (searchRef.current.value !== ""){
            dispatch(search(searchRef.current.value));
        }
    }

    return (
        <>
            <div className="albums-header d-flex justify-content-between">
                <h1>Albums</h1>
                <div className="search-area d-flex">
                    <label htmlFor="filter" className="align-self-center me-3">Rechercher par : </label>
                    <input type="text" className="rounded align-self-center py-1 px-2" placeholder="Entrez une valeur de recherche" onKeyDown={triggerSearch} ref={searchRef} />
                </div>
            </div>
            <hr />
            <div className="bg-dark row rounded justify-content-around">
                {albums.map((album: Album) =>
                    <AlbumDisplay key={album.id} album={album} />
                )}
            </div>
            <Link className="btn btn-success d-block col-2 mx-auto mt-4 fs-4" to="/addAlbum">Ajouter album</Link>
        </>
    );
}

export default AlbumList;