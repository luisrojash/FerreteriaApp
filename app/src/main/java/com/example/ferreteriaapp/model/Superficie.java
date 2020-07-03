package com.example.ferreteriaapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "superficie")
public class Superficie {

    @PrimaryKey
    @ColumnInfo(name = "superficie_id")
    private int superficie_id;

    @ColumnInfo(name = "superficie_nombre")
    private String superficie_nombre;

    public Superficie() {
    }

    public int getSuperficie_id() {
        return superficie_id;
    }

    public void setSuperficie_id(int superficie_id) {
        this.superficie_id = superficie_id;
    }

    public String getSuperficie_nombre() {
        return superficie_nombre;
    }

    public void setSuperficie_nombre(String superficie_nombre) {
        this.superficie_nombre = superficie_nombre;
    }
}
