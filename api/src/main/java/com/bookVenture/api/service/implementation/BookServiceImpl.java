package com.bookVenture.api.service.implementation;

import com.bookVenture.api.dto.BookDto;
import com.bookVenture.api.models.Book;
import com.bookVenture.api.repositories.BookRepository;
import com.bookVenture.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setType(bookDto.getType());

        Book newBook = bookRepository.save(book);

        BookDto bookResponse = new BookDto();
        bookResponse.setId(newBook.getId());
        bookResponse.setTitle(newBook.getTitle());
        bookResponse.setType(newBook.getType());

        return bookResponse;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> book = bookRepository.findAll();
        return book
                .stream()
                .map(b -> mapToDto(b))
                .collect(Collectors.toList());
    }

    private BookDto mapToDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setType(book.getType());
        return bookDto;
    }

    private Book mapToEntity(BookDto bookdto){
        Book book = new Book();
        book.setId(bookdto.getId());
        book.setTitle(bookdto.getTitle());
        book.setType(bookdto.getType());
        return book;
    }
}
