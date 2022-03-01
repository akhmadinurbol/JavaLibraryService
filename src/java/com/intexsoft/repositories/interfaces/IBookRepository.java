package com.intexsoft.repositories.interfaces;

import com.intexsoft.models.Book;

import java.util.List;

public interface IBookRepository {
    void findBooks(Book book);
    List<Book> findById(Book book);
    void orderBooks();
    void returnBooks();
}
