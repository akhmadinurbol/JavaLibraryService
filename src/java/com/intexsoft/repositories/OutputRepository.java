package com.intexsoft.repositories;

import com.intexsoft.models.Book;
import com.intexsoft.repositories.interfaces.IOutputRepository;

import java.util.List;

public class OutputRepository implements IOutputRepository {

    @Override
    public void outputAll(List<Book> books) {
        for(Book book: books){
            if(book.getDateOfIssue() == null && book.getSubscriber() == null){
                System.out.println("FOUND id: " + book.getId() + ", library: " + book.getLibraryName() + "");
            } else if(!book.getDateOfIssue().isEmpty() && !book.getDateOfIssue().isEmpty()){
                System.out.println("FOUNDMISSING id: " + book.getId() + ", library: " + book.getLibraryName() + ", date of issue: " + book.getDateOfIssue() + ".");
            }
        }
        if(books.isEmpty()){
            System.out.println("NOTFOUND");
        }
    }
}
