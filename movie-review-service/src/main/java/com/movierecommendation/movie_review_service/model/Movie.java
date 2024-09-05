package com.movierecommendation.movie_review_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String description;
    private String image;
    private String big_image;
    private List<String> genre;
    private String thumbnail;
    private String rating;
    private int rank;
    private int year;
    private String imdbid;
    private String imdb_link;
}
