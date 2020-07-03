package com.example.ferreteriaapp.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categoria")
public class Categoria {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "categoria_id")
    private int categoria_id;

    @ColumnInfo(name = "categoria_nombre")
    private String categoria_nombre;

    public Categoria() {
    }
    @ColumnInfo(name = "categoria_fech_creacion")
    private String categoria_fech_creacion;

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getCategoria_nombre() {
        return categoria_nombre;
    }

    public void setCategoria_nombre(String categoria_nombre) {
        this.categoria_nombre = categoria_nombre;
    }

    public String getCategoria_fech_creacion() {
        return categoria_fech_creacion;
    }

    public void setCategoria_fech_creacion(String categoria_fech_creacion) {
        this.categoria_fech_creacion = categoria_fech_creacion;
    }
}