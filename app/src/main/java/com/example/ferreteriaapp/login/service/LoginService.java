package com.example.ferreteriaapp.login.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("apiNew/ValidacionUserApi.php")
    Call<LoginResponse> validarSesionUsuario(@Field("email") String email,
                                             @Field("clave") String clave);
}
