package com.example.ferreteriaapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "provedor")
public class Proveedor {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "proveedor_id")
    private int proveedor_id;

    @ColumnInfo(name = "proveedor_nombre")
    private String proveedor_nombre;

    @ColumnInfo(name = "proveedor_marca_id")
    private String proveedor_marca_id;

    @ColumnInfo(name = "proveedor_marca")
    private String proveedor_marca;

    @ColumnInfo(name = "proveedor_correo")
    private String proveedor_correo;

    @ColumnInfo(name = "proveedor_direccion")
    private String proveedor_direccion;

    @ColumnInfo(name = "proveedor_telefono")
    private String proveedor_telefono;


    public int getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(int proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public String getProveedor_nombre() {
        return proveedor_nombre;
    }

    public void setProveedor_nombre(String proveedor_nombre) {
        this.proveedor_nombre = proveedor_nombre;
    }

    public String getProveedor_marca_id() {
        return proveedor_marca_id;
    }

    public void setProveedor_marca_id(String proveedor_marca_id) {
        this.proveedor_marca_id = proveedor_marca_id;
    }

    public String getProveedor_marca() {
        return proveedor_marca;
    }

    public void setProveedor_marca(String proveedor_marca) {
        this.proveedor_marca = proveedor_marca;
    }

    public String getProveedor_correo() {
        return proveedor_correo;
    }

    public void setProveedor_correo(String proveedor_correo) {
        this.proveedor_correo = proveedor_correo;
    }

    public String getProveedor_direccion() {
        return proveedor_direccion;
    }

    public void setProveedor_direccion(String proveedor_direccion) {
        this.proveedor_direccion = proveedor_direccion;
    }

    public String getProveedor_telefono() {
        return proveedor_telefono;
    }

    public void setProveedor_telefono(String proveedor_telefono) {
        this.proveedor_telefono = proveedor_telefono;
    }
}
