package com.intexsoft.repositories;

import com.intexsoft.models.Book;
import com.intexsoft.repositories.interfaces.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookRepository implements IBookRepository {
    private final Scanner scanner = new Scanner(System.in);
    private final ISearchRepository searchRepository = new SearchRepository();
    private final IOutputRepository outputRepository = new OutputRepository();

    @Override
    public List<Book> findBooks(Book bookInput){
        List<Book> allBooks = new ArrayList<>(searchRepository.findAll(bookInput));
        outputRepository.outputAll(allBooks);
        return allBooks;
    }

    @Override
    public List<Book> findById(Book bookInput){
        return new ArrayList<>(searchRepository.findById(bookInput));
    }

    @Override
    public void orderBooks(){
        System.out.println("Example: ORDER id=<index> subscriber=<subscriberName>");
        Map<String, String> params = new HashMap<>();
        String[] splitParams;

        String[] sc = scanner.nextLine().split(" ");

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();

        if (sc.length > 1){
            splitParams = sc[1].split("=");
            params.put(splitParams[0], splitParams[1]);
            if (sc.length == 3){
                splitParams = sc[2].split("=");
                params.put(splitParams[0], splitParams[1]);
            }

            if(!params.containsKey("id") || !params.containsKey("subscriber")) System.out.println("Please enter correctly keys id & subscriber!");
        } else System.out.println("Please enter name or author of book!");

        Book input = new Book(Integer.parseInt(params.get("id")),params.get("subscriber"));

        for (Book book : findById(input)) {
            if(book.getId() == Integer.parseInt(params.get("id"))){
                if(book.getDateOfIssue() == null && book.getSubscriber() == null){
                    book.setSubscriber(params.get("subscriber"));
                    book.setDateOfIssue(dateFormat.format(date));
                    System.out.println("OK Subscriber name:" + book.getSubscriber() +", Date of issue: " + book.getDateOfIssue() + ".");
                } else if(book.getDateOfIssue() != null && book.getSubscriber() != null){
                    System.out.println("RESERVED Subscriber name:" + book.getName() +", Date of issue: " + book.getDateOfIssue() + ".");
                }
            }
        }
    }

    @Override
    public void returnBooks() {
        System.out.println("Example: RETURN id=<index> subscriber=<subscriberName>");
        Map<String, String> params = new HashMap<>();
        String[] splitParams;

        String[] sc = scanner.nextLine().split(" ");

        if (sc.length > 1){
            splitParams = sc[1].split("=");
            params.put(splitParams[0], splitParams[1]);
            if (sc.length == 3){
                splitParams = sc[2].split("=");
                params.put(splitParams[0], splitParams[1]);
            }

            if(!params.containsKey("id") || !params.containsKey("subscriber")) System.out.println("Please enter correctly keys id & subscriber!");
        } else System.out.println("Please enter name or author of book!");

        Book input = new Book(Integer.parseInt(params.get("id")),params.get("subscriber"));

        for (Book book : findById(input)) {
            if(book.getId() == Integer.parseInt(params.get("id"))){
                if(book.getDateOfIssue() == null && book.getSubscriber() == null){
                    System.out.println("ALREADYRETURNED");
                }
                if(book.getDateOfIssue() != null && book.getSubscriber() != null){
                    if (Objects.equals(book.getSubscriber().toLowerCase(), params.get("subscriber").toLowerCase())){
                        book.setDateOfIssue("");
                        book.setSubscriber("");
                        System.out.println("OK subscriber: " + params.get("subscriber"));
                    } else System.out.println("Please check your name entered correctly!");
                }
            } else System.out.println("Please enter correct id!");
        }
    }
}
