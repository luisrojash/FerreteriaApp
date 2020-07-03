package com.example.ferreteriaapp.productos.listener;

import com.example.ferreteriaapp.productos.service.ListProductosResponse;

public interface ListProducListener {

    void onClickEliminarProducto(ListProductosResponse.ClassProductos productoUi);

    void onClickEditarProducto(ListProductosResponse.ClassProductos productoUi);
}
