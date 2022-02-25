package com.intexsoft.repositories.interfaces;

import com.intexsoft.models.Book;

import java.util.List;

public interface IOutputRepository {
    void outputAll(List<Book> books);
}
