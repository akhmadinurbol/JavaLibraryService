package com.intexsoft;

import com.intexsoft.repositories.BookRepository;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MyApplication myApp = new MyApplication(new BookRepository());
//        myApp.start();

        String filePath = "C:\\Users\\admin\\Desktop\\Intexsoft\\Java Library Service\\src\\resource\\libraries\\Karbysheva\\B.csv";
        String fileTemp = "C:\\Users\\admin\\Desktop\\Intexsoft\\Java Library Service\\src\\resource\\libraries\\Karbysheva\\B.csv";
        String editId = "1004";
        String editDateOfIssue = "2022.02.25";
        String editIssuedTo = "Nurbol";

        File oldFile = new File(filePath);
        File newFile = new File("C:\\Users\\admin\\Desktop\\Intexsoft\\Java Library Service\\src\\resource\\libraries\\Karbysheva");
        String id = "";
        String author = "";
        String name = "";
        String dateOfIssue = "";
        String issuedTo = "";
        try {
            FileWriter fw = new FileWriter(fileTemp, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Scanner x = new Scanner(new File(filePath));
            x.useDelimiter(",");

            while (x.hasNext()){

                id = x.next();
                author = x.next();
                name = x.next();
                if (x.next() == null){
                    dateOfIssue = x.next();
                    issuedTo = x.next();
                }
                if(id.equals(editId)){
                    pw.print(id + "," + author + "," + name + "," + editDateOfIssue + "," + editIssuedTo);
                } else {
                    pw.print(id + "," + author + "," + name + "," + dateOfIssue + "," + issuedTo);
                }
            }

            x.close();
            pw.flush();
            pw.close();
//            oldFile.delete();
//            File dump = new File(filePath);
//            newFile.renameTo(dump);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
