package com.UFV.prExt;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    //Método para leer el archivo JSON
    public static String readFileAsString(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public List<TIA_ZonasBasicas> readJsonZonaBasica(String json) throws FileNotFoundException {

        String JSON = readFileAsString(json);
        JSON = JSON.replace("/", "-");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        DataWrapper dataWrapper = gson.fromJson(JSON, DataWrapper.class);
        List<TIA_ZonasBasicas> listaZonas = dataWrapper.getDataBasicas();

        //System.out.println(listaZonas.get(0).getTodo());
        return listaZonas;

    }

    public List<TIA_ZonasBasicas_Edad> readJsonZonaEdad(String json) throws FileNotFoundException {

        String JSON = readFileAsString(json);
        JSON = JSON.replace("/", "-");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        DataWrapper dataWrapper = gson.fromJson(JSON, DataWrapper.class);
        List<TIA_ZonasBasicas_Edad> listaZonas = dataWrapper.getDataEdad();

        //System.out.println(listaZonas.get(0).getTodo());
        return listaZonas;

    }


}
