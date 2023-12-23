package task3;

import com.google.gson.*;

import java.io.*;
import java.util.Map;
import java.util.Objects;

public class Task3 {
    public static void main(String[] args) {
        try (InputStream valuesFileReader = Task3.class.getResourceAsStream(args[1]);
             InputStream testsFileReader = Task3.class.getResourceAsStream(args[0]);
             OutputStream reportOutputStream = new FileOutputStream("./report.json")) {

            BufferedReader valuesBufferedReader =
                    new BufferedReader(new InputStreamReader(Objects.requireNonNull(valuesFileReader)));
            BufferedReader testsBufferedReader =
                    new BufferedReader(new InputStreamReader(Objects.requireNonNull(testsFileReader)));

            // Чтение данных из файла values.json
            JsonObject valuesJsonObject = JsonParser.parseReader(valuesBufferedReader).getAsJsonObject();

            // Чтение данных из файла tests.json
            JsonElement testsJsonElement = JsonParser.parseReader(testsBufferedReader);

            // Обновление значений полей id и value в файле tests.json
            JsonObject testsJsonObject = testsJsonElement.getAsJsonObject();

            updateValues(testsJsonObject, valuesJsonObject);

            testsJsonObject.add("value", valuesJsonObject.get("value"));

            // Запись обновленных данных в файл tests.json
            try (OutputStreamWriter writer = new OutputStreamWriter(reportOutputStream)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(testsJsonObject, writer);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
    private static void updateValues(JsonObject testsJsonObject, JsonObject valuesJsonObject) {
        for (JsonElement objectValues : valuesJsonObject.get("values").getAsJsonArray()) {
            updateValue(testsJsonObject, objectValues.getAsJsonObject());
        }
    }
    // РАБОТАЕТ, НЕ ТРОГАТЬ
    private static void updateValue(JsonElement jsonElement, JsonObject valueObject) {
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                updateValue(entry.getValue(), valueObject);
            }
            if (jsonObject.has("id")) {
                if (jsonObject.get("id").getAsInt() == valueObject.get("id").getAsInt()) {
                    jsonObject.addProperty("value", valueObject.get("value").getAsString());
                }
            }
        } else if (jsonElement.isJsonArray()) {
            for (JsonElement element : jsonElement.getAsJsonArray()) {
                updateValue(element, valueObject);
            }
        }
    }
}