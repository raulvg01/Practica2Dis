package org.vaadin.example;

// Importaciones adicionales
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemDoubleClickEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")

public class MainView extends VerticalLayout {
    private Grid<TIA_ZonasBasicas> gridZonasBasicas = new Grid<>(TIA_ZonasBasicas.class);
    private VerticalLayout pestanya1 = new VerticalLayout();
    // Crear el diálogo y los campos de texto
    private Dialog editDialog = new Dialog();
    private Dialog addDialog = new Dialog();
    private TextField idField = new TextField("Id");
    private TextField codigoGeometriaField = new TextField("Código Geometria");
    private TextField zonaBasicaSaludField = new TextField("Zona Básica Salud");
    private TextField tasaIncidencia14DiasField = new TextField("Tasa Incidencia 14 Días");
    private TextField tasaIncidenciaTotalField = new TextField("Tasa Incidencia Total");
    private TextField casosConfirmadosTotalesField = new TextField("Casos Confirmados Totales");
    private TextField casosConfirmados14DiasField = new TextField("Casos Confirmados 14 Días");
    private DatePicker fechaInformeField = new DatePicker("Fecha Informe");
    ArrayList<TIA_ZonasBasicas> listaZonasBasicas = new ArrayList<>();


    //Campos edad
    private Grid<TIA_ZonasBasicas_Edad> gridZonasBasicasEdad = new Grid<>(TIA_ZonasBasicas_Edad.class);
    private VerticalLayout pestanya2 = new VerticalLayout();
    private Dialog editDialog2 = new Dialog();
    private  TextField idField2 = new TextField("Id");
    private TextField codigoGeometriaField2 = new TextField("Código Geometria");
    private TextField zonaBasicaSaludField2 = new TextField("Zona Básica Salud");
    private TextField tasa_incidencia_acumulada_P60mas_ultimos_14diasField = new TextField("Tasa Incidencia 14 Días");
    private TextField casos_confirmados_P60mas_ultimos_14diasField = new TextField("Casos 14 días");
    private DatePicker fechaInformeField2 = new DatePicker("Fecha Informe");
    ArrayList<TIA_ZonasBasicas_Edad> listaZonasBasicasEdad = new ArrayList<>();

    VerticalLayout zonasBasicasLayout = new VerticalLayout();
    VerticalLayout zonasBasicasEdadLayout = new VerticalLayout();

    //VerticalLayout del contenido
    private final VerticalLayout contenido = new VerticalLayout();




    public MainView(@Autowired ZonaService service) {

        // Crear componente de pestañas
        Tabs tabs = new Tabs();
        Tab tabZonasBasicas = new Tab("Zonas Básicas");
        Tab tabZonasEdad = new Tab("Zonas Básicas Edad");
        tabs.add(tabZonasBasicas, tabZonasEdad);
        add(tabs);

        // Contenedor de pestaña Zonas Básicas
        zonasBasicasLayout.add(gridZonasBasicas);

        // Contenedor de pestaña Zonas Básicas Edad
        zonasBasicasEdadLayout.add(gridZonasBasicasEdad);

        // Agregar el contenido existente del método MainView al layout de Zonas Básicas
        try {
            listaZonasBasicas = service.getZonasBasicasPrimera();
        } catch (Exception ex) {
            Notification.show("Error al leer las Zonas Basicas");
        }

        gridZonasBasicas.setItems(listaZonasBasicas);
        gridZonasBasicas.setColumns("id", "codigo_geometria", "zona_basica_salud", "tasa_incidencia_acumulada_ultimos_14dias", "tasa_incidencia_acumulada_total", "casos_confirmados_totales","casos_confirmados_ultimos_14dias", "fecha_informe");


        // Agregar el contenido existente del método MainView al layout de Zonas Básicas Edad
        ArrayList<TIA_ZonasBasicas_Edad> listaZonasBasicasEdad = new ArrayList<>();
        try {
            listaZonasBasicasEdad = service.getZonasEdadPrimera();
        } catch (Exception ex) {
            Notification.show("Error al leer las Zonas Basicas");
        }

        //Grid<TIA_ZonasBasicas_Edad> gridZonasBasicasEdad = new Grid<>(TIA_ZonasBasicas_Edad.class);
        gridZonasBasicasEdad.setItems(listaZonasBasicasEdad);
        gridZonasBasicasEdad.setColumns("id", "codigo_geometria", "zona_basica_salud", "tasa_incidencia_acumulada_P60mas_ultimos_14dias", "casos_confirmados_P60mas_ultimos_14dias", "fecha_informe");




        // Configurar el diálogo de edición
        configureEditDialog();
        // Agregar el listener de doble clic al grid
        gridZonasBasicas.addItemDoubleClickListener(this::onGridItemDoubleClick);

        // Configurar diálogo 2
        configureEditDialog2();

        // Agregar el listener de doble clic al grid 2
        gridZonasBasicasEdad.addItemDoubleClickListener(this::onGridItemDoubleClick2);

        // Agregar Grid y contenido al layout de Zonas Básicas
        zonasBasicasLayout.add(gridZonasBasicas);
        // Agregar grid y contenido al layout de Zonas Básicas Edad
        zonasBasicasEdadLayout.add(gridZonasBasicasEdad);

        // Añadir botón de Nuevo al layout de Zonas Básicas
        Button newButton = new Button("Nuevo", e -> openNewDialog());
        zonasBasicasLayout.add(newButton);

        // Evento de cambio de pestaña
        tabs.addSelectedChangeListener(event -> {
            if (event.getSelectedTab().equals(tabZonasBasicas)) {
                contenido.removeAll();
                contenido.add(zonasBasicasLayout);
            } else if (event.getSelectedTab().equals(tabZonasEdad)) {
                contenido.removeAll();
                contenido.add(zonasBasicasEdadLayout);
            }
        });

        //Mostrar solo el primer Grid si no se ha seleccionado ninguna pestaña
        contenido.add(zonasBasicasLayout);
        add(contenido);

    }



