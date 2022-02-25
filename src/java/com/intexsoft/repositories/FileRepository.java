package com.intexsoft.repositories;

import com.intexsoft.repositories.interfaces.IFileRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileRepository implements IFileRepository {
    private final List<List<String>> allBooks = new ArrayList<>();

    @Override
    public List<List<String>> getAllFiles(File dir){
        try {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        getAllFiles(new File(file.getCanonicalPath()));
                    } else {
                        if(file.getName().contains(".csv")){
                            readCSVBooks(file);
                        } else if(file.getName().contains(".properties")){
                            readTextBooks(file);
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

    @Override
    public List<List<String>> readTextBooks(File file){
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
            allBooks.add(textBooks);
        } catch (Exception e){
            e.printStackTrace();
        }
        return allBooks;
    }

    @Override
    public List<List<String>> readCSVBooks(File file){
        List<String> values;
        String line = "";
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
                allBooks.add(values);
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
        return allBooks;
    }
}
