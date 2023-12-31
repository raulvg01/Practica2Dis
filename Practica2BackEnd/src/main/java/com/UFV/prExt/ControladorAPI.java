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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ControladorAPI {

    private List<TIA_ZonasBasicas> listaZonasBasicas;
    private List<TIA_ZonasBasicas_Edad> listaZonasEdad;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    private JsonReader jsonReader = new JsonReader();

    private String jsonBasicas = "src/main/resources/Covid19-TIA_ZonasBasicasSalud.json";

    private String jsonEdad = "src/main/resources/Covid19-TIA_ZonasBasicasSalud_Mayores60.json";


    @GetMapping("/zonasBasicasPrimera")
    public List<TIA_ZonasBasicas> getZonasBasicasPrimera() throws FileNotFoundException, ParseException {
        listaZonasBasicas = jsonReader.readJsonZonaBasica(jsonBasicas);
        // System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasBasicas;
    }

    @GetMapping("/zonasBasicas")
    public List<TIA_ZonasBasicas> getZonasBasicas() throws FileNotFoundException, ParseException {
        // System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasBasicas;
    }
    @PostMapping("/zonaBasica")
    public String addZonaBasica(@RequestBody String zonaBasicaString) throws IOException {
        TIA_ZonasBasicas zonaBasica = gson.fromJson(zonaBasicaString, TIA_ZonasBasicas.class);
        zonaBasica.setId(listaZonasBasicas.size());
        listaZonasBasicas.add(zonaBasica);
        return "Zona añadida correctamente";

    }


    @PostMapping("/updateZonaBasica")
    public String updateZonaBasica(@RequestBody String zonaBasicaString) throws IOException {
        TIA_ZonasBasicas zonaBasica = gson.fromJson(zonaBasicaString, TIA_ZonasBasicas.class);
        //System.out.println(zonaBasica.getTodo());
        for (TIA_ZonasBasicas zona : listaZonasBasicas) {
            if (zona.getId() == zonaBasica.getId()){
                zona.setZona_basica_salud(zonaBasica.getZona_basica_salud());
                zona.setTasa_incidencia_acumulada_ultimos_14dias(zonaBasica.getTasa_incidencia_acumulada_ultimos_14dias());
                zona.setTasa_incidencia_acumulada_total(zonaBasica.getTasa_incidencia_acumulada_total());
                zona.setFecha_informe(zonaBasica.getFecha_informe());
                zona.setCasos_confirmados_totales(zonaBasica.getCasos_confirmados_totales());
                zona.setCasos_confirmados_ultimos_14dias(zonaBasica.getCasos_confirmados_ultimos_14dias());
                return "Zona actualizada";
            }
        }
        return "Zona no encontrada";
        //jsonReader.writeJsonBasicas(listaZonasBasicas, jsonBasicas);
    }




    @GetMapping("/zonasEdadPrimera")
    public List<TIA_ZonasBasicas_Edad> getZonasEdadPrimera() throws FileNotFoundException, ParseException {
        listaZonasEdad = jsonReader.readJsonZonaEdad(jsonEdad);
        // System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasEdad;
    }

    @GetMapping("/zonasEdad")
    public List<TIA_ZonasBasicas_Edad> getZonasEdad() throws FileNotFoundException, ParseException {
        // System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasEdad;
    }


    @PostMapping("/updateZonaEdad")
    public String updateZonaEdad(@RequestBody String zonaEdadString) throws IOException {
        TIA_ZonasBasicas_Edad zonaEdad = gson.fromJson(zonaEdadString, TIA_ZonasBasicas_Edad.class);
        //System.out.println(zonaBasica.getTodo());
        for (TIA_ZonasBasicas_Edad zona : listaZonasEdad) {
            if (zona.getId() == zonaEdad.getId()){
                zona.setZona_basica_salud(zonaEdad.getZona_basica_salud());
                zona.setTasa_incidencia_acumulada_P60mas_ultimos_14dias(zonaEdad.getTasa_incidencia_acumulada_P60mas_ultimos_14dias());
                zona.setFecha_informe(zonaEdad.getFecha_informe());
                zona.setCasos_confirmados_P60mas_ultimos_14dias(zonaEdad.getCasos_confirmados_P60mas_ultimos_14dias());
                return "Zona actualizada";
            }
        }
        return "Zona no encontrada";
        //jsonReader.writeJsonBasicas(listaZonasBasicas, jsonBasicas);
    }



}







