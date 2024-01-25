package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Book;
import org.example.entity.BookEntity;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//You can use RequiredArgsConstructor instead of using Autowired annotation
//for dependency injection
public class BookServiceImpl implements BookService {

    final BookRepository repository;
    ModelMapper mapper;
    @Bean
    public void setup(){
        this.mapper=new ModelMapper();
    }
    @Override
    public void addBook(Book book) {
        BookEntity entity = mapper.map(book, BookEntity.class);
        repository.save(entity);
    }

    @Override
    public List<BookEntity> getBooks() {
        return (List<BookEntity>) repository.findAll();
    }

    @Override
    public boolean deleteBook(Long id) {
       if (repository.existsById(id)){
           repository.deleteById(id);
           return true;
       }
       return false;
    }
}
