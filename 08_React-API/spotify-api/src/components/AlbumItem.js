import { Link } from 'react-router-dom'

function AlbumItem({ album }) {
	return (
		<Link to={`/albums/${album.id}`} className="item">
			<h3>{album.name}</h3>
			<p>{album.artists[0].name} / {album.release_date}</p>

			<img src={album.images[0].url} alt="썸네일" />
		</Link>
	)
}

export default AlbumItem
