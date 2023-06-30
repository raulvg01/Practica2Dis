package com.UFV.prExt;

import java.util.Date;

public class TIA_ZonasBasicas_Edad {
    String codigo_geometria;
    String zona_basica_salud;
    Float tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    Float casos_confirmados_P60mas_ultimos_14dias;
    Date fecha_informe;

    public TIA_ZonasBasicas_Edad() {
    }

    public TIA_ZonasBasicas_Edad(String codigo_geometria, String zona_basica_salud, Float tasa_incidencia_acumulada_P60mas_ultimos_14dias, Float casos_confirmados_P60mas_ultimos_14dias, Date fecha_informe) {
        this.codigo_geometria = codigo_geometria;
        this.zona_basica_salud = zona_basica_salud;
        this.tasa_incidencia_acumulada_P60mas_ultimos_14dias = tasa_incidencia_acumulada_P60mas_ultimos_14dias;
        this.casos_confirmados_P60mas_ultimos_14dias = casos_confirmados_P60mas_ultimos_14dias;
        this.fecha_informe = fecha_informe;
    }

    public String getCodigo_geometria() {
        return codigo_geometria;
    }

    public String getZona_basica_salud() {
        return zona_basica_salud;
    }

    public Float getTasa_incidencia_acumulada_P60mas_ultimos_14dias() {
        return tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    }

    public Float getCasos_confirmados_P60mas_ultimos_14dias() {
        return casos_confirmados_P60mas_ultimos_14dias;
    }

    public Date getFecha_informe() {
        return fecha_informe;
    }

    public void setCodigo_geometria(String codigo_geometria) {
        this.codigo_geometria = codigo_geometria;
    }

    public void setZona_basica_salud(String zona_basica_salud) {
        this.zona_basica_salud = zona_basica_salud;
    }

    public void setTasa_incidencia_acumulada_P60mas_ultimos_14dias(Float tasa_incidencia_acumulada_P60mas_ultimos_14dias) {
        this.tasa_incidencia_acumulada_P60mas_ultimos_14dias = tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    }

    public void setCasos_confirmados_P60mas_ultimos_14dias(Float casos_confirmados_P60mas_ultimos_14dias) {
        this.casos_confirmados_P60mas_ultimos_14dias = casos_confirmados_P60mas_ultimos_14dias;
    }

    public void setFecha_informe(Date fecha_informe) {
        this.fecha_informe = fecha_informe;
    }

    public String getTodo() {

        return "Codigo geometria: " + codigo_geometria + "\n" +
                "Zona basica salud: " + zona_basica_salud + "\n" +
                "Tasa incidencia acumulada P60 ultimos 14 dias: " + tasa_incidencia_acumulada_P60mas_ultimos_14dias + "\n" +
                "Casos confirmados P60 ultimos 14 dias: " + casos_confirmados_P60mas_ultimos_14dias + "\n" +
                "Fecha informe: " + fecha_informe + "\n";

    }

}