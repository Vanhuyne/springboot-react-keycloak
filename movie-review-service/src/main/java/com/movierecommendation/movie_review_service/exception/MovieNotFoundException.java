package com.movierecommendation.movie_review_service.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String id) {
        super("Movie not found with id: " + id);
    }
}
