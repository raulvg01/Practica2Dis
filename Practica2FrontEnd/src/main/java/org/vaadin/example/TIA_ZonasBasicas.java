package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class TIA_ZonasBasicas {
    String codigo_geometria;
    String zona_basica_salud;
    Float tasa_incidencia_acumulada_ultimos_14dias;
    Float tasa_incidencia_acumulada_total;
    int casos_confirmados_totales;
    int casos_confirmados_ultimos_14dias;
    Date fecha_informe;

    public TIA_ZonasBasicas() {
    }

    public TIA_ZonasBasicas(String codigo_geometria, String zona_basica_salud, Float tasa_incidencia_acumulada_ultimos_14dias, Float tasa_incidencia_acumulada_total, int casos_confirmados_totales, int casos_confirmados_ultimos_14dias, Date fecha_informe) {
        this.codigo_geometria = codigo_geometria;
        this.zona_basica_salud = zona_basica_salud;
        this.tasa_incidencia_acumulada_ultimos_14dias = tasa_incidencia_acumulada_ultimos_14dias;
        this.tasa_incidencia_acumulada_total = tasa_incidencia_acumulada_total;
        this.casos_confirmados_totales = casos_confirmados_totales;
        this.casos_confirmados_ultimos_14dias = casos_confirmados_ultimos_14dias;
        this.fecha_informe = fecha_informe;
    }

    public String getCodigo_geometria() {
        return codigo_geometria;
    }

    public String getZona_basica_salud() {
        return zona_basica_salud;
    }

    public Float getTasa_incidencia_acumulada_ultimos_14dias() {
        return tasa_incidencia_acumulada_ultimos_14dias;
    }

    public Float getTasa_incidencia_acumulada_total() {
        return tasa_incidencia_acumulada_total;
    }

    public int getCasos_confirmados_totales() {
        return casos_confirmados_totales;
    }

    public int getCasos_confirmados_ultimos_14dias() {
        return casos_confirmados_ultimos_14dias;
    }

    public Date getFecha_informe() {
        return fecha_informe;
    }

    public void setCodigo_geometria(String codigo_geometria) {
        this.codigo_geometria = codigo_geometria;
    }

    public void setZona_basica_salud(String zona_basica_salud) {
        this.zona_basica_salud = zona_basica_salud;
    }

    public void setTasa_incidencia_acumulada_ultimos_14dias(Float tasa_incidencia_acumulada_ultimos_14dias) {
        this.tasa_incidencia_acumulada_ultimos_14dias = tasa_incidencia_acumulada_ultimos_14dias;
    }

    public void setTasa_incidencia_acumulada_total(Float tasa_incidencia_acumulada_total) {
        this.tasa_incidencia_acumulada_total = tasa_incidencia_acumulada_total;
    }

    public void setCasos_confirmados_totales(int casos_confirmados_totales) {
        this.casos_confirmados_totales = casos_confirmados_totales;
    }

    public void setCasos_confirmados_ultimos_14dias(int casos_confirmados_ultimos_14dias) {
        this.casos_confirmados_ultimos_14dias = casos_confirmados_ultimos_14dias;
    }

    public void setFecha_informe(Date fecha_informe) {
        this.fecha_informe = fecha_informe;
    }


    public static List<TIA_ZonasBasicas> deserializarZonasBasicas(String json) {
        Gson gson = new Gson();
        TypeToken<List<TIA_ZonasBasicas>> token = new TypeToken<List<TIA_ZonasBasicas>>() {};
        List<TIA_ZonasBasicas> listaZonasBasicas = gson.fromJson(json, token.getType());
        return listaZonasBasicas;
    }

    //Método para comrpobar que se ha deserializado correctamente
    public List<TIA_ZonasBasicas> deserializar() {
        // Ruta del archivo JSON
        String filePath = "src/main/resources/Covid19-TIA_ZonasBásicasSalud.json";

        // Leer el contenido del archivo y convertirlo en una cadena de texto
        String json = readFileAsString(filePath);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy/MM/dd HH:mm:ss");
        Gson gson = gsonBuilder.create();

        DataWrapper wrapper = gson.fromJson(json, DataWrapper.class);
        List<TIA_ZonasBasicas> listaZonas = wrapper.getData();

        if (listaZonas != null && !listaZonas.isEmpty()) {
            for (TIA_ZonasBasicas zona : listaZonas) {
                System.out.println("Código de Geometría: " + zona.getCodigo_geometria());
                System.out.println("Zona Básica de Salud: " + zona.getZona_basica_salud());
                System.out.println("Tasa de Incidencia Acumulada en los Últimos 14 Días: " + zona.getTasa_incidencia_acumulada_ultimos_14dias());
                System.out.println("Tasa de Incidencia Acumulada Total: " + zona.getTasa_incidencia_acumulada_total());
                System.out.println("Casos Confirmados Totales: " + zona.getCasos_confirmados_totales());
                System.out.println("Fecha de Informe: " + zona.getFecha_informe());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("Error en la deserialización del JSON.");
        }


        return listaZonas;

    }

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
    }

