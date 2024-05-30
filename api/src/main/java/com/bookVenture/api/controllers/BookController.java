package com.bookVenture.api.controllers;

import com.bookVenture.api.dto.BookDto;
import com.bookVenture.api.dto.BookResponse;
import com.bookVenture.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("book/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable long id){


        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("book/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("book/{id}/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable("id") long id){
        BookDto response = bookService.updateBook(bookDto, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("book/{id}/delete")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }
}
