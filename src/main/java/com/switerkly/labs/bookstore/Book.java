package com.switerkly.labs.bookstore;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document
public class Book extends AuditMetaData {

    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int publishedYear;
    private String genre;
    private int pagesCount;
    private String description;

    public Book(String title, String author, String isbn, String publisher,
                int publishedYear, String genre, int pagesCount, String description) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.pagesCount = pagesCount;
        this.description = description;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return getId().equals(book.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
