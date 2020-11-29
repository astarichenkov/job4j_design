package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> result = new ArrayList<>();
        String unavailable = null;
        String available = null;
        String[] current;

        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            while (read.ready()) {
                String s = read.readLine();
                current = s.split(" ");
                if ((current[0].equals("500") || current[0].equals("400")) && unavailable == null) {
                    unavailable = current[1];
                    available = null;
                }
                if ((current[0].equals("200") || current[0].equals("300")) && unavailable != null) {
                    available = current[1];
                }
                if (unavailable != null && available != null) {
                    result.add(String.format("%s;%s", unavailable, available));
                    unavailable = null;
                    available = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            result.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./test_files/server.log", "./test_files/target.csv");
    }
}