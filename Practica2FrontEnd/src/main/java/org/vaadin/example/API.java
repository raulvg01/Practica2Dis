package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
public class API {
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static final String urlPrefix = "http://localhost:8080/%s";

    public String getZonasBasicasPrimera() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "zonasBasicasPrimera", "");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
       // System.out.println(response.body());
        return response.body();
    }

    public String getZonasBasicas() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, "zonasBasicas", "");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println(response.body());
        return response.body();
    }

    public String postZonaBasica(TIA_ZonasBasicas zonaBasica) throws Exception{
        // creamos la url de la api
        String fullUrl = String.format(urlPrefix, "zonaBasica", "");

        // convertimos el objeto zona a json
        JsonObject jsonObject = gson.toJsonTree(zonaBasica).getAsJsonObject();
        // creamos un objeto HttpResquest con la url y el json
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(fullUrl)).POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString())).header("Content-Type", "application/json").build();
        // obtenemos la respuesta de la api y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        // devolvemos la respuesta
        return response.body();
    }

    public String updateZonaBasica(TIA_ZonasBasicas zonaBasicaUpdated) throws Exception{
        // creamos la url de la api
        String fullUrl = String.format(urlPrefix, "updateZonaBasica", "");

        // convertimos el objeto zona a json
        JsonObject jsonObject = gson.toJsonTree(zonaBasicaUpdated).getAsJsonObject();
        // creamos un objeto HttpResquest con la url y el json
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(fullUrl)).POST(HttpRequest.BodyPublishers.ofString(jsonObject.toString())).header("Content-Type", "application/json").build();
        // obtenemos la respuesta de la api y la convertimos a String
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        // devolvemos la respuesta
        return response.body();
    }

}
