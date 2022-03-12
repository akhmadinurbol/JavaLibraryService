package com.intexsoft;

import com.intexsoft.repositories.BookRepository;
import com.intexsoft.service.BookService;

public class Main {

    public static void main(String[] args) {
        MyApplication myApp = new MyApplication(new BookService());
        myApp.start();
    }
}


