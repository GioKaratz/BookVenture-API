package com.bookVenture.api.service;

import com.bookVenture.api.dto.BookDto;
import com.bookVenture.api.dto.BookResponse;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    BookResponse getAllBooks(int page, int pageSize);
    BookDto getBookById(long id);
    BookDto updateBook(BookDto bookDto, long id);
    void deleteBook(long id);
}
