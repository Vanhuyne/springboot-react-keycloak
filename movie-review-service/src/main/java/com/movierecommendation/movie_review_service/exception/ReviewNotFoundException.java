package com.movierecommendation.movie_review_service.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String id) {
        super("Review not found with id: " + id);
    }
}
