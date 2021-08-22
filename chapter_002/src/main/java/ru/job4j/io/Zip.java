package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static String excluded;
    private static Path path;
    private static File target;

    private static final Predicate<Path> condition = p -> {
        String name = p.toFile().getName();
        return !name.equals(excluded);
    };

    public static void packFiles(List<File> sources, File target) {
        try (FileOutputStream fos = new FileOutputStream(target);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            for (File fileName : sources) {
                try (FileInputStream fis = new FileInputStream(fileName)) {
                    ZipEntry zipEntry = new ZipEntry(String.valueOf(fileName));
                    zipOut.putNextEntry(zipEntry);
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteFileIfExist(File target) {
        if (target.exists()) {
            try {
                Files.delete(target.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkArgs(String[] args) throws FileNotFoundException {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        if (args.length != 3) {
            throw new IllegalArgumentException("There are not enough arguments to start the program." +
                    " Usage java -jar pack.jar -d=FOLDER -e=EXCLUDED -o=TARGET_FILE.");
        }
        excluded = argsName.get("e");
        path = Paths.get(argsName.get("d"));
        target = new File(argsName.get("o"));

        if (Files.notExists(target.toPath())) {
            throw new FileNotFoundException("Target file or directory not found");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        checkArgs(args);
        deleteFileIfExist(target);
        List<Path> paths = Search.search(path, condition);
        List<File> files = new ArrayList<>();
        paths.forEach(p -> files.add(p.toFile()));
        packFiles(files, target);
    }
}