    private void configureEditDialog2() {
        // Configurar el layout del diálogo
        VerticalLayout dialogLayout2 = new VerticalLayout();
        dialogLayout2.add(idField2,codigoGeometriaField2, zonaBasicaSaludField2, tasa_incidencia_acumulada_P60mas_ultimos_14diasField, casos_confirmados_P60mas_ultimos_14diasField, fechaInformeField2);

        // Hacer que el campo codigoGeometriaField sea de solo lectura
        idField.setReadOnly(true);
        codigoGeometriaField.setReadOnly(true);


        // Configurar botones
        Button cancelButton2 = new Button("Cancelar", e -> editDialog2.close());
        Button saveButton2 = new Button("Guardar", e -> {
            try {
                saveEditedData2();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            gridZonasBasicasEdad.setItems(listaZonasBasicasEdad);
        });
        saveButton2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonsLayout2 = new HorizontalLayout(cancelButton2, saveButton2);


        // Agregar layout y botones al diálogo
        editDialog2.add(dialogLayout2, buttonsLayout2);
    }

    private void configureEditDialog(){

        // Configurar el layout del diálogo
        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.add(idField,codigoGeometriaField, zonaBasicaSaludField, tasaIncidencia14DiasField, tasaIncidenciaTotalField, casosConfirmadosTotalesField,casosConfirmados14DiasField, fechaInformeField);

        // Hacer que el campo codigoGeometriaField sea de solo lectura
        idField.setReadOnly(true);
        codigoGeometriaField.setReadOnly(true);


        // Configurar botones
        Button cancelButton = new Button("Cancelar", e -> editDialog.close());
        Button saveButton = new Button("Guardar", e -> {
            try {
                saveEditedData();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            gridZonasBasicas.setItems(listaZonasBasicas);
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonsLayout = new HorizontalLayout(cancelButton, saveButton);


        // Agregar layout y botones al diálogo
        editDialog.add(dialogLayout, buttonsLayout);

    }


    private void onGridItemDoubleClick(ItemDoubleClickEvent<TIA_ZonasBasicas> event) {
        // Crear un nuevo diálogo de edición
        Dialog editDialog = new Dialog();

        // Obtener el elemento seleccionado del Grid
        TIA_ZonasBasicas selectedZonaBasica = event.getItem();

        // Configurar los campos del diálogo con los datos del elemento seleccionado
        idField.setValue(String.valueOf(selectedZonaBasica.getId()));
        idField.setReadOnly(true);
        codigoGeometriaField.setValue(selectedZonaBasica.getCodigo_geometria());
        zonaBasicaSaludField.setValue(selectedZonaBasica.getZona_basica_salud());
        tasaIncidencia14DiasField.setValue(selectedZonaBasica.getTasa_incidencia_acumulada_ultimos_14dias().toString());
        tasaIncidenciaTotalField.setValue(selectedZonaBasica.getTasa_incidencia_acumulada_total().toString());
        casosConfirmadosTotalesField.setValue(String.valueOf(selectedZonaBasica.getCasos_confirmados_totales()));
        casosConfirmados14DiasField.setValue(String.valueOf(selectedZonaBasica.getCasos_confirmados_ultimos_14dias()));
        Date date = selectedZonaBasica.getFecha_informe();
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        fechaInformeField.setValue(localDate);

        // Configurar los botones del diálogo
        Button cancelButton = new Button("Cancelar", e -> editDialog.close());
        Button saveButton = new Button("Guardar", e -> {
            try {
                saveEditedData();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            editDialog.close();
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonsLayout = new HorizontalLayout(cancelButton, saveButton);

        // Agregar los campos y los botones al diálogo
        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.add(idField, codigoGeometriaField, zonaBasicaSaludField, tasaIncidencia14DiasField, tasaIncidenciaTotalField, casosConfirmadosTotalesField, casosConfirmados14DiasField, fechaInformeField);
        editDialog.add(dialogLayout, buttonsLayout);

        // Abrir el diálogo de edición
        editDialog.open();
    }

    //Evento doble click en la tabla de zonas basicas de salud por edad

    private void onGridItemDoubleClick2(ItemDoubleClickEvent<TIA_ZonasBasicas_Edad> event) {
        // Crear un nuevo diálogo de edición
        Dialog editDialog2 = new Dialog();

        // Obtener el elemento seleccionado del Grid
        TIA_ZonasBasicas_Edad selectedItem = event.getItem();

        // Configurar los campos del diálogo con los datos del elemento seleccionado
        // Rellenar campos de texto con datos de la fila seleccionada
        idField2.setValue(Integer.toString(selectedItem.getId()));
        codigoGeometriaField2.setValue(selectedItem.getCodigo_geometria());
        zonaBasicaSaludField2.setValue(selectedItem.getZona_basica_salud());
        tasa_incidencia_acumulada_P60mas_ultimos_14diasField.setValue(Float.toString(selectedItem.getTasa_incidencia_acumulada_P60mas_ultimos_14dias()));
        casos_confirmados_P60mas_ultimos_14diasField.setValue(String.valueOf(selectedItem.getCasos_confirmados_P60mas_ultimos_14dias()));
        Instant instant = selectedItem.getFecha_informe().toInstant();
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        fechaInformeField2.setValue(localDateTime.toLocalDate());

        // Configurar los botones del diálogo
        Button cancelButton = new Button("Cancelar", e -> editDialog.close());
        Button saveButton = new Button("Guardar", e -> {
            try {
                saveEditedData2();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            editDialog2.close();
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonsLayout2 = new HorizontalLayout(cancelButton, saveButton);

        // Agregar los campos y los botones al diálogo
        VerticalLayout dialogLayout2 = new VerticalLayout();
        dialogLayout2.add(idField2,codigoGeometriaField2, zonaBasicaSaludField2, tasa_incidencia_acumulada_P60mas_ultimos_14diasField, casos_confirmados_P60mas_ultimos_14diasField, fechaInformeField2);
        editDialog2.add(dialogLayout2, buttonsLayout2);

        // Abrir el diálogo de edición
        editDialog2.open();
    }

    private void saveEditedData() throws Exception {
        ZonaService service = new ZonaService();
       TIA_ZonasBasicas zonaBasicaUpdate = new TIA_ZonasBasicas();
        // Rellenar el objeto con los datos del formulario
        zonaBasicaUpdate.setId(Integer.parseInt(idField.getValue()));
        zonaBasicaUpdate.setCodigo_geometria(codigoGeometriaField.getValue());
        zonaBasicaUpdate.setZona_basica_salud(zonaBasicaSaludField.getValue());
        zonaBasicaUpdate.setTasa_incidencia_acumulada_ultimos_14dias(Float.parseFloat(tasaIncidencia14DiasField.getValue()));
        zonaBasicaUpdate.setTasa_incidencia_acumulada_total(Float.parseFloat(tasaIncidenciaTotalField.getValue()));
        zonaBasicaUpdate.setCasos_confirmados_totales(Integer.parseInt(casosConfirmadosTotalesField.getValue()));
        zonaBasicaUpdate.setCasos_confirmados_ultimos_14dias(Integer.parseInt(casosConfirmados14DiasField.getValue()));

        LocalDate localDate = fechaInformeField.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        java.util.Date date = java.util.Date.from(instant);
        zonaBasicaUpdate.setFecha_informe(date);
        String result = service.updateZonaBasica(zonaBasicaUpdate);
        System.out.println(result);
        //Recargar la pagina

        try {
            listaZonasBasicas = service.getZonasBasicas();
        } catch (Exception ex) {
            Notification.show("Error al leer las Zonas Basicas");
        }

        gridZonasBasicas.setItems(listaZonasBasicas);
        gridZonasBasicas.setColumns("id", "codigo_geometria", "zona_basica_salud", "tasa_incidencia_acumulada_ultimos_14dias", "tasa_incidencia_acumulada_total", "casos_confirmados_totales","casos_confirmados_ultimos_14dias", "fecha_informe");

        // Cerrar el diálogo
        editDialog.close();
    }


    private void saveEditedData2() throws Exception {
        ZonaService service = new ZonaService();
        TIA_ZonasBasicas_Edad zonaEdadUpdate = new TIA_ZonasBasicas_Edad();
        // Rellenar el objeto con los datos del formulario
        zonaEdadUpdate.setId(Integer.parseInt(idField2.getValue()));
        zonaEdadUpdate.setCodigo_geometria(codigoGeometriaField2.getValue());
        zonaEdadUpdate.setZona_basica_salud(zonaBasicaSaludField2.getValue());
        zonaEdadUpdate.setTasa_incidencia_acumulada_P60mas_ultimos_14dias(Float.parseFloat(tasa_incidencia_acumulada_P60mas_ultimos_14diasField.getValue()));
        zonaEdadUpdate.setCasos_confirmados_P60mas_ultimos_14dias(Integer.parseInt(casos_confirmados_P60mas_ultimos_14diasField.getValue()));
        LocalDate localDate = fechaInformeField2.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        java.util.Date date = java.util.Date.from(instant);
        zonaEdadUpdate.setFecha_informe(date);
        String result = service.updateZonaEdad(zonaEdadUpdate);
        System.out.println(result);
        //Recargar la pagina

        try {
            listaZonasBasicasEdad = service.getZonasEdad();
        } catch (Exception ex) {
            Notification.show("Error al leer las Zonas Edad");
        }

        System.out.println(listaZonasBasicasEdad.get(0).getTodo());

        gridZonasBasicasEdad.setItems(listaZonasBasicasEdad);
        gridZonasBasicasEdad.setColumns( "id", "codigo_geometria", "zona_basica_salud", "tasa_incidencia_acumulada_P60mas_ultimos_14dias", "casos_confirmados_P60mas_ultimos_14dias", "fecha_informe");


        // Cerrar el diálogo
        editDialog2.close();
    }




    private void openNewDialog() {
        // Crear un nuevo diálogo
        Dialog addDialog = new Dialog();

        VerticalLayout dialogLayout = new VerticalLayout();
        idField.clear();
        codigoGeometriaField.clear();
        zonaBasicaSaludField.clear();
        tasaIncidencia14DiasField.clear();
        tasaIncidenciaTotalField.clear();
        casosConfirmadosTotalesField.clear();
        casosConfirmados14DiasField.clear();
        fechaInformeField.clear();
        dialogLayout.add(codigoGeometriaField, zonaBasicaSaludField, tasaIncidencia14DiasField, tasaIncidenciaTotalField, casosConfirmadosTotalesField, casosConfirmados14DiasField, fechaInformeField);
        codigoGeometriaField.setReadOnly(false);

        Button cancelButton = new Button("Cancelar", e -> addDialog.close());
        Button saveButton = new Button("Guardar", e -> {
            try {
                saveNewData();
                addDialog.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonsLayout = new HorizontalLayout(cancelButton, saveButton);

        addDialog.add(dialogLayout, buttonsLayout);

        // Abrir el diálogo
        addDialog.open();
    }

    private void saveNewData() throws Exception {
        ZonaService service = new ZonaService();
        TIA_ZonasBasicas zonaBasicaAdd = new TIA_ZonasBasicas();
        // Rellenar el objeto con los datos del formulario
        zonaBasicaAdd.setCodigo_geometria(codigoGeometriaField.getValue());
        zonaBasicaAdd.setZona_basica_salud(zonaBasicaSaludField.getValue());
        zonaBasicaAdd.setTasa_incidencia_acumulada_ultimos_14dias(Float.parseFloat(tasaIncidencia14DiasField.getValue()));
        zonaBasicaAdd.setTasa_incidencia_acumulada_total(Float.parseFloat(tasaIncidenciaTotalField.getValue()));
        zonaBasicaAdd.setCasos_confirmados_totales(Integer.parseInt(casosConfirmadosTotalesField.getValue()));
        zonaBasicaAdd.setCasos_confirmados_ultimos_14dias(Integer.parseInt(casosConfirmados14DiasField.getValue()));
        LocalDate localDate = fechaInformeField.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        java.util.Date date = java.util.Date.from(instant);
        zonaBasicaAdd.setFecha_informe(date);
        String result = service.addZonaBasica(zonaBasicaAdd);
        System.out.println(result);
        //Recargar la pagina
        ArrayList<TIA_ZonasBasicas> listaZonasBasicas = new ArrayList<>();

        try {
            listaZonasBasicas = service.getZonasBasicas();
        } catch (Exception ex) {
            Notification.show("Error al leer las Zonas Basicas");
        }

        gridZonasBasicas.setItems(listaZonasBasicas);
        gridZonasBasicas.setColumns("id","codigo_geometria", "zona_basica_salud", "tasa_incidencia_acumulada_ultimos_14dias", "tasa_incidencia_acumulada_total", "casos_confirmados_totales", "fecha_informe");

        // Cerrar el diálogo
        addDialog.close();
    }

}