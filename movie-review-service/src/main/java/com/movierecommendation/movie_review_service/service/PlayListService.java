package com.movierecommendation.movie_review_service.service;

import com.movierecommendation.movie_review_service.model.PlayList;
import com.movierecommendation.movie_review_service.repository.PlayListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayListService {

    private final PlayListRepository playListRepository;

    private PlayList getOrCreatePlayList(String userId) {
        PlayList playList = playListRepository.findByUserId(userId);
        if (playList == null) {
            playList = PlayList.builder()
                    .userId(userId)
                    .movieIds(new ArrayList<>())
                    .build();
            playListRepository.save(playList);
        }
        return playList;
    }

    public PlayList addMovieToPlayList(String userId, String movieId) {
        PlayList playList = getOrCreatePlayList(userId);
        if (!playList.getMovieIds().contains(movieId)) {
            playList.getMovieIds().add(movieId);
            playListRepository.save(playList);
            log.info("Movie {} added to playlist of user {}", movieId, userId);
        }
        return playList;
    }

    public PlayList removeMovieFromPlayList(String userId, String movieId) {
        PlayList playList = getOrCreatePlayList(userId);
        playList.getMovieIds().remove(movieId);
        playListRepository.save(playList);
        log.info("Movie {} removed from playlist of user {}", movieId, userId);
        return playList;
    }

    public PlayList getPlayListByUserId(String userId) {
        return getOrCreatePlayList(userId);
    }

    
}
