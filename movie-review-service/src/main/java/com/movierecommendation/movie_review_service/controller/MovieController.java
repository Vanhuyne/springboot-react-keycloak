package com.movierecommendation.movie_review_service.controller;

import com.movierecommendation.movie_review_service.dto.MovieRequest;
import com.movierecommendation.movie_review_service.dto.MovieResponse;
import com.movierecommendation.movie_review_service.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createMovie(@RequestBody MovieRequest movie) {
        movieService.createMovie(movie);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable String id, @RequestBody MovieRequest movie) {
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
