import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Layout from './layouts/Layout'
import Main from './pages/Main'
import './App.css'
import ReleaseList from './pages/ReleaseList'
import AlbumDetail from "./pages/AlbumDetail";

function App() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path='/' element={<Layout />}>
					<Route index element={<Main />} />

					<Route path='albums' >
						<Route index element={<ReleaseList/>} />
						<Route path=':albumId' element={<AlbumDetail />} />
					</Route>
				</Route>
			</Routes>
		</BrowserRouter>
	)
}

export default App
