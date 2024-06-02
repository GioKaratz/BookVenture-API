package com.bookVenture.api.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookVenture.api.dto.AuthorDto;
import com.bookVenture.api.exceptions.AuthorNotFoundException;
import com.bookVenture.api.models.Author;
import com.bookVenture.api.repositories.AuthorRepository;
import com.bookVenture.api.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = maptToEntity(authorDto);
        Author newAuthor = authorRepository.save(author);

        return maptToDto(newAuthor);
    }

    @Override
    public AuthorDto getAuthorById(long id) {
        Author author = authorRepository.findById(id)
            .orElseThrow(() -> new AuthorNotFoundException("Author not found."));
        return maptToDto(author);
    }

    @Override
    public AuthorDto updateAuthor(long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id)
            .orElseThrow(() -> new AuthorNotFoundException("Author not found."));
            author.setFirstName(authorDto.getFirstName());
            author.setLastName(authorDto.getLastName());
            author.setNationality(authorDto.getNationality());
            author.setBirthDate(authorDto.getBirthDate());
        Author updatedAuthor = authorRepository.save(author);
        return maptToDto(updatedAuthor);
    }

    @Override
    public void deleteAuthor(long id) {
        Author author = authorRepository.findById(id)
            .orElseThrow(() -> new AuthorNotFoundException("Author not found."));
        authorRepository.delete(author);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
            .map(author -> maptToDto(author))
            .collect(Collectors.toList());
    }

    private AuthorDto maptToDto(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setNationality(author.getNationality());
        authorDto.setBirthDate(author.getBirthDate());
        return authorDto;
    }
    private Author maptToEntity(AuthorDto authorDto){
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setNationality(authorDto.getNationality());
        author.setBirthDate(authorDto.getBirthDate());
        return author;
    }


}
