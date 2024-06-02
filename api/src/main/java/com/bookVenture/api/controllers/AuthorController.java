package com.bookVenture.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookVenture.api.dto.AuthorDto;
import com.bookVenture.api.service.AuthorService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable(value = "id") long id) {
        AuthorDto authorDto = authorService.getAuthorById(id);

        return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }

    @PostMapping("/author/create")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorDto createdAuthorDto = authorService.createAuthor(authorDto);
        
        return new ResponseEntity<>(createdAuthorDto, HttpStatus.CREATED);
    }
    
    @PutMapping("/author/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable(value = "id") long id, 
        @RequestBody AuthorDto authorDto) {
        
        AuthorDto updatedAuthor = authorService.updateAuthor(id, authorDto);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable(value = "id") long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Author Deleted", HttpStatus.OK);
    }
    
    
}
