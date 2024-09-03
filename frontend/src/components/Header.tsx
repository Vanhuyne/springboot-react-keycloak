import { useKeycloak } from '@react-keycloak/web';
import { Link } from 'react-router-dom';
const Header = () => {
    const { keycloak } = useKeycloak();
    
    return (
        <header className="flex justify-between items-center p-4 ">
            <div className="flex items-center space-x-4">
                <img src="/assets/logo.png" alt="HeyCine Logo" className="h-10 w-10 rounded-full" />
                <span className="text-2xl font-bold">HeyCine</span>   
                <nav className="flex space-x-4">
                    <Link to="/movies" className="text-lg text-gray-700 hover:text-gray-900">Movies</Link>
                    <Link to="/series" className="text-lg text-gray-700 hover:text-gray-900">Series</Link>
                    <Link to="/tv-shows" className="text-lg text-gray-700 hover:text-gray-900">TV Shows</Link>
                </nav>
            </div>
            <div className="flex space-x-2 items-center">
                {keycloak.authenticated ? (
                    <>
                        <span className="text-lg text-gray-700">{keycloak.tokenParsed?.preferred_username}</span>
                        <button
                            className="px-4 py-2 border rounded text-gray-700 hover:bg-gray-200"
                            onClick={() => keycloak.logout()}
                        >
                            Sign out
                        </button>
                    </>
                ) : (
                    <>
                        <button
                            className="px-4 py-2 border rounded text-gray-700 hover:bg-gray-200"
                            onClick={() => keycloak.login()}
                        >
                            Log in
                        </button>
                        <button
                            className="px-4 py-2 bg-pink-500 text-white rounded hover:bg-pink-600"
                            onClick={() => keycloak.register()}
                        >
                            Sign up
                        </button>
                    </>
                )}
            </div>
        </header>
    );
};

export default Header;