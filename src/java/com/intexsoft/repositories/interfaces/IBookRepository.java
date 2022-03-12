package com.intexsoft.repositories.interfaces;

import com.intexsoft.models.Book;

import java.util.List;

public interface IBookRepository {
    List<Book> findById(int id);
    List<Book> findAll(String author, String name);
}
