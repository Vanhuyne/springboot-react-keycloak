package com.movierecommendation.movie_review_service.dto;

public record ReviewRequest(
        String id,
        String movieId,
        String userId,
        int rating,
        String comment
) { }
