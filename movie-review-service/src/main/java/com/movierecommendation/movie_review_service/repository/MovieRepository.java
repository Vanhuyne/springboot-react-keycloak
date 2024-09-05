package com.movierecommendation.movie_review_service.repository;

import com.movierecommendation.movie_review_service.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {

//    List<Movie> findByTitleContainingOrGenreContainingOrDirectorContaining(String title, String genre, String director);
//    List<Movie> findByReleaseYear(int releaseYear);
//    List<Movie> findByGenre(String genre);
//    List<Movie> findByGenreIn(List<String> genres);

}
