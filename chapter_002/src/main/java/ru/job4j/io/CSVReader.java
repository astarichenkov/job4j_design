package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class CSVReader {
    private static Path path;
    private static String delimiter;
    private static String out;
    private static String[] filter;
    private static List<String> headers = new ArrayList<>();
    private static final List<Map<String, String>> scannedList = new ArrayList<>();
    private static final String fileRegex = ".*\\.+.*";

    private static void checkArgs(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("There are not enough arguments to start the program." +
                    " Usage java -jar csvReader.jar -path=SOURCE_FILE -delimiter=DELIMITER -out=TARGET_FILE " +
                    "-filter=COLUMNS.");
        }
        path = Paths.get(argsName.get("path"));
        delimiter = argsName.get("delimiter");
        out = argsName.get("out");
        filter = argsName.get("filter").split(",");

        if (!path.toString().matches(fileRegex)) {
            throw new IllegalArgumentException("Invalid path.");
        }
        if (!delimiter.matches(";") && !delimiter.matches(",")) {
            throw new IllegalArgumentException("Invalid delimiter. Use ';' or ',' ");
        }
        if (!out.equals("stdout") && !out.matches(fileRegex)) {
            throw new IllegalArgumentException("Invalid output. Use stdout or filename.");
        }
        if (filter.length == 0) {
            throw new IllegalArgumentException("No filter is set for output.");
        }
    }

    private static void scanData() throws IOException {
        var scanner = new Scanner(path).useDelimiter(delimiter + "|\\r\\n");
        if (scanner.hasNext()) {
            headers = List.of(scanner.nextLine().split(delimiter));
        }
        while (scanner.hasNext()) {
            Map<String, String> map = new LinkedHashMap<>();
            for (String head : headers) {
                if (scanner.hasNext()) {
                    map.put(head, scanner.next());
                }
            }
            scannedList.add(map);
        }
    }

    private static void stdOut() {
        for (Map<String, String> m : scannedList) {
            for (int i = 0; i < filter.length; i++) {
                System.out.print(m.get(filter[i]) + " ");
            }
            System.out.println();
        }
    }

    private static void outToFile() {
        try (FileOutputStream fos = new FileOutputStream(out)) {
            StringBuilder s = new StringBuilder();
            for (Map<String, String> m : scannedList) {
                for (int i = 0; i < filter.length; i++) {
                    s.append(m.get(filter[i])).append(" ");
                }
                s.append(System.lineSeparator());
                fos.write(s.toString().getBytes());
                s.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outputData() {
        if (out.equals("stdout")) {
            stdOut();
        } else {
            outToFile();
        }
    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        scanData();
        outputData();
    }
}
