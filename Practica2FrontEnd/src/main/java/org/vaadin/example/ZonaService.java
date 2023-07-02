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

    public ArrayList<TIA_ZonasBasicas> getZonasBasicas() throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getZonasBasicas();
        System.out.println(resultsAPI);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ArrayList<TIA_ZonasBasicas> listaZonasBasicas = gson.fromJson(resultsAPI, new TypeToken<ArrayList<TIA_ZonasBasicas>>(){}.getType());
        System.out.println(listaZonasBasicas.get(0).getTodo());
        return listaZonasBasicas;
    }


}
