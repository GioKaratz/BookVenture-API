package com.bookVenture.api.service;


import java.util.List;

import com.bookVenture.api.dto.BookDto;
import com.bookVenture.api.dto.BookResponse;

public interface BookService {
    BookDto createBook(BookDto bookDto, long authorId);
    BookResponse getAllBooks(int page, int pageSize);
    List<BookDto> getBookByAuthorId(long id);
    BookDto getBookById(long id, long autorId);
    BookDto updateBook(BookDto bookDto, long bookId, long authorId);
    void deleteBook(long id, long authorId);
}
