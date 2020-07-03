package com.example.ferreteriaapp.inventario.fragmento.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InventarioFrService {


    @GET("apiNew/BuscarAlmacenApi.php")
    Call<InventarioFrResponse> obtenerLista(@Query("pagecount") int pagecount,
                                            @Query("almacen_id") String almacen_id);
}
