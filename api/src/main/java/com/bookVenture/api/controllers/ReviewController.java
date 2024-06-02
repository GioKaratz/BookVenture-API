package com.bookVenture.api.controllers;

import com.bookVenture.api.dto.ReviewDto;
import com.bookVenture.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private ReviewService reviewService;
    
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/book/{bookId}/review")
    public ResponseEntity<ReviewDto> createReview(
            @PathVariable(value = "bookId")int bookId,
            @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(
                bookId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/book/{bookId}/reviews")
    public List<ReviewDto> getReviewsByBookId(@PathVariable(value = "bookId") long bookId){
        return reviewService.getReviewsByBookId(bookId);
    }

    @GetMapping("/book/{bookId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewByID(
            @PathVariable(value = "bookId") long bookId,
            @PathVariable(value = "id") long reviewId
    ){
        ReviewDto reviewDto = reviewService.getReviewById(reviewId, bookId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @PutMapping("/book/{bookId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(
            @PathVariable(value = "bookId") long bookId,
            @PathVariable(value = "id") long reviewId,
            @RequestBody ReviewDto reviewDto
    ){
        ReviewDto updatedReview = reviewService.updateReview(reviewId, bookId, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/book/{bookId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "bookId") long bookId,
                                               @PathVariable(value = "id") long reviewId){
        reviewService.deleteReview(reviewId, bookId);
        return new ResponseEntity<>("Review Deleted", HttpStatus.OK);
    }
}
