import React, { useState, useEffect } from 'react';
import { useKeycloak } from '@react-keycloak/web';
import { PlayList } from '../models/PlayList';
import { getUserPlaylists, removeMovieFromPlayList } from '../services/playlistService';

const UserPlaylists: React.FC = () => {
  const { keycloak } = useKeycloak();
  const [playlist, setPlaylist] = useState<PlayList | null>(null);

  useEffect(() => {
    if (keycloak.authenticated) {
      fetchPlaylist();
    }
  }, [keycloak.authenticated]);

  const fetchPlaylist = async () => {
    try {
      const userId = keycloak.tokenParsed?.sub as string;
      const userPlaylist = await getUserPlaylists(userId);
      setPlaylist(userPlaylist);
    } catch (error) {
      console.error('Error fetching playlist:', error);
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
      }
    }
  };

  return (
    <div className="mt-8">
      <h2 className="text-2xl font-bold mb-4">Your Playlist</h2>
      {keycloak.authenticated ? (
        playlist ? (
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
        )
      ) : (
        <p>Please log in to view and manage your playlist.</p>
      )}
    </div>
  );
};

export default UserPlaylists;