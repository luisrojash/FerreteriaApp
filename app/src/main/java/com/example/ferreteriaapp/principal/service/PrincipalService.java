package com.example.ferreteriaapp.principal.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrincipalService {

    @GET("apiNew/BuscarProductoApi.php")
    Call<PrincipalResponse> obtenerListaProductos(@Query("nombreProducto") String nombreProducto);
}
