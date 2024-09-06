import React, { useEffect, useState } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { PlayList } from '../models/PlayList';
import { getUserPlaylists, removeMovieFromPlayList } from '../services/playlistService';
import { log } from 'console';

interface PlaylistModalProps {
  onClose: () => void;
}

const PlaylistModal: React.FC<PlaylistModalProps> = ({ onClose }) => {
  const { keycloak } = useKeycloak();
  const [playlist, setPlaylist] = useState<PlayList | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    if (keycloak.authenticated) {
      fetchPlaylist();
    }
  }, [keycloak.authenticated]);

  const fetchPlaylist = async () => {
    setIsLoading(true);
    setError(null);
    try {
      const userId = keycloak.tokenParsed?.sub as string;
      const userPlaylist = await getUserPlaylists(userId);

      console.log(userPlaylist);
      setPlaylist(userPlaylist);
    } catch (error) {
      console.error('Error fetching playlist:', error);
      setError('Failed to fetch playlist. Please try again.');
    } finally {
      setIsLoading(false);
    }
  };

  const handleRemoveMovie = async (movieId: string) => {
    if (keycloak.authenticated && playlist) {
      try {
        const userId = keycloak.tokenParsed?.sub as string;
        const updatedPlaylist = await removeMovieFromPlayList(userId, movieId);
        setPlaylist(updatedPlaylist);
      } catch (error) {
        console.error('Error removing movie from playlist:', error);
        setError('Failed to remove movie. Please try again.');
      }
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-white p-6 rounded-lg max-w-lg w-full max-h-[80vh] overflow-y-auto">
        <h2 className="text-2xl font-bold mb-4">Your Playlist</h2>
        {isLoading ? (
          <p>Loading your playlist...</p>
        ) : error ? (
          <p className="text-red-500">{error}</p>
        ) : playlist && Array.isArray(playlist.movieIds) && playlist.movieIds.length > 0 ? (
          <ul>
            {playlist.movieIds.map((movieId) => (
              <li key={movieId} className="mb-2 flex justify-between items-center">
                <span>{movieId}</span>
                <button
                  onClick={() => handleRemoveMovie(movieId)}
                  className="bg-red-500 text-white px-2 py-1 rounded"
                >
                  Remove
                </button>
              </li>
            ))}
          </ul>
        ) : (
          <p>Your playlist is empty.</p>
        )}
        <button
          onClick={onClose}
          className="mt-4 bg-blue-500 text-white px-4 py-2 rounded"
        >
          Close
        </button>
      </div>
    </div>
  );
};

export default PlaylistModal;
