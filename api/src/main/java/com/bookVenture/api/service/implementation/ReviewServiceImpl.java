package com.bookVenture.api.service.implementation;

import com.bookVenture.api.dto.ReviewDto;
import com.bookVenture.api.exceptions.BookNotFoundException;
import com.bookVenture.api.exceptions.ReviewNotFoundException;
import com.bookVenture.api.models.Book;
import com.bookVenture.api.models.Review;
import com.bookVenture.api.repositories.BookRepository;
import com.bookVenture.api.repositories.ReviewRepository;
import com.bookVenture.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public ReviewDto createReview(long bookId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with associated reviews not found"));

        review.setBook(book);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByBookId(long id) {
        List<Review> reviews = reviewRepository.findByBookId(id);

        return reviews.stream()
                .map(review -> mapToDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(long reviewId, long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with associated reviews not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review with associated book not found"));

        if(review.getBook().getId() != book.getId()){
            throw new ReviewNotFoundException("This review does not belong to a book");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(long reviewId, long bookId, ReviewDto reviewDto) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with assosiated reviews not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review with associated book not found"));
        if(review.getBook().getId() != book.getId()){
            throw new ReviewNotFoundException("This review does not belong to a book");
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(long id, long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with associated reviews not found"));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with associated book not found"));
        if(review.getBook().getId() != book.getId()){
            throw new ReviewNotFoundException("This review does not belong to a book");
        }

        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto){
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
