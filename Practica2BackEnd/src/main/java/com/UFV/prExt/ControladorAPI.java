package com.UFV.prExt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ControladorAPI {

    private List<TIA_ZonasBasicas> listaZonasBasicas;
    private List<TIA_ZonasBasicas_Edad> listaZonasEdad;

    private String jsonZonaBasica = "src/main/resources/Covid19-TIA_ZonasBasicasSalud.json";

    private String jsonZonaEdad = "src/main/resources/Covid19-TIA_ZonasBasicasSalud_Mayores60.json";

    private JsonReader jsonReader = new JsonReader();


    @GetMapping("/zonasBasicas")
    public List<TIA_ZonasBasicas> getZonasBasicas() throws FileNotFoundException {
        listaZonasBasicas = jsonReader.readJsonZonaBasica(jsonZonaBasica);
        return listaZonasBasicas;
    }


    @GetMapping("/zonasEdad")
    public List<TIA_ZonasBasicas_Edad> getZonasEdad() throws FileNotFoundException {
        listaZonasEdad = jsonReader.readJsonZonaEdad(jsonZonaEdad);
        return listaZonasEdad;
    }


    }








