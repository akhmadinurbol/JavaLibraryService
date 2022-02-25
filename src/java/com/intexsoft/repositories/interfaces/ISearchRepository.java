package com.intexsoft.repositories.interfaces;

import com.intexsoft.models.Book;

import java.util.List;

public interface ISearchRepository {
    List<Book> findById(Book book);
    List<Book> findAll(Book book);
}
