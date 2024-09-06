import React, { useEffect, useState } from 'react';
import { Movie } from '../models/Moive';
import { FaStar } from 'react-icons/fa';
import keycloak from '../keycloak';
import { addMovieToPlayList } from '../services/playlistService';

interface MovieRecommendationsProps {
  initialMovies: Movie[];
  fetchMoreMovies: (page: number) => Promise<Movie[]>;
  isLoading: boolean;
  error: string | null;
  currentPage: number;
}

const MovieRecommendations: React.FC<MovieRecommendationsProps> = ({ initialMovies, fetchMoreMovies }) => {
  
  const [movies, setMovies] = useState<Movie[]>(initialMovies);
  const [page, setPage] = useState<number>(1);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    setMovies(initialMovies);
  }, [initialMovies]);
  

  const handleSeeMore = async () => {
    setLoading(true);
    try {
      const newMovies = await fetchMoreMovies(page);
      setMovies([...movies, ...newMovies]);
      setPage(page + 1);
    } catch (error) {
      console.error('Error fetching more movies:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleAddToPlaylist = async (movieId: string) => {
    if(keycloak.authenticated){
      try{
        const userId = keycloak.tokenParsed?.sub as string;
        await addMovieToPlayList(userId, movieId);
        alert('Movie added to playlist');
      }catch(error){
        console.error('Error adding movie to playlist:', error);
        alert('Error adding movie to playlist');
      }
    }else{
      keycloak.login();
    }
  };

  return (
    <div className="mt-8">
      <h2 className="text-2xl font-bold mb-4">Recommended Movies</h2>
      <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">
        {movies.map((movie) => (
          <div key={movie.id} className="flex flex-col">
            <img
              src={movie.image}
              alt={movie.title}
              className="w-full h-auto shadow-md"
            />
            <div className="bg-gray-800 text-white p-4">
              <h3 className="text-lg font-semibold truncate">{movie.title}</h3>
              <p className="text-sm">{movie.year}</p>
              <p className="text-sm flex items-center">
                <FaStar className="text-yellow-400 mr-1" />
                {movie.rating}
              </p>
              <button
                onClick={() => handleAddToPlaylist(movie.id)}
                className="mt-2 w-full bg-blue-500 text-white px-2 py-1 rounded"
              >
                Add to Playlist
              </button>
            </div>
          </div>
        ))}
      </div>
      <div className="mt-6 text-center">
        <button
          onClick={handleSeeMore}
          disabled={loading}
          className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded"
        >
          {loading ? 'Loading...' : 'See More'}
        </button>
      </div>
    </div>
  );
};

export default MovieRecommendations;