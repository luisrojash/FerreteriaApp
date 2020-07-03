package com.example.ferreteriaapp.productos;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.example.ferreteriaapp.productos.service.DefaultResponse;
import com.example.ferreteriaapp.productos.service.ListProductosResponse;
import com.example.ferreteriaapp.productos.service.ListProductosService;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductosRepository {

    private ListProductosService service;

    public ListProductosRepository(ListProductosService service) {
        this.service = service;
    }

    public void obtenerListaProdutos(int pageCount, MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerListaInicial(pageCount).enqueue(new Callback<ListProductosResponse>() {
            @Override
            public void onResponse(Call<ListProductosResponse> call, Response<ListProductosResponse> response) {
                if (response.isSuccessful()) {
                    ListProductosResponse listProductosResponseList = response.body();
                    if (listProductosResponseList == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                        mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, listProductosResponseList);
                    mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<ListProductosResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataResponseLista.postValue(stringObjectHashMap);
            }
        });
    }

    public void onClickEliminarProducto(ListProductosResponse.ClassProductos productoUi, MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.eliminarProducto(productoUi.getProducto_id()).enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    DefaultResponse listProductosResponseList = response.body();
                    if (listProductosResponseList == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                        mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, listProductosResponseList);
                    mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataResponseLista.postValue(stringObjectHashMap);
            }
        });
    }
}
