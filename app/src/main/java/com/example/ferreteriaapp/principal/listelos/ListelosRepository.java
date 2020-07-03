package com.example.ferreteriaapp.principal.listelos;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.principal.listelos.service.ListelosResponse;
import com.example.ferreteriaapp.principal.listelos.service.ListelosService;
import com.example.ferreteriaapp.productos.service.ListProductosResponse;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListelosRepository {
    private ListelosService service;

    public ListelosRepository(ListelosService service) {
        this.service = service;
    }

    void obtenerLista(int contador, String codigoCategoria, String tipoOperacion, MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerListaInicial(contador, codigoCategoria).enqueue(new Callback<ListelosResponse>() {
            @Override
            public void onResponse(Call<ListelosResponse> call, Response<ListelosResponse> response) {
                if (response.isSuccessful()) {
                    ListelosResponse listProductosResponseList = response.body();
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
            public void onFailure(Call<ListelosResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataResponseLista.postValue(stringObjectHashMap);
            }
        });
    }
}
