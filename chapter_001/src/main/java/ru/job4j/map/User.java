package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Map<User, Object> userMap = new HashMap<>();
        Calendar birthday1 = new GregorianCalendar(2017, Calendar.AUGUST , 25);
        Calendar birthday2 = new GregorianCalendar(2017, Calendar.AUGUST , 25);
        User one = new User("One", 1, birthday1);
        User two = new User("One", 1, birthday2);
        System.out.println(one.hashCode());
        System.out.println(two.hashCode());
        System.out.println(one.equals(two));
        userMap.put(one, new Object());
        userMap.put(two, new Object());
        System.out.println(userMap);
    }
}
