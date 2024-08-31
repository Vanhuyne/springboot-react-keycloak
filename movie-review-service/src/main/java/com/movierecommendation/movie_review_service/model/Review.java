package com.movierecommendation.movie_review_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviews")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String id;
    private String movieId;
    private String userId;
    private int rating;
    private String comment;
}
