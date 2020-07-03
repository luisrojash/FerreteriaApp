package com.example.ferreteriaapp.operaciones.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OperacionesService {

    @GET("apiNew/BuscarProductoApi.php")
    Call<OperacionesResponse> obtenerLista(@Query("nombreProducto") String nombreProducto);

    @GET("apiNew/ObtenerLoteApi.php")
    Call<OperacionesResponse> obtenerLote(@Query("idProductos") String idProductos,
                                          @Query("idAlamacen") String idAlamacen,
                                          @Query("loteCodigo") String loteCodigo);


    @FormUrlEncoded
    @POST("apiNew/OperacionesApi.php")
    Call<OpeResponse> registrarOperacion(@Field("jsonOperaciones") String jsonOperaciones,
                                         @Field("tipoOperacion") String tipoOperacion);

    @FormUrlEncoded
    @POST("apiNew/EntradaApi.php")
    Call<OpeResponse> registrarOperacionEntrada(@Field("jsonOperaciones") String jsonOperaciones,
                                                @Field("tipoOperaciones") String tipoOperacion);

    @FormUrlEncoded
    @POST("apiNew/DevolucionesApi.php")
    Call<OpeResponse> registrarOperacionDevoluciones(@Field("jsonOperaciones") String jsonOperaciones,
                                                @Field("tipoOperaciones") String tipoOperacion);




    @FormUrlEncoded
    @POST("apiNew/SalidaApi.php")
    Call<OpeResponse> registrarOperacionSalida(@Field("jsonOperaciones") String jsonOperaciones,
                                               @Field("tipoOperaciones") String tipoOperacion);


}
