package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest {
    List<Integer> list;

    @Before
    public void initData() {
        list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(7);
        list.add(5);
    }

    @Test
    public void WhenMaxElementInteger() {
        MaxMin maxMin = new MaxMin();
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer) o1 - (Integer) o2;
            }
        };
        int rsl = maxMin.max(list, comparator);
        Assert.assertEquals(rsl, 7);
    }

    @Test
    public void WhenMaxStringLength() {
        List<String> list = new ArrayList<>();
        list.add("str");
        list.add("length");
        list.add("size");
        MaxMin maxMin = new MaxMin();
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.toString().length() - o2.toString().length();
            }
        };
        String rsl = maxMin.max(list, comparator);
        Assert.assertEquals(rsl, "length");
    }


}
