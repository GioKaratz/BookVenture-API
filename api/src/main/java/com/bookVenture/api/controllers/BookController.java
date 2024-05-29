package com.bookVenture.api.controllers;

import com.bookVenture.api.dto.BookDto;
import com.bookVenture.api.models.Book;
import com.bookVenture.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class BookController {

    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("book")
    public ResponseEntity<List<BookDto>> getBooks(){

        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("book/{id}")
    public Book getBookById(@PathVariable long id){

        Book book = new Book(1, "Davinci Code", "Adventure");

        return book;
    }

    @PostMapping("book/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("book/{id}/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") long id){
        System.out.println(book.getTitle());
        System.out.println(book.getType());

        return ResponseEntity.ok(book);
    }

    @DeleteMapping("book/{id}/delete")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id){
        System.out.println(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
