package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest {
    List<Integer> integerList;
    List<String> stringList;
    MaxMin maxMin;
    Comparator<String> stringComparator;
    Comparator<Integer> integerComparator;

    @Before
    public void initData() {
        maxMin = new MaxMin();

        integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(7);
        integerList.add(5);

        stringList = new ArrayList<>();
        stringList.add("str");
        stringList.add("length");
        stringList.add("size");

        stringComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.toString().length() - o2.toString().length();
            }
        };

        integerComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return (Integer) o1 - (Integer) o2;
            }
        };
    }

    @Test
    public void WhenMaxElementInteger() {
        int rsl = maxMin.max(integerList, integerComparator);
        Assert.assertEquals(rsl, 7);
    }

    @Test
    public void WhenMaxStringLength() {
        String rsl = maxMin.max(stringList, stringComparator);
        Assert.assertEquals(rsl, "length");
    }

     @Test
    public void WhenMinElementInteger() {
        int rsl = maxMin.min(integerList, integerComparator);
        Assert.assertEquals(rsl, 1);
    }

    @Test
    public void WhenMinStringLength() {
        String rsl = maxMin.min(stringList, stringComparator);
        Assert.assertEquals(rsl, "str");
    }
}
