package com.movierecommendation.movie_review_service.controller;

import com.movierecommendation.movie_review_service.model.PlayList;
import com.movierecommendation.movie_review_service.service.PlayListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/playList")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PlayListController {
    private final PlayListService playListService;

    @GetMapping("/{userId}")
    public ResponseEntity<PlayList> getPlayListByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(playListService.getPlayListByUserId(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<PlayList> addMovieToPlayList(@PathVariable String userId, @RequestParam String movieId) {
        return ResponseEntity.ok(playListService.addMovieToPlayList(userId, movieId));
    }

    @PostMapping("/{userId}/remove")
    public ResponseEntity<PlayList> removeMovieFromPlayList(@PathVariable String userId, @RequestParam String movieId) {
        return ResponseEntity.ok(playListService.removeMovieFromPlayList(userId, movieId));
    }
}
