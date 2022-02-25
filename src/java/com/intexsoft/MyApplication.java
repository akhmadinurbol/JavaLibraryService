package com.intexsoft;

import com.intexsoft.models.Book;
import com.intexsoft.repositories.interfaces.IBookRepository;

import java.util.*;

public class MyApplication {
    private final IBookRepository bookRepository;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void start(){
        System.out.println("***************************************");
        while (true) {
            System.out.println("""
                    Select option (1-5):\s
                    1. Find books\s
                    2. Order books\s
                    3. Return books\s
                    4. Exit
                    """);
            try {
                int option = scanner.nextInt();
                if (option == 1) {
                    System.out.println("Example: FIND author=<author> name=<bookName>");
                    Scanner input = new Scanner(System.in);
                    Map<String, String> params = new HashMap<>();
                    String[] splitParams;

                    String[] sc = input.nextLine().split(" ");

                    if (sc.length > 1){
                        splitParams = sc[1].split("=");
                        params.put(splitParams[0], splitParams[1]);
                        if (sc.length == 3){
                            splitParams = sc[2].split("=");
                            params.put(splitParams[0], splitParams[1]);
                        }

                        if(params.containsKey("author") || params.containsKey("name")) bookRepository.findBooks(new Book(params.get("author"), params.get("name")));
                        else System.out.println("Please enter correctly keys name & author!");
                    } else System.out.println("Please enter name or author of book!");


                } else if (option == 2) {
                    bookRepository.orderBooks();
                } else if (option == 3) {
                    bookRepository.returnBooks();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("***************************************");
        }
    }
}
