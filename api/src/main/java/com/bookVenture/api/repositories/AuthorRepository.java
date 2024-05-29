package com.bookVenture.api.repositories;

import com.bookVenture.api.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
