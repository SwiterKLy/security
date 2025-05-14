package com.switerkly.labs.bookstore;

import com.switerkly.labs.security.SecurityConfig;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final SecurityConfig securityConfig;

    private List<Book> books;

    @PostConstruct
    void init() {
        books.add(new Book(
                "Book Title 1",             // title
                "Author 1",                 // author
                "ISBN-001",                 // isbn
                "Publisher 1",              // publisher
                2020,                       // publishedYear
                "Fiction",                  // genre
                320,                        // pagesCount
                "Book Description 1"        // description
        ));

        books.add(new Book(
                "Book Title 2",
                "Author 2",
                "ISBN-002",
                "Publisher 2",
                2021,
                "Non-fiction",
                280,
                "Book Description 2"
        ));

        books.add(new Book(
                "Book Title 3",
                "Author 3",
                "ISBN-003",
                "Publisher 3",
                2022,
                "Science",
                350,
                "Book Description 3"
        ));

        repository.saveAll(books);
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Book create(Book book) {
        return repository.save(book);
    }

    public Book update(Book book) {
        return repository.save(book);
    }
}
