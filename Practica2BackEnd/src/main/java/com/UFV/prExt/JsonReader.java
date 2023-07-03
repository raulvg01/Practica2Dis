package com.UFV.prExt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public ArrayList<TIA_ZonasBasicas> readJsonZonaBasica(String json) throws FileNotFoundException ,ParseException {


        String JSON = readFileAsString(json);
        JSON = JSON.replace("/", "-");


// Utilizar Jackson para analizar el JSON
        ObjectMapper objectMapperData = new ObjectMapper();
        ArrayList<TIA_ZonasBasicas> objetos;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<TIA_ZonasBasicas> listaZonasBasicas = new ArrayList<>();

        try {
            // Convertir el JSON en un objeto Java Map
            Map<String, List<Map<String, Object>>> jsonData = objectMapperData.readValue(JSON, new TypeReference<Map<String, List<Map<String, Object>>>>() {
            });

            // Obtener la lista de objetos del campo "data"
            List<Map<String, Object>> data = jsonData.get("data");

            // Crear un ArrayList para almacenar los objetos
            objetos = new ArrayList<>();
            int i = 0;
            int j = 0;
            int id = 0;

            // Recorrer la lista de objetos y guardarlos en el ArrayList
            for (Map<String, Object> objeto : data) {
              //  i++;
                TIA_ZonasBasicas auxZonaBasica = new TIA_ZonasBasicas();
                auxZonaBasica.setCodigo_geometria((String) objeto.get("codigo_geometria"));
                auxZonaBasica.setZona_basica_salud((String) objeto.get("zona_basica_salud"));
                auxZonaBasica.setId(id);
                id++;

                // Convertir los campos de double a float
                double tasaUltimos14dias = (Double) objeto.get("tasa_incidencia_acumulada_ultimos_14dias");
                float tasaUltimos14diasFloat = (float) tasaUltimos14dias;
                auxZonaBasica.setTasa_incidencia_acumulada_ultimos_14dias(tasaUltimos14diasFloat);

                double tasaTotal = (Double) objeto.get("tasa_incidencia_acumulada_total");
                float tasaTotalFloat = (float) tasaTotal;
                auxZonaBasica.setTasa_incidencia_acumulada_total(tasaTotalFloat);



                if (objeto.get("casos_confirmados_totales") != null)
                    auxZonaBasica.setCasos_confirmados_totales((Integer) objeto.get("casos_confirmados_totales"));
                else{
                    auxZonaBasica.setCasos_confirmados_totales(0);
                    j++;
                }

                //System.out.println("Id = "+ i + "AAAAAA" + objeto.get("casos_confirmados_totales"));
                if (objeto.get("casos_confirmados_ultimos_14dias") != null)
                    auxZonaBasica.setCasos_confirmados_ultimos_14dias((Integer) objeto.get("casos_confirmados_ultimos_14dias"));
                else{
                    auxZonaBasica.setCasos_confirmados_ultimos_14dias(0);
                    i++;
                }

                auxZonaBasica.setFecha_informe(dateFormat.parse((String) objeto.get("fecha_informe")));

                listaZonasBasicas.add(auxZonaBasica);
            }

            //System.out.println("Numero de zonas14 null: " + i);
            //System.out.println("Numero de zonastotal null: " + j);
            //System.out.println("Numero de zonas: " + listaZonasBasicas.size());
            //System.out.println(listaZonasBasicas.get(0).getTodo());

            // Hacer uso de la lista de objetos TIA_ZonasBasicas según tus necesidades

        } catch (IOException e) {
            e.printStackTrace();
        }


        return listaZonasBasicas;
    }

    public List<TIA_ZonasBasicas_Edad> readJsonZonaEdad(String json) throws FileNotFoundException {


        String JSON = readFileAsString(json);
        JSON = JSON.replace("/", "-");


// Utilizar Jackson para analizar el JSON
        ObjectMapper objectMapperData = new ObjectMapper();
        ArrayList<TIA_ZonasBasicas_Edad> objetos;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<TIA_ZonasBasicas_Edad> listaZonasEdad = new ArrayList<>();

        try {
            // Convertir el JSON en un objeto Java Map
            Map<String, List<Map<String, Object>>> jsonData = objectMapperData.readValue(JSON, new TypeReference<Map<String, List<Map<String, Object>>>>() {
            });

            // Obtener la lista de objetos del campo "data"
            List<Map<String, Object>> data = jsonData.get("data");

            // Crear un ArrayList para almacenar los objetos
            objetos = new ArrayList<>();
            int i = 0;
            int j = 0;
            int id = 0;

            // Recorrer la lista de objetos y guardarlos en el ArrayList
            for (Map<String, Object> objeto : data) {
                //  i++;
                TIA_ZonasBasicas_Edad auxZonaEdad = new TIA_ZonasBasicas_Edad();
                auxZonaEdad.setCodigo_geometria((String) objeto.get("codigo_geometria"));
                auxZonaEdad.setZona_basica_salud((String) objeto.get("zona_basica_salud"));
                auxZonaEdad.setId(id);
                id++;

               // System.out.println(objeto.get("tasa_incidencia_acumulada_P60mas_ultimos_14dias"));

                if (objeto.get("tasa_incidencia_acumulada_P60mas_ultimos_14dias") != null) {
                    // Convertir los campos de double a float
                    double tasaUltimos14dias = (Double) objeto.get("tasa_incidencia_acumulada_P60mas_ultimos_14dias");
                    float tasaUltimos14diasFloat = (float) tasaUltimos14dias;
                    auxZonaEdad.setTasa_incidencia_acumulada_P60mas_ultimos_14dias(tasaUltimos14diasFloat);
                }else{
                    auxZonaEdad.setTasa_incidencia_acumulada_P60mas_ultimos_14dias(0.0f);
                    j++;
                }

                if (objeto.get("casos_confirmados_P60mas_ultimos_14dias") != null)
                    auxZonaEdad.setCasos_confirmados_P60mas_ultimos_14dias((Integer) objeto.get("casos_confirmados_P60mas_ultimos_14dias"));
                else{
                    auxZonaEdad.setCasos_confirmados_P60mas_ultimos_14dias(0);
                    j++;
                }

                //System.out.println("Id = "+ i + "AAAAAA" + objeto.get("casos_confirmados_totales"));


                auxZonaEdad.setFecha_informe(dateFormat.parse((String) objeto.get("fecha_informe")));

                listaZonasEdad.add(auxZonaEdad);
            }

            System.out.println("Numero de casos null: " + i);
            System.out.println("Numero de tasa null: " + j);
            System.out.println("Numero de zonas: " + listaZonasEdad.size());
            //System.out.println(listaZonasBasicas.get(0).getTodo());

            // Hacer uso de la lista de objetos TIA_ZonasBasicas según tus necesidades

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return listaZonasEdad;
    }


    public void writeJsonBasicas(List<TIA_ZonasBasicas> zonaBasica, String json) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        //FileWriter writer = new FileWriter(json);
        //JsonWriter jsonWriter = new JsonWriter(writer);
        //jsonWriter.setIndent("  ");
        JsonArray jsonArray = gson.toJsonTree(zonaBasica).getAsJsonArray();
        System.out.println(jsonArray.toString());
        //gson.toJson(jsonArray, jsonWriter);
        //jsonWriter.close();
    }

    public void writeJsonEdad(List<TIA_ZonasBasicas_Edad> zonaBasica, String jsonEdad) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        //FileWriter writer = new FileWriter(jsonEdad);
        //JsonWriter jsonWriter = new JsonWriter(writer);
        //jsonWriter.setIndent("  ");
        JsonArray jsonArray = gson.toJsonTree(zonaBasica).getAsJsonArray();
        System.out.println(jsonArray.toString());
        //gson.toJson(jsonArray, jsonWriter);
        //jsonWriter.close();
    }

}
