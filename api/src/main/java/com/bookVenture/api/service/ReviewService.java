package com.bookVenture.api.service;

import com.bookVenture.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(long bookId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByBookId(long id);
    ReviewDto getReviewById(long reviewId, long bookId);
    ReviewDto updateReview(long reviewId, long bookId, ReviewDto reviewDto);
    void deleteReview(long id, long bookId);
}
