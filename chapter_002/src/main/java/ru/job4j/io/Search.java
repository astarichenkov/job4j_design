package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("There are not enough arguments to start the program. Usage java -jar Search.jar ROOT_FOLDER EXTENSION.");
        }
        String path = args[0];
        String ext = args[1];
        if (!path.matches("[a-zA-Z]:\\\\(?:[^\\\\/:*?\"<>|\\r\\n]+\\\\)*[^\\\\/:*?\"<>|\\r\\n]*")) {
            throw new IllegalArgumentException("Invalid path. Usage java -jar Search.jar ROOT_FOLDER EXTENSION.");
        }
        if (!ext.matches("\\w+")) {
            throw new IllegalArgumentException("Invalid file extension. Usage java -jar Search.jar ROOT_FOLDER EXTENSION.");
        }
        Path start = Paths.get(path);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }
}