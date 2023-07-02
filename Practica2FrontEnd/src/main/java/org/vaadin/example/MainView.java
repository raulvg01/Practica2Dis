package org.vaadin.example;

// Importaciones adicionales
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemDoubleClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")

public class MainView extends VerticalLayout {

    private Grid<TIA_ZonasBasicas> gridZonasBasicas = new Grid<>(TIA_ZonasBasicas.class);

    // Crear el diálogo y los campos de texto
    private Dialog editDialog = new Dialog();
    private TextField codigoGeometriaField = new TextField("Código Geometria");
    private TextField zonaBasicaSaludField = new TextField("Zona Básica Salud");
    private TextField tasaIncidencia14DiasField = new TextField("Tasa Incidencia 14 Días");
    private TextField tasaIncidenciaTotalField = new TextField("Tasa Incidencia Total");
    private TextField casosConfirmadosTotalesField = new TextField("Casos Confirmados Totales");
    private DatePicker fechaInformeField = new DatePicker("Fecha Informe");

    public MainView(@Autowired ZonaService service) {
        ArrayList<TIA_ZonasBasicas> listaZonasBasicas = new ArrayList<>();

        try {
            listaZonasBasicas = service.getZonasBasicas();
        } catch (Exception ex) {
            Notification.show("Error al leer las Zonas Basicas");
        }
        System.out.println(listaZonasBasicas.size());

        gridZonasBasicas.setItems(listaZonasBasicas);
        gridZonasBasicas.setColumns("codigo_geometria", "zona_basica_salud", "tasa_incidencia_acumulada_ultimos_14dias", "tasa_incidencia_acumulada_total", "casos_confirmados_totales", "fecha_informe");

        // Configurar el diálogo
        configureEditDialog();

        // Agregar el listener de doble clic al grid
        gridZonasBasicas.addItemDoubleClickListener(this::onGridItemDoubleClick);

        // Agregar el grid y el diálogo al layout principal
        add(gridZonasBasicas, editDialog);
    }

    private void configureEditDialog() {
        // Configurar el layout del diálogo
        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.add(codigoGeometriaField, zonaBasicaSaludField, tasaIncidencia14DiasField, tasaIncidenciaTotalField, casosConfirmadosTotalesField, fechaInformeField);

        // Hacer que el campo codigoGeometriaField sea de solo lectura
        codigoGeometriaField.setReadOnly(true);

        // Configurar botones
        Button cancelButton = new Button("Cancelar", e -> editDialog.close());
        Button saveButton = new Button("Guardar", e -> saveEditedData());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonsLayout = new HorizontalLayout(cancelButton, saveButton);

        // Agregar layout y botones al diálogo
        editDialog.add(dialogLayout, buttonsLayout);
    }

    private void onGridItemDoubleClick(ItemDoubleClickEvent<TIA_ZonasBasicas> event) {
        TIA_ZonasBasicas selectedItem = event.getItem();
        if (selectedItem != null) {
            // Rellenar campos de texto con datos de la fila seleccionada
            codigoGeometriaField.setValue(selectedItem.getCodigo_geometria());
            zonaBasicaSaludField.setValue(selectedItem.getZona_basica_salud());
            tasaIncidencia14DiasField.setValue(Float.toString(selectedItem.getTasa_incidencia_acumulada_ultimos_14dias()));
            tasaIncidenciaTotalField.setValue(Float.toString(selectedItem.getTasa_incidencia_acumulada_total()));
            casosConfirmadosTotalesField.setValue(String.valueOf(selectedItem.getCasos_confirmados_totales()));

            // Rellenar el campo de fecha con el formato deseado
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            //LocalDateTime fechaInforme = LocalDateTime.parse((CharSequence) selectedItem.getFecha_informe(), formatter);
            //fechaInformeField.setValue(fechaInforme.toLocalDate());
            //Solucionar el problema de la fecha


            // Abrir el diálogo
            editDialog.open();
        }
    }

    private void saveEditedData() {
        // Implementar la lógica para guardar los datos editados
        // ...

        // Cerrar el diálogo
        editDialog.close();
    }
}