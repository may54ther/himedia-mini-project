import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { getAlbum, getToken } from '../api/SpotifyAPI';

function AlbumDetail() {
    const { albumId } = useParams();
    const [ album, setAlbum ] = useState();

    useEffect(() => {
        getToken().then(token =>
            getAlbum(token, albumId).then(data => setAlbum(data))
        );
    }, []);

    return (
        <div className="content-col">
            {album && (
                <>
                    <h1>{album.name}</h1>
                    <img src={album.images[0].url} width="300" alt="썸네일" />
                    <p>가수명 : {album.artists[0].name}</p>
                    <p>발매일 : {album.release_date}</p>

                    <h3>TrackList</h3>
                    <ul>
                        {album.tracks.items.map(track =>
                            <li key={track.id}>{track.name}</li>
                        )}
                    </ul>
                    <p>{album.label}</p>
                    <button><Link to="/albums">목록으로</Link></button>
                </>
            )}
        </div>
    );
}

export default AlbumDetail;
