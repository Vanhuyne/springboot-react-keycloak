import React from 'react';

interface FeaturedMovieProps {
  title: string;
  description: string;
  genre: string;
  rating: number;
  imageUrl: string;
}

const FeaturedMovie: React.FC<FeaturedMovieProps> = ({ title, description, genre, rating, imageUrl }) => {
  return (
    <div className="relative rounded-lg overflow-hidden mb-6">
      <img src={imageUrl} alt={title} className="w-full h-96 object-cover" />
      <div className="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-black to-transparent p-4 text-white">
        <span className="bg-pink-500 text-white text-xs font-bold px-2 py-1 rounded">{genre}</span>
        <h2 className="text-2xl font-bold mt-2">{title}</h2>
        <p className="text-sm mt-1">{description}</p>
        <div className="flex items-center mt-2">
          <span className="text-yellow-400 mr-1">★</span>
          <span>{rating}</span>
          <button className="ml-4 bg-pink-500 text-white px-3 py-1 rounded text-sm">View Rating</button>
        </div>
      </div>
    </div>
  );
};

export default FeaturedMovie;