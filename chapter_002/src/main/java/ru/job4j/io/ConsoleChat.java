package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String s;
        boolean isRun = true;
        System.out.print("Введите команду: ");
        while (!OUT.equals(s = in.nextLine())) {
            if (CONTINUE.equals(s)) {
                isRun = true;
                String ans = getRandomString(answers);
                System.out.println(ans);
                log.add(s);
                log.add(ans);
                continue;
            }
            if (STOP.equals(s)) {
                isRun = false;
                log.add(s);
                continue;
            }
            if (isRun) {
                String ans = getRandomString(answers);
                System.out.println(ans);
                log.add(s);
                log.add(ans);
            }
        }
        log.add(s);
        saveLog(log);
    }

    private String getRandomString(List<String> answers) {
        return answers.get((int) (Math.random() * (answers.size() - 1)));
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("botAnswers.txt", "onegin.txt");
        cc.run();
    }
}