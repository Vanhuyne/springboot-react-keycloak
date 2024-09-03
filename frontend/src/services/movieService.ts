import axios from "axios";
import { Movie } from "../models/Moive";

const API_URL = 'http://localhost:9000/api/movies';

export const getMovies = async (): Promise<Movie[]> => {
    try {
        const response = await axios.get<Movie[]>(API_URL);
    return response.data;
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
  };

  export const getMovieById = async (id: string): Promise<Movie> => {
    try {
      const response = await axios.get<Movie>(`${API_URL}/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching movie with id ${id}:`, error);
      throw error;
    }
  };
