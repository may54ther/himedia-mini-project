const BASE_URL = 'https://api.spotify.com/v1/'

/* Client Credentials */
export async function getToken() {
	const CLIENT_ID = '5eb6813a0b464f02901886db0f428b5e'
	const CLIENT_SECRET = '11c9ab475b9c4455a55ffb03a3a90eec'

	const response = await fetch('https://accounts.spotify.com/api/token', {
		method: 'POST',
		body: new URLSearchParams({
			grant_type: 'client_credentials',
		}),
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
			Authorization: 'Basic ' + btoa(CLIENT_ID + ':' + CLIENT_SECRET),
		},
	});
	const data =  await response.json();
	return data.access_token;
}

export async function getNewReleases(access_token) {
	const url = `${BASE_URL}browse/new-releases?locale=KR&limit=30&offset=0`
	const response = await fetch(url, {
		method: 'GET',
		headers: { Authorization: 'Bearer ' + access_token}
	});
	const data = await response.json();
	return data.albums.items;
}

export async function getAlbum(access_token, id) {
	const url = `${BASE_URL}albums/${id}?market=KR`
	const response = await fetch(url, {
		method: 'GET',
		headers: {Authorization: 'Bearer ' + access_token}
	})
	const data =  await response.json();
	return data;
}
