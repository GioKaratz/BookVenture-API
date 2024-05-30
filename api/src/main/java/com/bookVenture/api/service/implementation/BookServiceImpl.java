package com.bookVenture.api.service.implementation;

import com.bookVenture.api.dto.BookDto;
import com.bookVenture.api.dto.BookResponse;
import com.bookVenture.api.exceptions.BookNotFoundException;
import com.bookVenture.api.models.Book;
import com.bookVenture.api.repositories.BookRepository;
import com.bookVenture.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public BookResponse getAllBooks(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Book> books = bookRepository.findAll(pageable);

        List<Book> listOfBooks = books.getContent();

        List<BookDto> bookDtos = listOfBooks
                .stream()
                .map(b -> mapToDto(b))
                .collect(Collectors.toList());
        BookResponse bookResponse = new BookResponse();
        bookResponse.setContent(bookDtos);
        bookResponse.setPageNo(books.getNumber());
        bookResponse.setPageSize(books.getSize());
        bookResponse.setTotalElement(books.getTotalElements());
        bookResponse.setTotalPages(books.getTotalPages());
        bookResponse.setLast(books.isLast());

        return bookResponse;
    }

    @Override
    public BookDto getBookById(long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book could not be found."));
        return mapToDto(book);
    }

    @Override
    public BookDto updateBook(BookDto bookDto, long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book could not be found."));
        book.setType(bookDto.getType());
        book.setTitle(book.getTitle());

        Book updatedBook = bookRepository.save(book);

        return mapToDto(updatedBook);
    }

    @Override
    public void deleteBook(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book could not be found."));
        bookRepository.delete(book);
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
