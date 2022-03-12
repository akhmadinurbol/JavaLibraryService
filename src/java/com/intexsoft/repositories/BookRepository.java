package com.intexsoft.repositories;

import com.intexsoft.models.Book;
import com.intexsoft.repositories.interfaces.*;

import java.io.*;
import java.util.*;

public class BookRepository implements IBookRepository {
    private List<List<String>> allBooks = new ArrayList<>();

    @Override
    public List<Book> findById(int id) {
        List<Book> foundById = new ArrayList<>();

        allBooks.clear();
        for(List<String> books: getAllBooks()){
            if(Objects.equals(books.get(0), String.valueOf(id))){
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
                book.setFilePath(books.get(6));
                foundById.add(book);
            }
        }
        return foundById;
    }

    @Override
    public List<Book> findAll(String author, String name) {
        List<Book> foundBooks = new ArrayList<>();
        author = author == null ? "" : author.toLowerCase();
        name = name == null ? "" : name.toLowerCase();
        boolean checkAuthor = false;
        boolean checkName = false;

        allBooks.clear();
        for(List<String> books: getAllBooks()){
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
                book.setFilePath(books.get(6));
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    private List<List<String>> getAllBooks() {
        String path = "C:\\Users\\admin\\Desktop\\Intexsoft\\Java Library Service\\src\\resource\\libraries";
        allBooks = getAllFiles(new File(path));
        return allBooks;
    }

    private List<List<String>> getAllFiles(File dir){
        try {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        getAllFiles(new File(file.getCanonicalPath()));
                    } else {
                        if(file.getName().contains(".csv")){
                            List<String> values;
                            String line;
                            String splitBy = ",";
                            try {
                                BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
                                while ((line = br.readLine()) != null) {
                                    values = new ArrayList<>(List.of(line.split(splitBy)));
                                    if(values.size() < 4){
                                        values.add("");
                                        values.add("");
                                    }
                                    values.add(file.getParentFile().getName());
                                    values.add(file.getPath());
                                    allBooks.add(values);
                                }

                            } catch(IOException e) {
                                e.printStackTrace();
                            }
                        } else if(file.getName().contains(".properties")){
                            Properties prop = new Properties();
                            try {
                                InputStream inputStream = new FileInputStream(file.getPath());
                                prop.load(inputStream);

                                List<String> textBooks = new ArrayList<>();

                                textBooks.add(prop.getProperty("Index"));
                                textBooks.add(prop.getProperty("Author"));
                                textBooks.add(prop.getProperty("Name"));
                                textBooks.add(prop.getProperty("Issued"));
                                textBooks.add(prop.getProperty("IssuedTo"));
                                textBooks.add(file.getParentFile().getName());
                                textBooks.add(file.getPath());
                                allBooks.add(textBooks);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } else {
                throw new AssertionError();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allBooks;
    }
}
