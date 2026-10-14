package com.polarbookshop.catalogservice;

import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    void  deleteByIsbn(String isbn);
}
