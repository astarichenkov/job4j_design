package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<String>> allMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        File file = path.toFile();
        FileProperty fileProperty = new FileProperty(file.getTotalSpace(), file.getName());
        if (allMap.containsKey(fileProperty)) {
            List<String> list = allMap.get(fileProperty);
            list.add(file.getAbsolutePath());
            allMap.replace(fileProperty, list);
        } else {
            allMap.put(fileProperty, new ArrayList<>(List.of(file.getAbsolutePath())));
        }
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<String>> entry : allMap.entrySet()) {
            List<String> list = entry.getValue();
            if (list.size() > 1) {
                list.forEach(System.out::println);
                System.out.println();
            }
        }
    }
}