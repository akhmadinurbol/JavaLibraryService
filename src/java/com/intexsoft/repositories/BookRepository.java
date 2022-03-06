package com.intexsoft.repositories;

import com.intexsoft.models.Book;
import com.intexsoft.repositories.interfaces.*;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookRepository implements IBookRepository {
    private final Scanner scanner = new Scanner(System.in);
    private final ISearchRepository searchRepository = new SearchRepository();

    @Override
    public void findBooks(Book bookInput){
        List<Book> foundBooks = searchRepository.findAll(bookInput);
        if(!foundBooks.isEmpty()){
            for(Book book: foundBooks){
                if(book.getDateOfIssue().isEmpty() && book.getSubscriber().isEmpty()){
                    System.out.println("FOUND id: " + book.getId() + ", library: " + book.getLibraryName() + "");
                } else if(!book.getDateOfIssue().isEmpty() && !book.getDateOfIssue().isEmpty()){
                    System.out.println("FOUNDMISSING id: " + book.getId() + ", library: " + book.getLibraryName() + ", date of issue: " + book.getDateOfIssue() + ".");
                }
            }
        } else{
            System.out.println("NOTFOUND");
        }
    }

    @Override
    public void orderBooks(){
        System.out.println("Example: ORDER id=<index> subscriber=<subscriberName>");
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

        List<Book> foundByIdBooks = searchRepository.findById(input);
        if(foundByIdBooks.isEmpty()){
            System.out.println("NOTFOUND");
        } else {
            for (Book book : foundByIdBooks) {
                if(book.getId() == Integer.parseInt(params.get("id"))){
                    if(book.getDateOfIssue().isEmpty() && book.getSubscriber().isEmpty()){
                        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                        Date date = new Date();

                        book.setSubscriber(params.get("subscriber"));
                        book.setDateOfIssue(dateFormat.format(date));

                        orderBook(book, book.getFilePath());

                        System.out.println("OK Subscriber name:" + book.getSubscriber() +", Date of issue: " + book.getDateOfIssue());
                    } else if(!book.getDateOfIssue().isEmpty() && !book.getSubscriber().isEmpty()){
                        System.out.println("RESERVED Subscriber name:" + book.getName() +", Date of issue: " + book.getDateOfIssue());
                    }
                }
            }
        }
    }

    @Override
    public void returnBooks() {
        System.out.println("Example: RETURN id=<index>");
        Map<String, String> params = new HashMap<>();
        String[] splitParams;

        String[] sc = scanner.nextLine().split(" ");

        if (sc.length > 1){
            splitParams = sc[1].split("=");
            params.put(splitParams[0], splitParams[1]);

            if(!params.containsKey("id")) System.out.println("Please enter correctly id!");
        } else System.out.println("Please enter id of book!");

        Book input = new Book(Integer.parseInt(params.get("id")));

        List<Book> foundByIdBooks = searchRepository.findById(input);
        if(foundByIdBooks.isEmpty()){
            System.out.println("NOTFOUND");
        } else {
            for (Book book : foundByIdBooks) {
                if(book.getId() == Integer.parseInt(params.get("id"))){
                    if(book.getDateOfIssue().equals("") && book.getSubscriber().equals("")){
                        System.out.println("ALREADYRETURNED");
                    } else {
                        System.out.println("OK subscriber: " + book.getSubscriber());

                        book.setSubscriber("");
                        book.setDateOfIssue("");

                        returnBook(book, book.getFilePath());
                    }
                } else System.out.println("Please enter correct id!");
            }
        }
    }

    private void orderBook(Book book, String filePath) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();

        try {
            String id = "";
            String author = "";
            String name = "";
            String dateOfIssue = "";
            String issuedTo = "";

            File temp = File.createTempFile("TempFile", ".txt", new File("C:\\Users\\admin\\Desktop\\Intexsoft\\Java Library Service\\src\\java\\com\\intexsoft"));
            temp.deleteOnExit();

            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            PrintWriter pw = new PrintWriter(out);

            Scanner x = new Scanner(new File(filePath));
            x.useDelimiter(",");

            while (x.hasNext()) {
                id = x.next();
                author = x.next();
                name = x.next();
                if (x.next() == null) {
                    dateOfIssue = x.next();
                    issuedTo = x.next();
                }
                if (Integer.parseInt(id) == book.getId()) {
                    pw.print(id + "," + author + "," + name + "," + dateFormat.format(date) + "," + book.getSubscriber());
                } else {
                    pw.print(id + "," + author + "," + name + "," + dateOfIssue + "," + issuedTo);
                }
            }

            pw.flush();
            pw.close();
            out.close();

            File orig = new File(filePath);

            FileChannel src = new FileInputStream(temp).getChannel();
            FileChannel dest = new FileOutputStream(orig).getChannel();
            dest.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }

    private void returnBook(Book book, String filePath) {
        try {
            String id = "";
            String author = "";
            String name = "";
            String dateOfIssue = "";
            String issuedTo = "";

            File temp = File.createTempFile("TempFile", ".txt", new File("C:\\Users\\admin\\Desktop\\Intexsoft\\Java Library Service\\src\\java\\com\\intexsoft"));
            temp.deleteOnExit();

            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            PrintWriter pw = new PrintWriter(out);

            Scanner x = new Scanner(new File(filePath));
            x.useDelimiter(",");

            while (x.hasNext()) {
                id = x.next();
                author = x.next();
                name = x.next();
                if (x.next() == null) {
                    dateOfIssue = x.next();
                    issuedTo = x.next();
                }
                if (Integer.parseInt(id) == book.getId()) {
                    dateOfIssue = "";
                    issuedTo = "";
                }
                pw.print(id + "," + author + "," + name + "," + dateOfIssue + "," + issuedTo + "");
            }

            pw.flush();
            pw.close();
            out.close();

            File orig = new File(filePath);

            FileChannel src = new FileInputStream(temp).getChannel();
            FileChannel dest = new FileOutputStream(orig).getChannel();
            dest.transferFrom(src, 0, src.size());
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }
}
