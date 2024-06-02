package com.bookVenture.api.service;


import java.util.List;

import com.bookVenture.api.dto.AuthorDto;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto getAuthorById(long id);
    AuthorDto updateAuthor(long id, AuthorDto authorDto);
    void deleteAuthor(long id);
}
