package com.example.ferreteriaapp.productos.service;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ListProductosResponse  {
    private List<ClassProductos> listaProductos;

    public List<ClassProductos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ClassProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public static class ClassProductos implements Parcelable{
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
        }

        public static final Parcelable.Creator<ClassProductos> CREATOR = new Parcelable.Creator<ClassProductos>() {
            @Override
            public ClassProductos createFromParcel(Parcel source) {
                return new ClassProductos(source);
            }

            @Override
            public ClassProductos[] newArray(int size) {
                return new ClassProductos[size];
            }
        };
    }
}
