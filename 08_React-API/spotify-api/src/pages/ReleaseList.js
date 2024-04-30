import { useEffect, useState } from 'react'
import { getToken, getNewReleases } from '../api/SpotifyAPI'
import AlbumItem from '../components/AlbumItem'

function ReleaseList() {
	const [albumList, setAlbumList] = useState()

	useEffect(() => {
		getToken().then(token => {
			getNewReleases(token).then(data => setAlbumList(data))
		})
	}, [])

	return (
		<div className='content-col'>
			<h1>신규 발매 앨범</h1>

			<div className='content-row'>
				{albumList &&
					albumList.map(album => (
						<AlbumItem key={album.id} album={album} />
					))}
			</div>
		</div>
	)
}

export default ReleaseList
