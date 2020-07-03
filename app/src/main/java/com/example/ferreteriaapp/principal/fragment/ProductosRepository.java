package com.example.ferreteriaapp.principal.fragment;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.principal.fragment.service.ProductosResponse;
import com.example.ferreteriaapp.principal.fragment.service.ProductosService;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosRepository {

    private ProductosService service;

    public ProductosRepository(ProductosService productosService) {
        this.service = productosService;
    }

    void obtenerLista(int contador, String codigoCategoria, String tipoOperacion, MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerListaInicial(contador, codigoCategoria).enqueue(new Callback<ProductosResponse>() {
            @Override
            public void onResponse(Call<ProductosResponse> call, Response<ProductosResponse> response) {
                if (response.isSuccessful()) {
                    ProductosResponse listProductosResponseList = response.body();
                    if (listProductosResponseList == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                        mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, listProductosResponseList);
                    stringObjectHashMap.put("tipoOperacion", tipoOperacion);
                    mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<ProductosResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataResponseLista.postValue(stringObjectHashMap);
            }
        });

    }


}
