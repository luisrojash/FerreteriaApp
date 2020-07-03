package com.example.ferreteriaapp.productos.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ListProductosService {

    @GET("apiNew/ListaProductosRegApi.php")
    Call<ListProductosResponse> obtenerListaInicial(@Query("pagecount") int contador_pagina);

    /*Eliminar Producto*/
    @FormUrlEncoded
    @POST("apiNew/EliminarProductoApi.php")
    Call<DefaultResponse> eliminarProducto(@Field("producto_id") String producto_id);
}
