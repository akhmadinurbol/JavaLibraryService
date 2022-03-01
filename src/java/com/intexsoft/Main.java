package com.intexsoft;

import com.intexsoft.repositories.BookRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    private static Scanner x;

    public static void main(String[] args) {
        MyApplication myApp = new MyApplication(new BookRepository());
//        myApp.start();

        String filePath = "C:\\Users\\admin\\Desktop\\Intexsoft\\Java Library Service\\src\\resource\\libraries\\Karbysheva\\B.csv";
        String newId = "1004";
        String newDateOfIssue = "2022.02.25";
        String newIssuedTo = "Nurbol";

        editCSVFile(filePath, newId, newDateOfIssue, newIssuedTo);
    }

    private static void editCSVFile(String filePath, String newId, String newDateOfIssue, String newIssuedTo) {

        String fileTemp = "C:\\Users\\admin\\Desktop\\Intexsoft\\Java Library Service\\src\\resource\\libraries\\Karbysheva\\C.csv";
        File oldFile = new File(filePath);
        File newFile = new File(fileTemp);
        String id = "";
        String author = "";
        String name = "";
        String dateOfIssue = "";
        String issuedTo = "";
        try {
            FileWriter fw = new FileWriter(fileTemp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File(filePath));
            x.useDelimiter(",");

            while (x.hasNext()){

                id = x.next();
                author = x.next();
                name = x.next();
                if (x.next() == null){
                    dateOfIssue = x.next();
                    issuedTo = x.next();
                }
                if(id.equals(newId)){
                    pw.print(id + "," + author + "," + name + "," + newDateOfIssue + "," + newIssuedTo);
                } else {
                    pw.print(id + "," + author + "," + name + "," + dateOfIssue + "," + issuedTo);
                }
            }

            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filePath);
            newFile.renameTo(dump);
        } catch (Exception e){
            System.out.println("Error!");
        }
    }
}
/*
 1004,Asimov,Foundation,,
 1005,Bulgakov,Margaritha,,
 1006,Bulgakov,SobachjeSerdce,,
 1007,Oreilly,ThinkingInJava,,
*/


