package org.vaadin.example;


import java.util.Date;

public class TIA_ZonasBasicas {
    String codigo_geometria;
    String zona_basica_salud;
    Float tasa_incidencia_acumulada_ultimos_14dias;
    Float tasa_incidencia_acumulada_total;
    int casos_confirmados_totales;
    Date fecha_informe;

    public TIA_ZonasBasicas() {
    }

    public TIA_ZonasBasicas(String codigo_geometria, String zona_basica_salud, Float tasa_incidencia_acumulada_ultimos_14dias, Float tasa_incidencia_acumulada_total, int casos_confirmados_totales, int casos_confirmados_ultimos_14dias, Date fecha_informe) {
        this.codigo_geometria = codigo_geometria;
        this.zona_basica_salud = zona_basica_salud;
        this.tasa_incidencia_acumulada_ultimos_14dias = tasa_incidencia_acumulada_ultimos_14dias;
        this.tasa_incidencia_acumulada_total = tasa_incidencia_acumulada_total;
        this.casos_confirmados_totales = casos_confirmados_totales;
        this.fecha_informe = fecha_informe;
    }

    public String getCodigo_geometria() {
        return codigo_geometria;
    }

    public String getZona_basica_salud() {
        return zona_basica_salud;
    }

    public Float getTasa_incidencia_acumulada_ultimos_14dias() {
        return tasa_incidencia_acumulada_ultimos_14dias;
    }

    public Float getTasa_incidencia_acumulada_total() {
        return tasa_incidencia_acumulada_total;
    }

    public int getCasos_confirmados_totales() {
        return casos_confirmados_totales;
    }


    public Date getFecha_informe() {
        return fecha_informe;
    }

    public String getTodo() {

        return "Codigo geometria: " + codigo_geometria + "\n" +
                "Zona basica salud: " + zona_basica_salud + "\n" +
                "Tasa incidencia acumulada ultimos 14 dias: " + tasa_incidencia_acumulada_ultimos_14dias + "\n" +
                "Tasa incidencia acumulada total: " + tasa_incidencia_acumulada_total + "\n" +
                "Casos confirmados totales: " + casos_confirmados_totales + "\n" +
                "Fecha informe: " + fecha_informe + "\n";

    }

    public void setCodigo_geometria(String codigo_geometria) {
        this.codigo_geometria = codigo_geometria;
    }

    public void setZona_basica_salud(String zona_basica_salud) {
        this.zona_basica_salud = zona_basica_salud;
    }

    public void setTasa_incidencia_acumulada_ultimos_14dias(Float tasa_incidencia_acumulada_ultimos_14dias) {
        this.tasa_incidencia_acumulada_ultimos_14dias = tasa_incidencia_acumulada_ultimos_14dias;
    }

    public void setTasa_incidencia_acumulada_total(Float tasa_incidencia_acumulada_total) {
        this.tasa_incidencia_acumulada_total = tasa_incidencia_acumulada_total;
    }

    public void setCasos_confirmados_totales(int casos_confirmados_totales) {
        this.casos_confirmados_totales = casos_confirmados_totales;
    }

    public void setFecha_informe(Date fecha_informe) {
        this.fecha_informe = fecha_informe;
    }




}