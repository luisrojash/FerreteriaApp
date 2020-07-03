package com.example.ferreteriaapp.principal.listelos.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListelosService {

    @GET("apiNew/ListaListelosApi.php")
    Call<ListelosResponse> obtenerListaInicial(@Query("pagecount") int contador_pagina,
                                                    @Query("categoria_id") String categoria_id);
}
