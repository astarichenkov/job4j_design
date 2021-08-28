package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private String model;
    @XmlElement
    private Engine engine;
    @XmlElementWrapper(name = "equipments")
    @XmlElement(name = "equipment")
    private String[] equipment;
    @XmlAttribute
    private int mileage;
    @XmlAttribute
    private boolean requiresRepair;

    public Car() {
    }

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

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getEquipment() {
        return equipment;
    }

    public int getMileage() {
        return mileage;
    }

    public boolean isRequiresRepair() {
        return requiresRepair;
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
