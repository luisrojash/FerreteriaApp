package com.example.ferreteriaapp.principal.fragment.model;

import com.google.gson.annotations.SerializedName;

public class Productos {
    @SerializedName("producto_id")
    String productoId;
    @SerializedName("producto_nombre")

    String productoNombre;
    @SerializedName("producto_descripcion")

    String productoDescripcion;
    @SerializedName("producto_imagen")

    String productoImagen;
    @SerializedName("producto_inventario_minimo")

    String productoInventarioMinimo;
    @SerializedName("producto_precio_in")

    String productoPrecioIn;
    @SerializedName("producto_precio_out")

    String productoPrecioOut;
    @SerializedName("producto_presentacion")

    String productoPresentacion;
    @SerializedName("producto_unidad")

    String productoUnidad;
    @SerializedName("producto_estado")

    String productoEstado;
    @SerializedName("producto_fech_creacion")

    String productoFechCreacion;
    @SerializedName("producto_cod_producto")

    String productoCodProducto;
    @SerializedName("producto_stock_inicial")

    String productoStockInicial;
    @SerializedName("departamento_id")

    String departamentoId;
    @SerializedName("provincia_id")

    String provinciaId;
    @SerializedName("distrito_id")

    String distritoId;
    @SerializedName("proveedor_proveedor_id")

    String proveedorProveedorId;
    @SerializedName("color_color_id")

    String colorColorId;
    @SerializedName("categoria_categoria_id")

    String categoriaCategoriaId;
    @SerializedName("tipologia_tipologia_id")

    String tipologiaTipologiaId;
    @SerializedName("medida_medida_id")

    String medidaMedidaId;
    @SerializedName("material_material_id")

    String materialMaterialId;
    @SerializedName("superficie_superficie_id")

    String superficieSuperficieId;

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    public void setProductoDescripcion(String productoDescripcion) {
        this.productoDescripcion = productoDescripcion;
    }

    public String getProductoImagen() {
        return productoImagen;
    }

    public void setProductoImagen(String productoImagen) {
        this.productoImagen = productoImagen;
    }

    public String getProductoInventarioMinimo() {
        return productoInventarioMinimo;
    }

    public void setProductoInventarioMinimo(String productoInventarioMinimo) {
        this.productoInventarioMinimo = productoInventarioMinimo;
    }

    public String getProductoPrecioIn() {
        return productoPrecioIn;
    }

    public void setProductoPrecioIn(String productoPrecioIn) {
        this.productoPrecioIn = productoPrecioIn;
    }

    public String getProductoPrecioOut() {
        return productoPrecioOut;
    }

    public void setProductoPrecioOut(String productoPrecioOut) {
        this.productoPrecioOut = productoPrecioOut;
    }

    public String getProductoPresentacion() {
        return productoPresentacion;
    }

    public void setProductoPresentacion(String productoPresentacion) {
        this.productoPresentacion = productoPresentacion;
    }

    public String getProductoUnidad() {
        return productoUnidad;
    }

    public void setProductoUnidad(String productoUnidad) {
        this.productoUnidad = productoUnidad;
    }

    public String getProductoEstado() {
        return productoEstado;
    }

    public void setProductoEstado(String productoEstado) {
        this.productoEstado = productoEstado;
    }

    public String getProductoFechCreacion() {
        return productoFechCreacion;
    }

    public void setProductoFechCreacion(String productoFechCreacion) {
        this.productoFechCreacion = productoFechCreacion;
    }

    public String getProductoCodProducto() {
        return productoCodProducto;
    }

    public void setProductoCodProducto(String productoCodProducto) {
        this.productoCodProducto = productoCodProducto;
    }

    public String getProductoStockInicial() {
        return productoStockInicial;
    }

    public void setProductoStockInicial(String productoStockInicial) {
        this.productoStockInicial = productoStockInicial;
    }

    public String getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(String departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(String provinciaId) {
        this.provinciaId = provinciaId;
    }

    public String getDistritoId() {
        return distritoId;
    }

    public void setDistritoId(String distritoId) {
        this.distritoId = distritoId;
    }

    public String getProveedorProveedorId() {
        return proveedorProveedorId;
    }

    public void setProveedorProveedorId(String proveedorProveedorId) {
        this.proveedorProveedorId = proveedorProveedorId;
    }

    public String getColorColorId() {
        return colorColorId;
    }

    public void setColorColorId(String colorColorId) {
        this.colorColorId = colorColorId;
    }

    public String getCategoriaCategoriaId() {
        return categoriaCategoriaId;
    }

    public void setCategoriaCategoriaId(String categoriaCategoriaId) {
        this.categoriaCategoriaId = categoriaCategoriaId;
    }

    public String getTipologiaTipologiaId() {
        return tipologiaTipologiaId;
    }

    public void setTipologiaTipologiaId(String tipologiaTipologiaId) {
        this.tipologiaTipologiaId = tipologiaTipologiaId;
    }

    public String getMedidaMedidaId() {
        return medidaMedidaId;
    }

    public void setMedidaMedidaId(String medidaMedidaId) {
        this.medidaMedidaId = medidaMedidaId;
    }

    public String getMaterialMaterialId() {
        return materialMaterialId;
    }

    public void setMaterialMaterialId(String materialMaterialId) {
        this.materialMaterialId = materialMaterialId;
    }

    public String getSuperficieSuperficieId() {
        return superficieSuperficieId;
    }

    public void setSuperficieSuperficieId(String superficieSuperficieId) {
        this.superficieSuperficieId = superficieSuperficieId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productoId='" + productoId + '\'' +
                ", productoNombre='" + productoNombre + '\'' +
                ", productoDescripcion='" + productoDescripcion + '\'' +
                ", productoImagen='" + productoImagen + '\'' +
                ", productoInventarioMinimo='" + productoInventarioMinimo + '\'' +
                ", productoPrecioIn='" + productoPrecioIn + '\'' +
                ", productoPrecioOut='" + productoPrecioOut + '\'' +
                ", productoPresentacion='" + productoPresentacion + '\'' +
                ", productoUnidad='" + productoUnidad + '\'' +
                ", productoEstado='" + productoEstado + '\'' +
                ", productoFechCreacion='" + productoFechCreacion + '\'' +
                ", productoCodProducto='" + productoCodProducto + '\'' +
                ", productoStockInicial='" + productoStockInicial + '\'' +
                ", departamentoId='" + departamentoId + '\'' +
                ", provinciaId='" + provinciaId + '\'' +
                ", distritoId='" + distritoId + '\'' +
                ", proveedorProveedorId='" + proveedorProveedorId + '\'' +
                ", colorColorId='" + colorColorId + '\'' +
                ", categoriaCategoriaId='" + categoriaCategoriaId + '\'' +
                ", tipologiaTipologiaId='" + tipologiaTipologiaId + '\'' +
                ", medidaMedidaId='" + medidaMedidaId + '\'' +
                ", materialMaterialId='" + materialMaterialId + '\'' +
                ", superficieSuperficieId='" + superficieSuperficieId + '\'' +
                '}';
    }
}
