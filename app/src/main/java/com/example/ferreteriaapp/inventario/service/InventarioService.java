package com.example.ferreteriaapp.inventario.service;

import com.example.ferreteriaapp.principal.service.PrincipalResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InventarioService {

    @GET("apiNew/BuscarProductoApi.php")
    Call<PrincipalResponse> obtenerListaProductos(@Query("nombreProducto") String nombreProducto);
}
