package com.bookVenture.api.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private long id;
    private String title;
    private String content;
    private int stars;
//    private Book book;
}
