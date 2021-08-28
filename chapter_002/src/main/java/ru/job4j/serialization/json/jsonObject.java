package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jsonObject {
    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonEngine = new JSONObject("{\"model\":\"21179\"," +
                "\"volume\":1.8," +
                "\"power\":122}");
        System.out.println(jsonEngine);

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Conditioner");
        list.add("ABS");
        list.add("ESP");
        JSONArray jsonEquipment = new JSONArray(list);
        System.out.println(jsonEquipment);

        /* JSONObject напрямую методом put */
        Car vesta = new Car("Vesta", new Engine("21179", 1.8, 122),
                new String[]{"Conditioner", "ABS", "ESP"}, 154000, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", vesta.getModel());
        jsonObject.put("mileage", vesta.getMileage());
        jsonObject.put("requiresRepair", vesta.isRequiresRepair());
        jsonObject.put("equipment", jsonEquipment);
        jsonObject.put("engine", jsonEngine);

        /* Выведем результат в консоль */
        System.out.println(jsonObject);

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(vesta));
    }
}
