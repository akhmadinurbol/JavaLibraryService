package com.intexsoft;

import com.intexsoft.service.BookService;

import java.util.*;

public class MyApplication {
    private final BookService bookService;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public void start() {
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
                    bookService.findBooks();
                } else if (option == 2) {
                    bookService.orderBooks();
                } else if (option == 3) {
                    bookService.returnBooks();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("***************************************");
        }
    }
}
