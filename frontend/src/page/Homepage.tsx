import React, { useEffect, useState, useCallback } from 'react';
import FeaturedMovie from '../components/FeaturedMovie';
import MovieRecommendations from '../components/MovieRecommendations';
import { getMovies } from '../services/movieService';
import { Movie } from '../models/Moive';

const HomePage: React.FC = () => {
  const [movies, setMovies] = useState<Movie[]>([]);
  const [featuredMovie, setFeaturedMovie] = useState<Movie | null>(null);
  const [currentPage, setCurrentPage] = useState(0);
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  const fetchInitialMovies = useCallback(async () => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await getMovies(0, 10); // Fetch 10 movies initially
      setMovies(response.content);
      setFeaturedMovie(response.content[0]);
      console.log(response.content);
      setCurrentPage(1);
      return response.content;
    } catch (error) {
      console.error('Error fetching initial movies:', error);
      setError('Failed to fetch movies. Please try again.');
      return [];
    } finally {
      setIsLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchInitialMovies();
  }, [fetchInitialMovies]);

  const fetchMoreMovies = useCallback(async (): Promise<Movie[]> => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await getMovies(currentPage);
      const newMovies = response.content;
      setMovies(prevMovies => [...prevMovies, ...newMovies]);
      setCurrentPage(prevPage => prevPage + 1);
      return newMovies;
    } catch (error) {
      console.error('Error fetching more movies:', error);
      setError('Failed to fetch more movies. Please try again.');
      return [];
    } finally {
      setIsLoading(false);
    }
  }, [currentPage]);

  if (isLoading && movies.length === 0) {
    return <div>Loading...</div>;
  }

  if (error && movies.length === 0) {
    return <div>{error}</div>;
  }

  return (
    <div className="p-4">
      {featuredMovie && (
        <FeaturedMovie
          title={featuredMovie.title}
          description={featuredMovie.description}
          genre={featuredMovie.genre.join(', ')}
          rating={parseFloat(featuredMovie.rating)}
          imageUrl={featuredMovie.image}
        />
      )}
      <MovieRecommendations
        initialMovies={movies}
        fetchMoreMovies={fetchMoreMovies}
        isLoading={isLoading}
        error={error}
        currentPage={currentPage}
      />
    </div>
  );
};

export default HomePage;