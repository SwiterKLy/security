package com.switerkly.labs.bookstore;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookRestController {

    private final BookService service;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Book> getBooks() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Book getOneBook(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    public Book create(@RequestBody Book book) {
        return service.create(book);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    public Book update(@RequestBody Book book) {
        return service.update(book);
    }

    @GetMapping("/hello/user")
    @PreAuthorize("hasAuthority('USER')")
    public String helloUser() {
        return "Hello User!";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("hello/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }

    @GetMapping("hello/stranger")
    public String helloStranger() {
        return "Hello Stranger!";
    }
}
