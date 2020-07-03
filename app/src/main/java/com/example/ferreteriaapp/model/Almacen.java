package com.example.ferreteriaapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "almacen")
public class Almacen {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "almacen_id")
    private String almacen_id;
    @ColumnInfo(name = "almacen_nombre")
    private String almacen_nombre;

    @NonNull
    public String getAlmacen_id() {
        return almacen_id;
    }

    public void setAlmacen_id(@NonNull String almacen_id) {
        this.almacen_id = almacen_id;
    }

    public String getAlmacen_nombre() {
        return almacen_nombre;
    }

    public void setAlmacen_nombre(String almacen_nombre) {
        this.almacen_nombre = almacen_nombre;
    }
}
