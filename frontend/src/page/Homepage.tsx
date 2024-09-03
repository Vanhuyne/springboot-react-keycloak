import React, { useEffect, useState } from 'react';
import FeaturedMovie from '../components/FeaturedMovie';
import MovieRecommendations from '../components/MovieRecommendations';

import { getMovies } from '../services/movieService';
import { Movie } from '../models/Moive';


const HomePage: React.FC = () => {
  const [movies, setMovies] = useState<Movie[]>([]);
  const [featuredMovie, setFeaturedMovie] = useState<Movie | null>(null);

  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const fetchedMovies = await getMovies();
        setMovies(fetchedMovies);
        if (fetchedMovies.length > 0) {
          setFeaturedMovie(fetchedMovies[0]); // Set the first movie as featured
        }
      } catch (error) {
        console.error('Error fetching movies:', error);
      }
    };

    fetchMovies();
  }, []);
  
  return (
    <div className="p-4 ">
      {featuredMovie && (
        <FeaturedMovie
          title={featuredMovie.title}
          description={featuredMovie.description}
          genre={featuredMovie.genre}
          rating={0} // You might want to add a rating field to your Movie model
          imageUrl={featuredMovie.poster}
        />
      )}
      <MovieRecommendations movies={movies.map(movie => ({ title: movie.title, imageUrl: movie.poster }))} />
    </div>
  );
};

export default HomePage;