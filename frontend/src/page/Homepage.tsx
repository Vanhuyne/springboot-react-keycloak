import React, { useEffect, useState } from 'react';
import FeaturedMovie from '../components/FeaturedMovie';
import MovieRecommendations from '../components/MovieRecommendations';
import { getMovies } from '../services/movieService';
import { Movie } from '../models/Moive';



const HomePage: React.FC = () => {
  const [movies, setMovies] = useState<Movie[]>([]);
  const [featuredMovie, setFeaturedMovie] = useState<Movie | null>(null);
  const [currentPage, setCurrentPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);

  useEffect(() => {
    const initializeMovies = async () => {
      const initialMovies = await fetchInitialMovies();
      if (initialMovies.length > 0) {
        setMovies(initialMovies);
        setFeaturedMovie(initialMovies[0]);
      }
    };

    initializeMovies();
  }, []);

  const fetchInitialMovies = async () => {
    try {
      const response = await getMovies(0);
      console.log(response.content);
      setCurrentPage(1);
      setHasMore(response.number < response.totalPages - 1);
      return response.content;
    } catch (error) {
      console.error('Error fetching initial movies:', error);
      return [];
    }
  };

  const fetchMoreMovies = async (): Promise<Movie[]> => {
    try {
      const response = await getMovies(currentPage);
      const newMovies = response.content;
      setMovies(prevMovies => [...prevMovies, ...newMovies]);
      setCurrentPage(prevPage => prevPage + 1);
      setHasMore(response.number < response.totalPages - 1);
      return newMovies;
    } catch (error) {
      console.error('Error fetching more movies:', error);
      return [];
    }
  };

  return (
    <div className="p-4">
      {featuredMovie && (
        <FeaturedMovie
          title={featuredMovie.title}
          description={featuredMovie.description}
          genre={featuredMovie.genre.join(', ')}
          rating={parseFloat(featuredMovie.rating)}
          imageUrl={featuredMovie.big_image}
        />
      )}
      <MovieRecommendations
      initialMovies={movies}
      fetchMoreMovies={fetchMoreMovies}
      />
    </div>
  );
};

export default HomePage;