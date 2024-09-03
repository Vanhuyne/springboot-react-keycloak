import React from 'react';

interface Movie {
  title: string;
  imageUrl: string;
}

interface MovieRecommendationsProps {
  movies: Movie[];
}

const MovieRecommendations: React.FC<MovieRecommendationsProps> = ({ movies }) => {
  return (
    <div>
      <div className="flex justify-between items-center mb-4">
        <h3 className="text-xl font-bold">Movie Recommendations</h3>
        <div className="flex">
          <input type="text" placeholder="Search Movies..." className="border rounded-l px-2 py-1" />
          <button className="bg-gray-200 px-3 py-1 rounded-r">More filters</button>
        </div>
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {movies.map((movie, index) => (
          <div key={index} className="bg-white rounded-lg overflow-hidden shadow">
            <img src={movie.imageUrl} alt={movie.title} className="w-full h-40 object-cover" />
            <div className="p-2">
              <h4 className="font-bold">{movie.title}</h4>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MovieRecommendations;