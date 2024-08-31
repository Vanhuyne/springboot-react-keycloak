package com.movierecommendation.movie_review_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String description;
    private String poster;
}
