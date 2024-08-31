package com.movierecommendation.movie_review_service.service;

import com.movierecommendation.movie_review_service.dto.MovieResponse;
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
public class RecommendationService {
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public List<MovieResponse> getRecommendationsForUser(String userId) {
        // sophisticated algorithm to get recommendations
        List<Review> reviews = reviewRepository.findByUserId(userId);
        List<String> highlyRatedMovies = reviews.stream()
                .filter(review -> review.getRating() >= 4)
                .map(review -> movieRepository.findById(review.getMovieId())
                        .orElseThrow().getGenre())
                .distinct()
                .toList();

        return movieRepository.findByGenreIn(highlyRatedMovies)
                .stream()
                .map(this::convertToMovieResponse)
                .limit(10)
                .toList();
    }

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
}
