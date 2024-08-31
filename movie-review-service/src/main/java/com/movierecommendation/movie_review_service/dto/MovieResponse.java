package com.movierecommendation.movie_review_service.dto;

public record MovieResponse(
        String id,
        String title,
        String genre,
        int releaseYear,
        String director,
        String description,
        String poster
) {
}
