package com.example.ferreteriaapp.principal.zocalos.service;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ZocalosResponse {
    private List<ZocalosResponse.ClassProductos> listaProductos;

    public List<ZocalosResponse.ClassProductos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ZocalosResponse.ClassProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public static class ClassProductos implements Parcelable {
        String producto_id;
        String producto_cod_producto;
        String producto_descripcion;
        String producto_minimo;
        String producto_unidad;
        String producto_Stock;
        String producto_Lote;
        String producto_nombre;
        String proveedor_id;
        String proveedor_nombre;
        String proveedor_marca_id;
        String producto_image;
        String categoria_id;
        String tipologia_id;
        String superficie_superficie_id;
        String material_material_id;
        String medida_medida_id;
        String color_color_id;
        String almacen_id;
        String producto_precio_in;
        String producto_precio_out;
        String medida_descripcion;

        String color_nombre;
        String categoria_abrev;
        String tipologia_nombre;
        String material_nombre;
        String superficie_nombre;
        String almacen_nombre;

        public String getProducto_cod_producto() {
            return producto_cod_producto;
        }

        public void setProducto_cod_producto(String producto_cod_producto) {
            this.producto_cod_producto = producto_cod_producto;
        }

        public String getProducto_nombre() {
            return producto_nombre;
        }

        public void setProducto_nombre(String producto_nombre) {
            this.producto_nombre = producto_nombre;
        }

        public String getProveedor_id() {
            return proveedor_id;
        }

        public void setProveedor_id(String proveedor_id) {
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

        public String getProducto_image() {
            return producto_image;
        }

        public void setProducto_image(String producto_image) {
            this.producto_image = producto_image;
        }

        public String getCategoria_id() {
            return categoria_id;
        }

        public void setCategoria_id(String categoria_id) {
            this.categoria_id = categoria_id;
        }

        public String getTipologia_id() {
            return tipologia_id;
        }

        public void setTipologia_id(String tipologia_id) {
            this.tipologia_id = tipologia_id;
        }

        public String getProducto_id() {
            return producto_id;
        }

        public void setProducto_id(String producto_id) {
            this.producto_id = producto_id;
        }

        public String getProducto_descripcion() {
            return producto_descripcion;
        }

        public void setProducto_descripcion(String producto_descripcion) {
            this.producto_descripcion = producto_descripcion;
        }

        public String getProducto_minimo() {
            return producto_minimo;
        }

        public void setProducto_minimo(String producto_minimo) {
            this.producto_minimo = producto_minimo;
        }

        public String getProducto_unidad() {
            return producto_unidad;
        }

        public void setProducto_unidad(String producto_unidad) {
            this.producto_unidad = producto_unidad;
        }

        public String getProducto_Stock() {
            return producto_Stock;
        }

        public void setProducto_Stock(String producto_Stock) {
            this.producto_Stock = producto_Stock;
        }

        public String getProducto_Lote() {
            return producto_Lote;
        }

        public void setProducto_Lote(String producto_Lote) {
            this.producto_Lote = producto_Lote;
        }

        public String getSuperficie_superficie_id() {
            return superficie_superficie_id;
        }

        public void setSuperficie_superficie_id(String superficie_superficie_id) {
            this.superficie_superficie_id = superficie_superficie_id;
        }

        public String getMaterial_material_id() {
            return material_material_id;
        }

        public void setMaterial_material_id(String material_material_id) {
            this.material_material_id = material_material_id;
        }

        public String getMedida_medida_id() {
            return medida_medida_id;
        }

        public void setMedida_medida_id(String medida_medida_id) {
            this.medida_medida_id = medida_medida_id;
        }

        public String getColor_color_id() {
            return color_color_id;
        }

        public void setColor_color_id(String color_color_id) {
            this.color_color_id = color_color_id;
        }

        public String getAlmacen_id() {
            return almacen_id;
        }

        public void setAlmacen_id(String almacen_id) {
            this.almacen_id = almacen_id;
        }

        public String getProducto_precio_in() {
            return producto_precio_in;
        }

        public void setProducto_precio_in(String producto_precio_in) {
            this.producto_precio_in = producto_precio_in;
        }

        public String getProducto_precio_out() {
            return producto_precio_out;
        }

        public void setProducto_precio_out(String producto_precio_out) {
            this.producto_precio_out = producto_precio_out;
        }

        public String getMedida_descripcion() {
            return medida_descripcion;
        }

        public void setMedida_descripcion(String medida_descripcion) {
            this.medida_descripcion = medida_descripcion;
        }

        public String getColor_nombre() {
            return color_nombre;
        }

        public void setColor_nombre(String color_nombre) {
            this.color_nombre = color_nombre;
        }

        public String getCategoria_abrev() {
            return categoria_abrev;
        }

        public void setCategoria_abrev(String categoria_abrev) {
            this.categoria_abrev = categoria_abrev;
        }

        public String getTipologia_nombre() {
            return tipologia_nombre;
        }

        public void setTipologia_nombre(String tipologia_nombre) {
            this.tipologia_nombre = tipologia_nombre;
        }

        public String getMaterial_nombre() {
            return material_nombre;
        }

        public void setMaterial_nombre(String material_nombre) {
            this.material_nombre = material_nombre;
        }

        public String getSuperficie_nombre() {
            return superficie_nombre;
        }

        public void setSuperficie_nombre(String superficie_nombre) {
            this.superficie_nombre = superficie_nombre;
        }

        public String getAlmacen_nombre() {
            return almacen_nombre;
        }

        public void setAlmacen_nombre(String almacen_nombre) {
            this.almacen_nombre = almacen_nombre;
        }

        @Override
        public int describeContents() {
            return 0;
        }


        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(producto_id);
            parcel.writeString(producto_cod_producto);
            parcel.writeString(producto_descripcion);
            parcel.writeString(producto_minimo);
            parcel.writeString(producto_unidad);
            parcel.writeString(producto_Stock);
            parcel.writeString(producto_Lote);
            parcel.writeString(producto_nombre);
            parcel.writeString(proveedor_id);
            parcel.writeString(proveedor_nombre);
            parcel.writeString(proveedor_marca_id);
            parcel.writeString(producto_image);
            parcel.writeString(categoria_id);
            parcel.writeString(tipologia_id);
            parcel.writeString(superficie_superficie_id);
            parcel.writeString(material_material_id);
            parcel.writeString(medida_medida_id);
            parcel.writeString(color_color_id);
            parcel.writeString(almacen_id);
            parcel.writeString(producto_precio_in);
            parcel.writeString(producto_precio_out);
            parcel.writeString(medida_descripcion);


            parcel.writeString(color_nombre);
            parcel.writeString(categoria_abrev);
            parcel.writeString(tipologia_nombre);
            parcel.writeString(material_nombre);
            parcel.writeString(superficie_nombre);
            parcel.writeString(almacen_nombre);

        }

        private ClassProductos(Parcel in) {
            this.producto_id = in.readString();
            this.producto_cod_producto = in.readString();
            this.producto_descripcion = in.readString();
            this.producto_minimo = in.readString();
            this.producto_unidad = in.readString();
            this.producto_Stock = in.readString();
            this.producto_Lote = in.readString();
            this.producto_nombre = in.readString();
            this.proveedor_id = in.readString();
            this.proveedor_nombre = in.readString();
            this.proveedor_marca_id = in.readString();
            this.producto_image = in.readString();
            this.categoria_id = in.readString();
            this.tipologia_id = in.readString();
            this.superficie_superficie_id = in.readString();
            this.material_material_id = in.readString();
            this.medida_medida_id = in.readString();
            this.color_color_id = in.readString();
            this.almacen_id = in.readString();
            this.producto_precio_in = in.readString();
            this.producto_precio_out = in.readString();
            this.medida_descripcion = in.readString();

            this.color_nombre = in.readString();
            this.categoria_abrev = in.readString();
            this.tipologia_nombre = in.readString();
            this.material_nombre = in.readString();
            this.superficie_nombre = in.readString();
            this.almacen_nombre = in.readString();
        }

        public static final Parcelable.Creator<ZocalosResponse.ClassProductos> CREATOR = new Parcelable.Creator<ZocalosResponse.ClassProductos>() {
            @Override
            public ZocalosResponse.ClassProductos createFromParcel(Parcel source) {
                return new ZocalosResponse.ClassProductos(source);
            }

            @Override
            public ZocalosResponse.ClassProductos[] newArray(int size) {
                return new ZocalosResponse.ClassProductos[size];
            }
        };
    }
}
