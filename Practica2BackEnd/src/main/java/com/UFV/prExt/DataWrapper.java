package com.UFV.prExt;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataWrapper {
    private ArrayList<TIA_ZonasBasicas> dataBasicas;
    private List<TIA_ZonasBasicas_Edad> dataEdad;

    public ArrayList<TIA_ZonasBasicas> getDataBasicas() {
        return dataBasicas;
    }

    public void setDataBasicas(ArrayList<TIA_ZonasBasicas> dataBasicas) {
        this.dataBasicas = dataBasicas;
    }


    public List<TIA_ZonasBasicas_Edad> getDataEdad() {
        return dataEdad;
    }

    public void setDataEdad(List<TIA_ZonasBasicas_Edad> dataEdad) {
        this.dataEdad = dataEdad;
    }

}

