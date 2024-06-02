package com.bookVenture.api.repositories;

import com.bookVenture.api.models.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(long authorId);
}
