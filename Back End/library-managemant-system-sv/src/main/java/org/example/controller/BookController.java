package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Book;
import org.example.entity.BookEntity;
import org.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    final BookService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)//Response code becomes 201 when it does create
    public void addBook(@RequestBody Book book){
        service.addBook(book);
    }

    @GetMapping("/getBooks")
    @ResponseStatus(HttpStatus.FOUND)
    public Iterable<BookEntity> getBooks(){
        return service.getBooks();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteBooks(@PathVariable Long id){
        return (service.deleteBook(id)) ?
            ResponseEntity.ok("Deleted Successfully"):
            ResponseEntity.notFound().build();
    }

    @GetMapping("search/{id}")
    public Book searchBookByID(@PathVariable Long id){
        return service.searchBookById(id);
    }
}
