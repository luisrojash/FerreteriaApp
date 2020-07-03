package com.example.ferreteriaapp.inventario;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.base.AppExecutors;
import com.example.ferreteriaapp.db.FerreDB;
import com.example.ferreteriaapp.inventario.service.InventarioService;
import com.example.ferreteriaapp.principal.service.PrincipalResponse;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventarioRepository {

    private FerreDB db;
    private AppExecutors appExecutors;
    private InventarioService service;


    public InventarioRepository(FerreDB db, AppExecutors appExecutors, InventarioService service) {
        this.db = db;
        this.appExecutors = appExecutors;
        this.service = service;
    }

    void obtenerListaInventario(MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista) {
        appExecutors.networkIO().execute(() -> {
            HashMap<String, Object> hashMap = new HashMap<>();
            try {
                hashMap.put("listaAlmacen", db.almacenDao().obtenerListaAlmacen());
                mutableLiveDataResponseLista.postValue(hashMap);
            } catch (Exception e) {
                hashMap.put("error", "error");
                mutableLiveDataResponseLista.postValue(hashMap);
            }
        });
    }

    public void buscarProducto(String buscarProducto, MutableLiveData<HashMap<String, Object>> mutableLiveDataListaBuscador) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerListaProductos(buscarProducto).enqueue(new Callback<PrincipalResponse>() {
            @Override
            public void onResponse(Call<PrincipalResponse> call, Response<PrincipalResponse> response) {
                if (response.isSuccessful()) {
                    PrincipalResponse listProductosResponseList = response.body();
                    if (listProductosResponseList == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                        mutableLiveDataListaBuscador.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put("service", listProductosResponseList);
                    mutableLiveDataListaBuscador.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataListaBuscador.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<PrincipalResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataListaBuscador.postValue(stringObjectHashMap);
            }
        });
    }
}
