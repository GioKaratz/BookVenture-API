package com.bookVenture.api.repositories;

import com.bookVenture.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
