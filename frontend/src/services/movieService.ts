import axios from "axios";
import { Movie } from "../models/Moive";

interface MovieResponse {
  content: Movie[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
}
const API_URL = 'http://localhost:8082/api/movies';

export const getMovies = async (page: number = 0, size: number = 10): Promise<MovieResponse> => {
    try {
          const response = await axios.get<MovieResponse>(`${API_URL}?page=${page}&size=${size}`);
          if(response.status !== 200) {
            throw new Error('Failed to fetch movies');
          }
          return await response.data;
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
  };

  // export const getMovieById = async (id: string): Promise<Movie> => {
  //   try {
  //     const response = await axios.get<Movie>(`${API_URL}/${id}`);
  //     return response.data;
  //   } catch (error) {
  //     console.error(`Error fetching movie with id ${id}:`, error);
  //     throw error;
  //   }
  // };
