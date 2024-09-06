import axios from "axios";
import { PlayList } from "../models/PlayList";


const API_URL = 'http://localhost:9000/api/playList';

export const getUserPlaylists = async (userId: string) : Promise<PlayList> => {
  try {
    const response = await axios.get(`${API_URL}/${userId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching playlists:', error);
    throw error;
  }
};

export const addMovieToPlayList = async (userId: string, movieId: string): Promise<PlayList> => {
    try {
      const response = await axios.post<PlayList>(`${API_URL}/${userId}/add?movieId=${movieId}`);
      return response.data;
    } catch (error) {
      console.error('Error adding movie to playlist:', error);
      throw error;
    }
  };

  export const removeMovieFromPlayList = async (userId: string, movieId: string): Promise<PlayList> => {
    try {
      const response = await axios.post<PlayList>(`${API_URL}/${userId}/remove?movieId=${movieId}`);
      return response.data;
    } catch (error) {
      console.error('Error removing movie from playlist:', error);
      throw error;
    }
  };