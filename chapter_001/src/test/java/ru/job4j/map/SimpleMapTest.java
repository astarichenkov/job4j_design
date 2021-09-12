package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class SimpleMapTest {

    Map<String, Integer> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
    }


    @Test
    public void whenPutToEmptyMap() {
        Assert.assertTrue(map.put("KING", 100));
    }

    @Test
    public void whenPutDuplicateKey() {
        map.put("KING", 100);
        Assert.assertFalse(map.put("KING", 100));
    }

    @Test
    public void whenGetKey() {
        map.put("KING", 100);
        int value = map.get("KING");
        Assert.assertEquals(value, 100);
    }

    @Test
    public void whenGetFromEmptyMap() {
        Assert.assertNull(map.get("KING"));
    }

    @Test
    public void whenRemoveKey() {
        map.put("KING", 100);
        map.remove("KING");
        Assert.assertTrue(map.remove("KING"));
    }

    @Test
    public void whenRemoveFromEmptyMap() {
        Assert.assertFalse(map.remove("KING"));
    }

    @Test
    public void whenIteratorNext() {
        map.put("KING", 100);
        Iterator<String> iterator = map.iterator();
        String value = iterator.next();
        Assert.assertEquals(value, "KING");
    }

    @Test
    public void whenIteratorNotHasNext() {
        map.put("KING", 100);
        Iterator<String> iterator = map.iterator();
        iterator.next();
        Assert.assertFalse(iterator.hasNext());
    }
}
