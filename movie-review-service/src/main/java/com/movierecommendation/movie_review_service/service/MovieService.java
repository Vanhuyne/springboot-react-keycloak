package com.movierecommendation.movie_review_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rapidapi.key}")
    private String RapidAPIKey;

    public Page<MovieResponse> getAllMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        log.info("Total Movies: {}", movies.getTotalElements());
        return movies.map(this::convertToMovieResponse);
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
        movie.setDescription(movieRequest.description());
        movie.setImage(movieRequest.image());
        movie.setBig_image(movieRequest.big_image());
        movie.setGenre(movieRequest.genre());
        movie.setThumbnail(movieRequest.thumbnail());
        movie.setRating(movieRequest.rating());
        movie.setRank(movieRequest.rank());
        movie.setYear(movieRequest.year());
        movie.setImdbid(movieRequest.imdbid());
        movie.setImdb_link(movieRequest.imdb_link());
        
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



    // mapper to movie response
    private MovieResponse convertToMovieResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getImage(),
                movie.getBig_image(),
                movie.getGenre(),
                movie.getThumbnail(),
                movie.getRating(),
                movie.getRank(),
                movie.getYear(),
                movie.getImdbid(),
                movie.getImdb_link()
        );
    }

    // mapper movie request to movie
    private Movie convertToMovie(MovieRequest movieRequest) {
        return new Movie(
                movieRequest.id(),
                movieRequest.title(),
                movieRequest.description(),
                movieRequest.image(),
                movieRequest.big_image(),
                movieRequest.genre(),
                movieRequest.thumbnail(),
                movieRequest.rating(),
                movieRequest.rank(),
                movieRequest.year(),
                movieRequest.imdbid(),
                movieRequest.imdb_link()
        );
    }

    public MovieRequest convertToMovieRequest(Movie movie) {
        return new MovieRequest(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getImage(),
                movie.getBig_image(),
                movie.getGenre(),
                movie.getThumbnail(),
                movie.getRating(),
                movie.getRank(),
                movie.getYear(),
                movie.getImdbid(),
                movie.getImdb_link()
        );
    }

    public List<Movie> getMovieFromRapid() throws JsonProcessingException{
        String url = "https://imdb-top-100-movies.p.rapidapi.com/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "imdb-top-100-movies.p.rapidapi.com");
        headers.set("x-rapidapi-key", RapidAPIKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity , String.class);

        String jsonResponse = response.getBody();
        List<Movie> movies = objectMapper.readValue(jsonResponse, new TypeReference<List<Movie>>() {});
         return movies;
    }

    public void fetchAndSaveMovies() {
        try {
            List<Movie> movies = getMovieFromRapid();
//            movieRepository.deleteAll();
            movieRepository.saveAll(movies);
            log.info("List of Movies saved: {}", movies.size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Error fetching movies: {}", e.getMessage());
        }
    }
}
