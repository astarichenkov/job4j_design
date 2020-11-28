package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            Double fileLength = (double) subfile.length();
            System.out.println(String.format("name: %s, size: %.2f kb", subfile.getName(), fileLength));
        }
    }
}


//package ru.job4j.io;
//
//import java.io.File;
//
//public class Dir {
//    public static void main(String[] args) {
//        File file = new File("c:\\projects\\pom_job4j_old.xml");
//        if (!file.exists()) {
//            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
//        }
//        Double fileLength = (double) file.length()/1024;
//        System.out.println(String.format("name: %s, size: %.2f kb", file.getAbsolutePath(), fileLength));
//    }
//}