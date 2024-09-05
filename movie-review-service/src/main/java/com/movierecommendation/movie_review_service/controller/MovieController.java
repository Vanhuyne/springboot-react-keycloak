package com.movierecommendation.movie_review_service.controller;

import com.movierecommendation.movie_review_service.dto.MovieRequest;
import com.movierecommendation.movie_review_service.dto.MovieResponse;
import com.movierecommendation.movie_review_service.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
            ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movieService.getAllMovies(pageable));
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

    @GetMapping("/fetch-and-save")
    public ResponseEntity<String> fetchAndSaveMovies() {
        movieService.fetchAndSaveMovies();
        return ResponseEntity.ok("Movies fetched and saved successfully");
    }
}
