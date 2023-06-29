package com.UFV.prExt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ControladorAPI
@RequestMapping("/api")

public class ControladorAPI {


    private List<TIA_ZonasBasicas> listaZonas;

    public ZonasController() {
        cargarDatos();
    }

    @GetMapping("/zonas")
    public List<TIA_ZonasBasicas> obtenerZonas() {
        return listaZonas;
    }

    private void cargarDatos() {
        try {
            ClassPathResource resource = new ClassPathResource("datos.json");
            InputStreamReader reader = new InputStreamReader(resource.getInputStream());

            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<TIA_ZonasBasicas>>(){}.getType();
            listaZonas = gson.fromJson(reader, tipoLista);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
