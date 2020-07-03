package com.example.ferreteriaapp.correo;

import com.example.ferreteriaapp.correo.response.CorreoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CorreoService {

    @GET("apiNew/CorreoApi.php")
    Call<CorreoResponse> obtenerListaProductos();
}
