package ru.job4j.tdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Ignore
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void WhenSessionNotFound() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertTrue(sessions.isEmpty());
    }

    @Test
    public void add() {
        List<Session> sessions = new ArrayList<>();
        Session session = new Session3D();
        sessions.add(session);
        assertThat(session, is(sessions.get(0)));
    }

    @Ignore
    @Test (expected = WrongDateException.class)
    public void whenWrongDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(1999, Calendar.FEBRUARY, 1, 0, 0);
        cinema.buy(account, 1, 1, date);
    }

    @Ignore
    @Test (expected = NonExistentPlaceException.class)
    public void whenNonExistentPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2021, Calendar.NOVEMBER, 10, 23, 0);
        cinema.buy(account, 1, 0, date);
    }

    @Ignore
    @Test (expected = OccupiedPlaceException.class)
    public void whenBuyOccupiedPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        cinema.buy(account, 1, 0, date);
    }
}