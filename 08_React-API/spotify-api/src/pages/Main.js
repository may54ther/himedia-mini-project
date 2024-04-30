import { Link } from 'react-router-dom'

function Main() {
	return (
		<div className='content-col'>
			<h2>메인</h2>

			<Link to='/albums'>🔗신규 발매 앨범 목록</Link>
		</div>
	)
}

export default Main
