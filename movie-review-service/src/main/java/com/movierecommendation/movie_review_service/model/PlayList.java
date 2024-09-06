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
@Document(value = "playList")
public class PlayList {
    @Id
    private String id;
    private String userId;
    private List<String> movieIds;

}
