package com.movierecommendation.movie_review_service.service;

import com.movierecommendation.movie_review_service.dto.MovieRequest;
import com.movierecommendation.movie_review_service.dto.MovieResponse;
import com.movierecommendation.movie_review_service.exception.InvalidInputException;
import com.movierecommendation.movie_review_service.exception.MovieNotFoundException;
import com.movierecommendation.movie_review_service.model.Movie;
import com.movierecommendation.movie_review_service.model.Review;
import com.movierecommendation.movie_review_service.repository.MovieRepository;
import com.movierecommendation.movie_review_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::convertToMovieResponse)
                .toList();
    }

    public void createMovie(MovieRequest movieRequest) {
        if (movieRequest.title() == null || movieRequest.title().isEmpty()) {
            throw new InvalidInputException("Movie title cannot be empty");
        }
        Movie movie = convertToMovie(movieRequest);
        movieRepository.save(movie);
        log.info("Movie created: {}", movie);
    }

    public MovieResponse getMovieById(String id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        log.info("Movie found: {}", movie);
        return convertToMovieResponse(movie);
    }

    public MovieResponse updateMovie(String id , MovieRequest movieRequest) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        movie.setTitle(movieRequest.title());
        movie.setGenre(movieRequest.genre());
        movie.setReleaseYear(movieRequest.releaseYear());
        movie.setDirector(movieRequest.director());
        movie.setDescription(movieRequest.description());
        movie.setPoster(movieRequest.poster());

        movieRepository.save(movie);
        log.info("Movie updated: {}", movie);
        return convertToMovieResponse(movie);
    }

    public void deleteMovie(String id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        List<Review> reviews = reviewRepository.findByMovieId(id);
        reviewRepository.deleteAll(reviews);
        movieRepository.delete(movie);
        log.info("Movie deleted: {}", movie);
    }

    public List<MovieResponse> searchMovies(String query) {
        // Implement search logic (e.g., by title, genre, or director)
        return movieRepository.findByTitleContainingOrGenreContainingOrDirectorContaining(query, query, query)
                .stream()
                .map(this::convertToMovieResponse)
                .toList();
    }

    public List<MovieResponse> getMoviesByYear(int year) {
        return movieRepository.findByReleaseYear(year)
                .stream()
                .map(this::convertToMovieResponse)
                .toList();
    }
    public List<MovieResponse> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre)
                .stream()
                .map(this::convertToMovieResponse)
                .toList();
    }


    // mapper to movie response
    private MovieResponse convertToMovieResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getReleaseYear(),
                movie.getDirector(),
                movie.getDescription(),
                movie.getPoster()
        );
    }

    // mapper movie request to movie
    private Movie convertToMovie(MovieRequest movieRequest) {
        return new Movie(
                movieRequest.id(),
                movieRequest.title(),
                movieRequest.genre(),
                movieRequest.releaseYear(),
                movieRequest.director(),
                movieRequest.description(),
                movieRequest.poster());
    }

    public MovieRequest convertToMovieRequest(Movie movie) {
        return new MovieRequest(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getReleaseYear(),
                movie.getDirector(),
                movie.getDescription(),
                movie.getPoster()
        );
    }
}
