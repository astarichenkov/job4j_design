package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GeneratorTest {
    @Test
    public void whenKeyExists() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan Ivanov");
        args.put("subject", "you");
        String expected = "I am a Ivan Ivanov, Who are you? ";
        String rsl = generator.produce(template, args);
        assertThat(rsl, is(expected));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenMapNotContainsKey() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan Ivanov");
        generator.produce(template, args);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenMapContainsExtraKeys() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}. ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Ivan Ivanov");
        args.put("subject", "you");
        generator.produce(template, args);
    }
}
