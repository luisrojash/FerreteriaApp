package com.example.ferreteriaapp.operaciones.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OperacionesResponse {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("listaProductos")
    @Expose
    private List<ProductosResp> listaProductos;

    @SerializedName("lote")
    @Expose
    private String lote;

    @SerializedName("loteLista")
    @Expose
    private List<LoteResp> loteLista;

    public List<LoteResp> getLoteLista() {
        return loteLista;
    }

    public void setLoteLista(List<LoteResp> loteLista) {
        this.loteLista = loteLista;
    }

    public boolean isError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }



    public List<ProductosResp> getListaProductos() {
        return listaProductos;
    }

    public class ProductosResp {
        private String producto_id;
        private String producto_nombre;
        private String producto_descripcion;
        private String almacen_id;
        private String producto_image;
        private String producto_precio_in;

        private String producto_precio_out;
        private String producto_unidad;
        private String producto_cod_producto;
        private String producto_stock_inicial;
        private String color_color_id;
        private String tipologia_id;
        private String medida_medida_id;
        private String material_material_id;
        private String superficie_superficie_id;
        private String lote_id;
        private String m2;
        private String categoria_id;
        private String proveedor_id;
        private String producto_minimo;
        private String medida_descripcion;



        public String getTipologia_id() {
            return tipologia_id;
        }

        public String getProducto_image() {
            return producto_image;
        }

        public String getProducto_minimo() {
            return producto_minimo;
        }

        public String getProducto_id() {
            return producto_id;
        }

        public String getProducto_nombre() {
            return producto_nombre;
        }

        public String getProducto_descripcion() {
            return producto_descripcion;
        }

        public String getAlmacen_id() {
            return almacen_id;
        }

        public String getProducto_imagen() {
            return producto_image;
        }

        public String getProducto_precio_in() {
            return producto_precio_in;
        }


        public String getProducto_precio_out() {
            return producto_precio_out;
        }

        public String getProducto_unidad() {
            return producto_unidad;
        }

        public String getProducto_cod_producto() {
            return producto_cod_producto;
        }

        public String getProducto_stock_inicial() {
            return producto_stock_inicial;
        }

        public String getProveedor_id() {
            return proveedor_id;
        }

        public String getColor_color_id() {
            return color_color_id;
        }


        public String getMedida_medida_id() {
            return medida_medida_id;
        }

        public String getMaterial_material_id() {
            return material_material_id;
        }

        public String getSuperficie_superficie_id() {
            return superficie_superficie_id;
        }

        public String getLote_id() {
            return lote_id;
        }

        public String getM2() {
            return m2;
        }

        public String getCategoria_id() {
            return categoria_id;
        }

        public String getMedida_descripcion() {
            return medida_descripcion;
        }
    }

    public class LoteResp {
        private String loteCodigo;
        private String stockProducto;
        private String unidadProducto;

        public String getLoteCodigo() {
            return loteCodigo;
        }

        public void setLoteCodigo(String loteCodigo) {
            this.loteCodigo = loteCodigo;
        }

        public String getStockProducto() {
            return stockProducto;
        }

        public void setStockProducto(String stockProducto) {
            this.stockProducto = stockProducto;
        }

        public String getUnidadProducto() {
            return unidadProducto;
        }

        public void setUnidadProducto(String unidadProducto) {
            this.unidadProducto = unidadProducto;
        }
    }
}
