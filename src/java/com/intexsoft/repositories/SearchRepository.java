package com.intexsoft.repositories;

import com.intexsoft.models.Book;
import com.intexsoft.repositories.interfaces.ISearchRepository;
import com.intexsoft.repositories.interfaces.IFileRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchRepository implements ISearchRepository {
    private final IFileRepository fileRepository = new FileRepository();
    private final File dir = new File("C:\\\\Users\\\\admin\\\\Desktop\\\\Intexsoft\\\\Java Library Service\\\\src\\\\resource\\\\libraries");
    private final List<List<String>> allBooks = fileRepository.getAllFiles(dir);

    @Override
    public List<Book> findById(Book bookInput) {
        List<Book> foundById = new ArrayList<>();

        for(List<String> books: allBooks){
            if(Objects.equals(books.get(0), String.valueOf(bookInput.getId()))){
                Book book = new Book();
                book.setId(Integer.parseInt(books.get(0)));
                book.setAuthor(books.get(1));
                book.setName(books.get(2));
                if(books.size() > 3){
                    book.setDateOfIssue(books.get(3));
                    book.setSubscriber(books.get(4));
                } else {
                    book.setDateOfIssue("");
                    book.setSubscriber("");
                }
                book.setLibraryName(books.get(5));
                foundById.add(book);
            }
        }
        return foundById;
    }

    @Override
    public List<Book> findAll(Book bookInput) {
        List<Book> foundBooks = new ArrayList<>();
        String author = bookInput.getAuthor() == null ? "" : bookInput.getAuthor().toLowerCase();
        String name = bookInput.getName() == null ? "" : bookInput.getName().toLowerCase();
        boolean checkAuthor = false;
        boolean checkName = false;

        for(List<String> books: allBooks){
            if(!author.isEmpty()) checkAuthor = books.get(1).toLowerCase().contains(author);
            if(!name.isEmpty()) checkName = books.get(2).toLowerCase().contains(name);
                if(checkAuthor || checkName){
                        Book book = new Book();
                        book.setId(Integer.parseInt(books.get(0)));
                        book.setAuthor(books.get(1));
                        book.setName(books.get(2));
                        if(books.size() > 3){
                            book.setDateOfIssue(books.get(3));
                            book.setSubscriber(books.get(4));
                        } else {
                            book.setDateOfIssue("");
                            book.setSubscriber("");
                        }
                        book.setLibraryName(books.get(5));
                        foundBooks.add(book);
                }
        }
        return foundBooks;
    }
}
