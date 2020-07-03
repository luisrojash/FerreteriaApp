package com.example.ferreteriaapp.principal.zocalos;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.principal.zocalos.service.ZocalosResponse;
import com.example.ferreteriaapp.principal.zocalos.service.ZocalosService;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZocalosRepository {
    private ZocalosService service;

    public ZocalosRepository(ZocalosService service) {
        this.service = service;
    }

    public void obtenerLista(int contador, String codigoCategoria, String tipoOperacion, MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerListaInicial(contador, codigoCategoria).enqueue(new Callback<ZocalosResponse>() {
            @Override
            public void onResponse(Call<ZocalosResponse> call, Response<ZocalosResponse> response) {
                if (response.isSuccessful()) {
                    ZocalosResponse listProductosResponseList = response.body();
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
            public void onFailure(Call<ZocalosResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataResponseLista.postValue(stringObjectHashMap);
            }
        });
    }
}
