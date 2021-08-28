package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {
    @XmlAttribute
    private String model;
    @XmlAttribute
    private double volume;
    @XmlAttribute
    private int power;

    public Engine() {
    }

    public Engine(String model, double volume, int power) {
        this.model = model;
        this.volume = volume;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "model='" + model + '\'' +
                ", volume=" + volume +
                ", power=" + power +
                '}';
    }
}
