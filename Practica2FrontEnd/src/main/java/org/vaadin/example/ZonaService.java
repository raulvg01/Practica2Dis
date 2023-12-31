package org.vaadin.example;



import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service
public class ZonaService implements Serializable{

    public ArrayList<TIA_ZonasBasicas> getZonasBasicasPrimera() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getZonasBasicasPrimera();
        //System.out.println(resultsAPI);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ArrayList<TIA_ZonasBasicas> listaZonasBasicas = gson.fromJson(resultsAPI, new TypeToken<ArrayList<TIA_ZonasBasicas>>(){}.getType());
        //System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasBasicas;
    }

    public ArrayList<TIA_ZonasBasicas> getZonasBasicas() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getZonasBasicas();
        //System.out.println(resultsAPI);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ArrayList<TIA_ZonasBasicas> listaZonasBasicas = gson.fromJson(resultsAPI, new TypeToken<ArrayList<TIA_ZonasBasicas>>(){}.getType());
        //System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasBasicas;
    }

    public String updateZonaBasica(TIA_ZonasBasicas zonaBasicaUpdate) throws Exception {
        API api = new API();
        String resultsAPI = api.updateZonaBasica(zonaBasicaUpdate);

        return resultsAPI;
    }

    public String addZonaBasica(TIA_ZonasBasicas zonaBasicaUpdate) throws Exception {
        API api = new API();
        String resultsAPI = api.addZonaBasica(zonaBasicaUpdate);

        return resultsAPI;
    }

    public ArrayList<TIA_ZonasBasicas_Edad> getZonasEdadPrimera() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getZonasEdadPrimera();
        //System.out.println(resultsAPI);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ArrayList<TIA_ZonasBasicas_Edad> listaZonasEdad = gson.fromJson(resultsAPI, new TypeToken<ArrayList<TIA_ZonasBasicas_Edad>>(){}.getType());
        //System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasEdad;
    }

    public ArrayList<TIA_ZonasBasicas_Edad> getZonasEdad() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getZonasEdad();
        //System.out.println(resultsAPI);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ArrayList<TIA_ZonasBasicas_Edad> listaZonasEdad = gson.fromJson(resultsAPI, new TypeToken<ArrayList<TIA_ZonasBasicas_Edad>>(){}.getType());
        //System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasEdad;
    }

    public String updateZonaEdad(TIA_ZonasBasicas_Edad zonaEdadUpdate) throws Exception {
        API api = new API();
        String resultsAPI = api.updateZonaEdad(zonaEdadUpdate);

        return resultsAPI;
    }


}
