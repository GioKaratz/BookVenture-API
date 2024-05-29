package com.bookVenture.api.service;

import com.bookVenture.api.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    List<BookDto> getAllBooks();
}
