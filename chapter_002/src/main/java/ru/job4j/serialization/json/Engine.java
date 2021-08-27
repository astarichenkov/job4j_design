package ru.job4j.serialization.json;

public class Engine {
    private final String Model;
    private final double volume;
    private final int power;

    public Engine(String model, double volume, int power) {
        Model = model;
        this.volume = volume;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "Model='" + Model + '\'' +
                ", volume=" + volume +
                ", power=" + power +
                '}';
    }
}
