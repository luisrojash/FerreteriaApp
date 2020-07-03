package com.example.ferreteriaapp.principal.zocalos.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZocalosService {

    @GET("apiNew/ListaListelosApi.php")
    Call<ZocalosResponse> obtenerListaInicial(@Query("pagecount") int contador_pagina,
                                              @Query("categoria_id") String categoria_id);
}
