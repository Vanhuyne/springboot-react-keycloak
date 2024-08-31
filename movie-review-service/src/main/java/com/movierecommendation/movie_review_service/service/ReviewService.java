package com.movierecommendation.movie_review_service.service;

import com.movierecommendation.movie_review_service.dto.ReviewRequest;
import com.movierecommendation.movie_review_service.dto.ReviewResponse;
import com.movierecommendation.movie_review_service.exception.InvalidInputException;
import com.movierecommendation.movie_review_service.exception.ReviewNotFoundException;
import com.movierecommendation.movie_review_service.model.Review;
import com.movierecommendation.movie_review_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review createReview(ReviewRequest reviewRequest) {
        if(reviewRequest.rating() < 1 || reviewRequest.rating() > 5) {
            throw new InvalidInputException("Rating must be between 1 and 5");
        }
        Review review = convertToReview(reviewRequest);
        reviewRepository.save(review);
        log.info("Review created: {}", review);
        return review;
    }

    public ReviewResponse getReviewById(String id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        log.info("Review found: {}", review);
        return convertToReviewResponse(review);
    }

    public List<ReviewResponse> getReviewsForMovie(String movieId) {
        return reviewRepository.findByMovieId(movieId)
                .stream()
                .map(this::convertToReviewResponse)
                .toList();
    }

    public ReviewResponse updateReview(String id, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        if (reviewRequest.rating() < 1 || reviewRequest.rating() > 5) {
            throw new InvalidInputException("Rating must be between 1 and 5");
        }

        review.setRating(reviewRequest.rating());
        review.setComment(reviewRequest.comment());

        reviewRepository.save(review);
        log.info("Review updated: {}", review);
        return convertToReviewResponse(review);
    }

    public void deleteReview(String id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException("Review not found");
        }
        reviewRepository.deleteById(id);
    }

    public double getAverageRatingForMovie(String movieId) {
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    private ReviewResponse convertToReviewResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getMovieId(),
                review.getUserId(),
                review.getRating(),
                review.getComment()
        );
    }

    private Review convertToReview(ReviewRequest reviewRequest) {
        return new Review(
                reviewRequest.id(),
                reviewRequest.movieId(),
                reviewRequest.userId(),
                reviewRequest.rating(),
                reviewRequest.comment()
        );
    }
}
