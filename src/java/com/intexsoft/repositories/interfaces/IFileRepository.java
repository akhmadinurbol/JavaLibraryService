package com.intexsoft.repositories.interfaces;

import java.io.File;
import java.util.List;

public interface IFileRepository {
    List<List<String>> getAllFiles(File dir);
    List<List<String>> readTextBooks(File file);
    List<List<String>> readCSVBooks(File file);
}
