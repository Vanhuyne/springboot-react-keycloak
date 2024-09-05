package com.movierecommendation.movie_review_service.dto;
import java.util.List;

public record MovieResponse(
    String id,
    String title,
    String description,
    String image,
    String big_image,
    List<String> genre,
    String thumbnail,
    String rating,
    int rank,
    int year,
    String imdbid,
    String imdb_link
) {}
