package com.intexsoft.repositories.interfaces;

import com.intexsoft.models.Book;

public interface IBookRepository {
    void findBooks(Book book);
    void orderBooks();
    void returnBooks();
}
