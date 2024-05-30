package com.bookVenture.api.repositories;

import com.bookVenture.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBookId(long bookId);

}
