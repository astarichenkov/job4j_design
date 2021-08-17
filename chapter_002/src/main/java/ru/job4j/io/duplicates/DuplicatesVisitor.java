package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, ArrayList<String>> allMap = new HashMap<>();
    private final Map<FileProperty, ArrayList<String>> duplicateMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        File file = path.toFile();
        FileProperty fileProperty = new FileProperty(file.getTotalSpace(), file.getName());
        if (allMap.containsKey(fileProperty)) {
            ArrayList<String> list = allMap.get(fileProperty);
            list.add(file.getAbsolutePath());
            if (!duplicateMap.containsKey(fileProperty)) {
                duplicateMap.put(fileProperty, list);
            } else {
                duplicateMap.replace(fileProperty, list);
            }
            allMap.replace(fileProperty, list);
        } else {
            allMap.put(fileProperty, new ArrayList<>(List.of(file.getAbsolutePath())));
        }
        return FileVisitResult.CONTINUE;
    }

    public Map<FileProperty, ArrayList<String>> getAllMap() {
        return duplicateMap;
    }
}