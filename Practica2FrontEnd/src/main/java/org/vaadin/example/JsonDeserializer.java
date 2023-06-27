package org.vaadin.example;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class JsonDeserializer<T> {
    private final Gson gson;

    public JsonDeserializer() {
        this.gson = new Gson();
    }

    public T deserialize(String filePath, Class<T> targetType) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, targetType);
        }
    }
}
