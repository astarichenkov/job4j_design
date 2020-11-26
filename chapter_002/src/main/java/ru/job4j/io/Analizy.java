package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        List<List<String>> sourceList = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines()
                    .filter(e -> !e.isEmpty())
                    .map(e -> e.split(" "))
                    .forEach(e -> sourceList.add(Arrays.asList(e)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> prevEl = sourceList.get(0);
        List<String> result = new ArrayList<>();
        for (int i = 1; i < sourceList.size(); i++) {
            List targetEl = sourceList.get(i);
            if ((prevEl.get(0).equals("400") || prevEl.get(0).equals("500"))
                    && (targetEl.get(0).equals("200") || targetEl.get(0).equals("300"))) {

                System.out.println(prevEl.get(1) + ";" + targetEl.get(1));
                result.add(prevEl.get(1) + ";" + targetEl.get(1));
                prevEl = sourceList.get(i);
            }
            if (prevEl.get(0).equals("200") || prevEl.get(0).equals("300")) {
                prevEl = sourceList.get(i);
            }
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            result.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "target.csv");
    }
}