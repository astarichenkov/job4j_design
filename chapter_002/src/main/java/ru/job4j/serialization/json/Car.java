package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final String model;
    private final Engine engine;
    private final String[] equipment;
    private final int mileage;
    private final boolean requiresRepair;

    public Car(String model, Engine engine, String[] equipment, int mileage, boolean requiresRepair) {
        this.model = model;
        this.engine = engine;
        this.equipment = equipment;
        this.mileage = mileage;
        this.requiresRepair = requiresRepair;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engine=" + engine +
                ", equipment=" + Arrays.toString(equipment) +
                ", mileage=" + mileage +
                ", requiresRepair=" + requiresRepair +
                '}';
    }

    public static void main(String[] args) {
        Car vesta = new Car("Vesta", new Engine("21179", 1.8, 122),
                new String[]{"Conditioner", "ABS", "ESP"}, 154000, false);
        final Gson gson = new GsonBuilder().create();

        String vestaJson = gson.toJson(vesta);
        System.out.println(vestaJson);

        final Car vestaMod = gson.fromJson(vestaJson, Car.class);
        System.out.println(vestaMod);
    }
}
