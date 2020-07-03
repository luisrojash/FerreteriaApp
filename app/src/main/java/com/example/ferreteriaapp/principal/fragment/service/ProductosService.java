package com.example.ferreteriaapp.principal.fragment.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductosService {

    @GET("apiNew/ListaMaterialApi.php")
    Call<ProductosResponse> obtenerListaInicial(@Query("pagecount") int contador_pagina,
                                                @Query("material_id") String material_id);
}
