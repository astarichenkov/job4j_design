package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects\\pom_job4j_old.xml");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        Double fileLength = (double) file.length()/1024;
        System.out.println(String.format("name: %s, size: %.2f kb", file.getAbsolutePath(), fileLength));
    }
}