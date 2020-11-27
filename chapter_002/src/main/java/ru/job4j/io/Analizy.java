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
            while (read.ready()) {
                String s = read.readLine();
                String[] strings = s.split(" ");
                sourceList.add(Arrays.asList(strings));
            }
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
            }
            prevEl = sourceList.get(i);
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