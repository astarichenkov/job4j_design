package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void whenPairWithoutComment() {
        String expected = "";
        try (BufferedReader read = new BufferedReader(new FileReader("../test_files/target.csv"))) {
            while (read.ready()) {
                expected = expected.concat(read.readLine() + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(
                expected,
                is("10:58:01;10:59:01"
                        + System.lineSeparator()
                        + "11:01:02;11:02:02"
                        + System.lineSeparator()
                )
        );
    }
}
