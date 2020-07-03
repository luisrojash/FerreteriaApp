package com.example.ferreteriaapp.splash.service;

import com.example.ferreteriaapp.model.Almacen;
import com.example.ferreteriaapp.model.Categoria;
import com.example.ferreteriaapp.model.Color;
import com.example.ferreteriaapp.model.Material;
import com.example.ferreteriaapp.model.Medida;
import com.example.ferreteriaapp.model.Proveedor;
import com.example.ferreteriaapp.model.Superficie;
import com.example.ferreteriaapp.model.Tipologia;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SplashResponse {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("categoriaLista")
    @Expose
    private List<Categoria> categoriaLista;
    @SerializedName("proveedorLista")
    @Expose
    private List<Proveedor> proveedorLista;
    @SerializedName("tipologiaLista")
    @Expose
    private List<Tipologia> tipologiaLista;
    @SerializedName("colorLista")
    @Expose
    private List<Color> colorLista;
    @SerializedName("materialLista")
    @Expose
    private List<Material> materialLista;
    @SerializedName("medidaLista")
    @Expose
    private List<Medida> medidaLista;
    @SerializedName("superficieLista")
    @Expose
    private List<Superficie> superficieLista;
    @SerializedName("almacenLista")
    @Expose
    private List<Almacen> almacenLista;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Categoria> getCategoriaLista() {
        return categoriaLista;
    }

    public void setCategoriaLista(List<Categoria> categoriaLista) {
        this.categoriaLista = categoriaLista;
    }

    public List<Proveedor> getProveedorLista() {
        return proveedorLista;
    }

    public void setProveedorLista(List<Proveedor> proveedorLista) {
        this.proveedorLista = proveedorLista;
    }

    public List<Tipologia> getTipologiaLista() {
        return tipologiaLista;
    }

    public void setTipologiaLista(List<Tipologia> tipologiaLista) {
        this.tipologiaLista = tipologiaLista;
    }

    public List<Color> getColorLista() {
        return colorLista;
    }

    public void setColorLista(List<Color> colorLista) {
        this.colorLista = colorLista;
    }

    public List<Material> getMaterialLista() {
        return materialLista;
    }

    public void setMaterialLista(List<Material> materialLista) {
        this.materialLista = materialLista;
    }

    public List<Medida> getMedidaLista() {
        return medidaLista;
    }

    public void setMedidaLista(List<Medida> medidaLista) {
        this.medidaLista = medidaLista;
    }

    public List<Superficie> getSuperficieLista() {
        return superficieLista;
    }

    public void setSuperficieLista(List<Superficie> superficieLista) {
        this.superficieLista = superficieLista;
    }

    public List<Almacen> getAlmacenLista() {
        return almacenLista;
    }

    public void setAlmacenLista(List<Almacen> almacenLista) {
        this.almacenLista = almacenLista;
    }
}
