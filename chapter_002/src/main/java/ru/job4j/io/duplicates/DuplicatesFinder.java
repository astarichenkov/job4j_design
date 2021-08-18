package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        Map<FileProperty, ArrayList<String>> map = duplicatesVisitor.getAllMap();
        for (Map.Entry<FileProperty, ArrayList<String>> entry : map.entrySet()) {
            ArrayList<String> list = entry.getValue();
            list.forEach(System.out::println);
            System.out.println();
        }
    }
}