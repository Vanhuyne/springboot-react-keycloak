package com.movierecommendation.movie_review_service.dto;

public record ReviewResponse(
        String id,
        String movieId,
        String userId,
        int rating,
        String comment
) { }
