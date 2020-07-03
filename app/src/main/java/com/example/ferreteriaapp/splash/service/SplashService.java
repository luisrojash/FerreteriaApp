package com.example.ferreteriaapp.splash.service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashService {
    @GET("apiNew/DatosGeneralesApi.php")
    Call<SplashResponse> obtenerDataPrincipal();
}
