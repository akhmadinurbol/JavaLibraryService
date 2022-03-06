package com.intexsoft.models;

public class Book {
    private int id;
    private String author;
    private String name;
    private String dateOfIssue;
    private String subscriber;
    private String libraryName;
    private String filePath;

    public Book() {
    }

    public Book(int id) {
        this.id = id;
    }

    public Book(String author, String name) {
        this.author = author;
        this.name = name;
    }

    public Book(int id, String subscriber) {
        this.id = id;
        this.subscriber = subscriber;
    }

    public Book(int id, String author, String name, String dateOfIssue, String subscriber, String libraryName) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.dateOfIssue = dateOfIssue;
        this.subscriber = subscriber;
        this.libraryName = libraryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
