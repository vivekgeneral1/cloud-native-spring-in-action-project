package com.polarbookshop.catalogservice;

public record Book(
    String isbn,
    String title,
    String author,
    Double price
) {}
