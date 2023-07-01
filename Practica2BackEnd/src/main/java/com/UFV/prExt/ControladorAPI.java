package com.UFV.prExt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ControladorAPI {

    private List<TIA_ZonasBasicas> listaZonasBasicas;
    private List<TIA_ZonasBasicas_Edad> listaZonasEdad;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    private JsonReader jsonReader = new JsonReader();

    private String jsonBasicas = "src/main/resources/Covid19-TIA_ZonasBasicasSalud.json";

    private String jsonEdad = "src/main/resources/Covid19-TIA_ZonasBasicas_Edad.json";

    @GetMapping("/zonasBasicas")
    public List<TIA_ZonasBasicas> getZonasBasicas() throws FileNotFoundException {
        listaZonasBasicas = jsonReader.readJsonZonaBasica(jsonBasicas);
        return listaZonasBasicas;
    }
    @PostMapping("/zonaBasica")
    public void addZonaBasica(@RequestBody String zonaBasicaString) throws IOException {
        TIA_ZonasBasicas zonaBasica = gson.fromJson(zonaBasicaString, TIA_ZonasBasicas.class);
        zonaBasica.setCodigo_geometria(String.valueOf(listaZonasBasicas.size()));
        listaZonasBasicas.add(zonaBasica);
        jsonReader.writeJsonBasicas(listaZonasBasicas, jsonBasicas);

    }

    @PostMapping("/zonaEdad")
    public void addZonaEdad(@RequestBody String zonaEdadString) throws IOException {
        TIA_ZonasBasicas_Edad zonaEdad = gson.fromJson(zonaEdadString, TIA_ZonasBasicas_Edad.class);
        zonaEdad.setCodigo_geometria(String.valueOf(listaZonasBasicas.size()));
        listaZonasEdad.add(zonaEdad);
        jsonReader.writeJsonEdad(listaZonasEdad, jsonEdad);

    }

    @GetMapping("/zonasEdad")
    public List<TIA_ZonasBasicas_Edad> getZonasEdad() throws FileNotFoundException {
        listaZonasEdad = jsonReader.readJsonZonaEdad(jsonEdad);
        return listaZonasEdad;
    }

    @PutMapping("/updateZonaBasica/{codigo_geometria}")
    public void updateZonaBasica(@PathVariable String codigo_geometria, @RequestBody String zonaBasicaString) throws IOException {
        TIA_ZonasBasicas zonaBasica = gson.fromJson(zonaBasicaString, TIA_ZonasBasicas.class);

        for (TIA_ZonasBasicas zona : listaZonasBasicas) {
            if (zona.getCodigo_geometria().equals(codigo_geometria)) {
                //zona.setCodigo_geometria(codigo_geometria);
                zona.setZona_basica_salud(zonaBasica.getZona_basica_salud());
                zona.setFecha_informe(zonaBasica.getFecha_informe());
                zona.setTasa_incidencia_acumulada_ultimos_14dias(zonaBasica.getTasa_incidencia_acumulada_ultimos_14dias());
                zona.setTasa_incidencia_acumulada_total(zonaBasica.getTasa_incidencia_acumulada_total());
                zona.setFecha_informe(zonaBasica.getFecha_informe());
                //zona.setCodigo_geometria(zonaBasica.getCodigo_geometria());
                break;
            }
        }
        jsonReader.writeJsonBasicas(listaZonasBasicas, jsonBasicas);
    }

    @PutMapping("/updateZonaBasicaEdad/{codigo_geometria}")
    public void updateZonaBasicaEdad(@PathVariable String codigo_geometria, @RequestBody String zonaBasicaEdadString) throws IOException {
        TIA_ZonasBasicas_Edad zonaBasicaEdad = gson.fromJson(zonaBasicaEdadString, TIA_ZonasBasicas_Edad.class);

        for (TIA_ZonasBasicas_Edad zonaEdad : listaZonasEdad) {
            if (zonaEdad.getCodigo_geometria().equals(codigo_geometria)) {
                //zona.setCodigo_geometria(codigo_geometria);
                zonaEdad.setZona_basica_salud(zonaBasicaEdad.getZona_basica_salud());
                zonaEdad.setFecha_informe(zonaBasicaEdad.getFecha_informe());
                zonaEdad.setTasa_incidencia_acumulada_P60mas_ultimos_14dias(zonaBasicaEdad.getTasa_incidencia_acumulada_P60mas_ultimos_14dias());
                zonaEdad.setCasos_confirmados_P60mas_ultimos_14dias(zonaBasicaEdad.getCasos_confirmados_P60mas_ultimos_14dias());
                zonaEdad.setFecha_informe(zonaBasicaEdad.getFecha_informe());
                //zonaEdad.setCodigo_geometria(zonaBasicaEdad.getCodigo_geometria());
                break;
            }
        }
        jsonReader.writeJsonBasicas(listaZonasBasicas, jsonBasicas);
    }
}







