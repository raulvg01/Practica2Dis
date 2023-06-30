package com.UFV.prExt;


import java.util.List;

public class DataWrapper {
    private List<TIA_ZonasBasicas> dataZonasBasicas;
    private List<TIA_ZonasBasicas_Edad> dataZonasEdad;


    public List<TIA_ZonasBasicas> getDataBasicas() {
        return dataZonasBasicas;
    }

    public void setDataBasicas(List<TIA_ZonasBasicas> dataZonasBasicas) {
        this.dataZonasBasicas = dataZonasBasicas;
    }

    public List<TIA_ZonasBasicas_Edad> getDataEdad() {
        return dataZonasEdad;
    }

    public void setDataEdad(List<TIA_ZonasBasicas_Edad> dataZonasEdad) {
        this.dataZonasEdad = dataZonasEdad;
    }

}

