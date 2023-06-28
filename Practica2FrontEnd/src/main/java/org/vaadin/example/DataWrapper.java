package org.vaadin.example;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataWrapper {
    @SerializedName("data")
    private List<TIA_ZonasBasicas> data;

    public List<TIA_ZonasBasicas> getData() {
        return data;
    }

    public void setData(List<TIA_ZonasBasicas> data) {
        this.data = data;
    }
}
