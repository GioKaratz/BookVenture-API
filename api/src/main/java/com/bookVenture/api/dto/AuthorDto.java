package com.bookVenture.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AuthorDto {
    private long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private Date birthDate;
}
