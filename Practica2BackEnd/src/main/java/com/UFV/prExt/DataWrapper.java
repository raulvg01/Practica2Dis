package com.UFV.prExt;


import java.util.List;

public class DataWrapper {
    private List<TIA_ZonasBasicas> dataBasicas;
    private List<TIA_ZonasBasicas_Edad> dataEdad;

    public List<TIA_ZonasBasicas> getDataBasicas() {
        return dataBasicas;
    }

    public void setDataBasicas(List<TIA_ZonasBasicas> dataBasicas) {
        this.dataBasicas = dataBasicas;
    }


    public List<TIA_ZonasBasicas_Edad> getDataEdad() {
        return dataEdad;
    }

    public void setDataEdad(List<TIA_ZonasBasicas_Edad> dataEdad) {
        this.dataEdad = dataEdad;
    }

}

