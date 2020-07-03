package com.example.ferreteriaapp.inventario.fragmento;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.inventario.fragmento.service.InventarioFrResponse;
import com.example.ferreteriaapp.inventario.fragmento.service.InventarioFrService;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventarioFrRepository {

    private InventarioFrService service;

    public InventarioFrRepository(InventarioFrService service) {
        this.service = service;
    }

    void obtenerListaAlmacen(String almacenId, int conteo, String tipoOperacion, MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerLista(conteo, almacenId).enqueue(new Callback<InventarioFrResponse>() {
            @Override
            public void onResponse(Call<InventarioFrResponse> call, Response<InventarioFrResponse> response) {
                if (response.isSuccessful()) {
                    InventarioFrResponse listProductosResponseList = response.body();
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
            public void onFailure(Call<InventarioFrResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataResponseLista.postValue(stringObjectHashMap);
            }
        });
    }
}
