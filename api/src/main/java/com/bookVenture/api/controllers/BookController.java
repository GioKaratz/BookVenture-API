package com.bookVenture.api.controllers;

import com.bookVenture.api.dto.BookDto;
import com.bookVenture.api.dto.BookResponse;
import com.bookVenture.api.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/")
public class BookController {

    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("book")
    public ResponseEntity<BookResponse> getBooks(
            @RequestParam(value = "page", defaultValue = "0", required = false)int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize
    ){

        return new ResponseEntity<>(bookService.getAllBooks(page, pageSize), HttpStatus.OK);
    }

    @GetMapping("book/{authorId}/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(value = "id") long id,
                                               @PathVariable(value = "authorId") long authorId){

        return ResponseEntity.ok(bookService.getBookById(id, authorId));
    }

    @GetMapping("book/{authorId}")
    public ResponseEntity<List<BookDto>> getBooksByAuthorId(@PathVariable(value = "authorId") long authorId){

        return ResponseEntity.ok(bookService.getBookByAuthorId(authorId));
    }
    

    @PostMapping("book/create/{authorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDto> createBook(@PathVariable(value = "authorId") long authorId,
        @RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createBook(bookDto, authorId), HttpStatus.CREATED);
    }

    @PutMapping("book/{id}/update/{authorId}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") long id,
        @RequestBody BookDto bookDto, @PathVariable(value = "authorId") long authorId){
        BookDto response = bookService.updateBook(bookDto, id, authorId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("book/{id}/delete/{authorId}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id, 
        @PathVariable(value = "authorId") long authorId){
        bookService.deleteBook(id, authorId);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }
}
