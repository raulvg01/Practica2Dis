package com.UFV.prExt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ControladorAPITest {

    @Mock
    private JsonReader jsonReader;

    @Mock
    private List<TIA_ZonasBasicas> listaZonasBasicas;

    @Mock
    private List<TIA_ZonasBasicas_Edad> listaZonasEdad;

    @InjectMocks
    private ControladorAPI controladorAPI;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private String jsonBasicas = "src/main/resources/Covid19-TIA_ZonasBasicasSalud.json";

    private String jsonEdad = "src/main/resources/Covid19-TIA_ZonasBasicasSalud_Mayores60.json";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetZonasBasicasPrimera() throws FileNotFoundException, ParseException {
        when(jsonReader.readJsonZonaBasica(jsonBasicas)).thenReturn((ArrayList<TIA_ZonasBasicas>) listaZonasBasicas);
        List<TIA_ZonasBasicas> result = controladorAPI.getZonasBasicasPrimera();
        assertEquals(listaZonasBasicas, result);
    }

    @Test
    public void testGetZonasBasicas() throws FileNotFoundException, ParseException {
        when(jsonReader.readJsonZonaBasica(jsonBasicas)).thenReturn((ArrayList<TIA_ZonasBasicas>) listaZonasBasicas);
        List<TIA_ZonasBasicas> result = controladorAPI.getZonasBasicas();
        assertEquals(listaZonasBasicas, result);
    }

    @Test
    public void testAddZonaBasica() throws Exception {
        TIA_ZonasBasicas zonaBasica = new TIA_ZonasBasicas();
        String zonaBasicaString = gson.toJson(zonaBasica);
        String expected = "Zona añadida correctamente";
        when(listaZonasBasicas.size()).thenReturn(0);
        String result = controladorAPI.addZonaBasica(zonaBasicaString);
        assertEquals(expected, result);
        verify(listaZonasBasicas).add(zonaBasica);
    }

    @Test
    public void testUpdateZonaBasica() throws Exception {
        TIA_ZonasBasicas zonaBasica = new TIA_ZonasBasicas();
        zonaBasica.setId(1);
        String zonaBasicaString = gson.toJson(zonaBasica);
        when(listaZonasBasicas.get(1)).thenReturn(zonaBasica);
        String expected = "Zona actualizada";
        String result = controladorAPI.updateZonaBasica(zonaBasicaString);
        assertEquals(expected, result);
        verify(listaZonasBasicas.get(1)).setZona_basica_salud(zonaBasica.getZona_basica_salud());
        verify(listaZonasBasicas.get(1)).setTasa_incidencia_acumulada_ultimos_14dias(zonaBasica.getTasa_incidencia_acumulada_ultimos_14dias());
        verify(listaZonasBasicas.get(1)).setTasa_incidencia_acumulada_total(zonaBasica.getTasa_incidencia_acumulada_total());
        verify(listaZonasBasicas.get(1)).setFecha_informe(zonaBasica.getFecha_informe());
        verify(listaZonasBasicas.get(1)).setCasos_confirmados_totales(zonaBasica.getCasos_confirmados_totales());
        verify(listaZonasBasicas.get(1)).setCasos_confirmados_ultimos_14dias(zonaBasica.getCasos_confirmados_ultimos_14dias());
    }

    @Test
    public void testGetZonasEdadPrimera() throws FileNotFoundException, ParseException {
        when(jsonReader.readJsonZonaEdad(jsonEdad)).thenReturn(listaZonasEdad);
        List<TIA_ZonasBasicas_Edad> result = controladorAPI.getZonasEdadPrimera();
        assertEquals(listaZonasEdad, result);
    }

    @Test
    public void testGetZonasEdad() throws FileNotFoundException, ParseException {
        when(jsonReader.readJsonZonaEdad(jsonEdad)).thenReturn(listaZonasEdad);
        List<TIA_ZonasBasicas_Edad> result = controladorAPI.getZonasEdad();
        assertEquals(listaZonasEdad, result);
    }

    @Test
    public void testUpdateZonaEdad() throws Exception {
        TIA_ZonasBasicas_Edad zonaEdad = new TIA_ZonasBasicas_Edad();
        zonaEdad.setId(1);
        String zonaEdadString = gson.toJson(zonaEdad);
        when(listaZonasEdad.get(1)).thenReturn(zonaEdad);
        String expected = "Zona actualizada";
        String result = controladorAPI.updateZonaEdad(zonaEdadString);
        assertEquals(expected, result);
        verify(listaZonasEdad.get(1)).setZona_basica_salud(zonaEdad.getZona_basica_salud());
        verify(listaZonasEdad.get(1)).setTasa_incidencia_acumulada_P60mas_ultimos_14dias(zonaEdad.getTasa_incidencia_acumulada_P60mas_ultimos_14dias());
        verify(listaZonasEdad.get(1)).setFecha_informe(zonaEdad.getFecha_informe());
        verify(listaZonasEdad.get(1)).setCasos_confirmados_P60mas_ultimos_14dias(zonaEdad.getCasos_confirmados_P60mas_ultimos_14dias());
    }
}