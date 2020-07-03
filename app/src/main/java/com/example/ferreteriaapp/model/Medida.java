package com.example.ferreteriaapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medida")
public class Medida {
    @PrimaryKey
    @ColumnInfo(name = "medida_id")
    private int medida_id;

    @ColumnInfo(name = "medida_medida")
    private String medida_medida;

    @ColumnInfo(name = "medida_descripcion")
    private String medida_descripcion;

    public Medida() {
    }

    public int getMedida_id() {
        return medida_id;
    }

    public void setMedida_id(int medida_id) {
        this.medida_id = medida_id;
    }

    public String getMedida_medida() {
        return medida_medida;
    }

    public void setMedida_medida(String medida_medida) {
        this.medida_medida = medida_medida;
    }

    public String getMedida_descripcion() {
        return medida_descripcion;
    }

    public void setMedida_descripcion(String medida_descripcion) {
        this.medida_descripcion = medida_descripcion;
    }
}
