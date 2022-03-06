package com.intexsoft;

import com.intexsoft.repositories.BookRepository;

public class Main {

    public static void main(String[] args) {
        MyApplication myApp = new MyApplication(new BookRepository());
        myApp.start();
    }
}


