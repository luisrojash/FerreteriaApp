package com.example.ferreteriaapp.registroUser.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistroUserService {

    @FormUrlEncoded
    @POST("apiNew/RegistrarUserApi.php")
    Call<RegistroUserResponse> registrarUser(@Field("usuario") String email,
                                             @Field("clave") String clave);
}
