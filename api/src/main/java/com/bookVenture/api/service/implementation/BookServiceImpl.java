package com.bookVenture.api.service.implementation;

import com.bookVenture.api.dto.BookDto;
import com.bookVenture.api.dto.BookResponse;
import com.bookVenture.api.exceptions.AuthorNotFoundException;
import com.bookVenture.api.exceptions.BookNotFoundException;
import com.bookVenture.api.models.Author;
import com.bookVenture.api.models.Book;
import com.bookVenture.api.repositories.AuthorRepository;
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
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDto createBook(BookDto bookDto, long authorId) {
        Book book = mapToEntity(bookDto);
        Author author = authorRepository.findById(authorId)
            .orElseThrow(() -> new AuthorNotFoundException("Author with associated books not found."));
        
        book.setAuthor(author);

        Book newBook = bookRepository.save(book);

        return mapToDto(newBook);
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
    public List<BookDto> getBookByAuthorId(long id) {
        List<Book> books = bookRepository.findByAuthorId(id);

        return books.stream()
            .map(book -> mapToDto(book))
            .collect(Collectors.toList());
    }
    
    @Override
    public BookDto getBookById(long id, long authorId) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book could not be found."));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with associated books not found."));
        if(book.getAuthor().getId() != author.getId()){
            throw new BookNotFoundException("This book does not belong to an author");
        }
        return mapToDto(book);
    }

    @Override
    public BookDto updateBook(BookDto bookDto, long bookId, long authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with associated books not found."));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book could not be found."));
        if(book.getAuthor().getId() != author.getId()){
            throw new BookNotFoundException("This book does not belong to an author");
        }

        book.setType(bookDto.getType());
        book.setTitle(bookDto.getTitle());

        Book updatedBook = bookRepository.save(book);

        return mapToDto(updatedBook);
    }

    @Override
    public void deleteBook(long id, long authorId) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book could not be found."));
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with associated books not found."));
        if(book.getAuthor().getId() != author.getId()){
            throw new BookNotFoundException("This book does not belong to an author");
        }

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
