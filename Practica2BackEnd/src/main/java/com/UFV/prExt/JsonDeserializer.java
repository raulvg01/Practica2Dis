package com.UFV.prExt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonDeserializer <T>{

    private final Gson gson;

    public JsonDeserializer() throws FileNotFoundException {
        this.gson = new Gson();
    }

    public T deserialize_one(String filePath, Class<T> targetType) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, targetType);
        }
    }

/*
    public List<T> deserialize_list(String filePath, Class<T> targetType) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = TypeToken.getParameterized(List.class, targetType).getType();
            return gson.fromJson(reader, listType);
        }
    }

 */
}

