package com.movierecommendation.movie_review_service.repository;

import com.movierecommendation.movie_review_service.model.PlayList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayListRepository extends MongoRepository<PlayList, String> {
    PlayList findByUserId(String userId);
    boolean existsByUserIdAndMovieIdsContaining(String userId, String movieId);
}
