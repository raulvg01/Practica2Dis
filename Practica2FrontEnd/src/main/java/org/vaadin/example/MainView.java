package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private Grid<TIA_ZonasBasicas> gridZonasBasicas = new Grid<>(TIA_ZonasBasicas.class);



    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired ZonaService service) {
        ArrayList<TIA_ZonasBasicas> listaZonasBasicas = new ArrayList<>() ;


        try{
            listaZonasBasicas = service.getZonasBasicas();

        } catch (Exception ex) {
            Notification.show("Error al leer las Zonas Basicas");
        }
        System.out.println(listaZonasBasicas.size());

        gridZonasBasicas.setItems(listaZonasBasicas);
        gridZonasBasicas.setColumns("codigo_geometria", "zona_basica_salud", "tasa_incidencia_acumulada_ultimos_14dias", "tasa_incidencia_acumulada_total", "casos_confirmados_totales", "fecha_informe");

        // Agregar el grid al layout principal
        add(gridZonasBasicas);

    }

}
