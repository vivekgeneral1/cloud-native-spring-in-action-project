package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.exceptions.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book createBook(Book book) {
        if(bookRepository.existsByIsbn(book.isbn())){
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public Book updateBook(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var toUpdate = new Book(
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price()
                    );
                    return bookRepository.save(toUpdate);
                }).orElseGet(() -> createBook(book));
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }
}
