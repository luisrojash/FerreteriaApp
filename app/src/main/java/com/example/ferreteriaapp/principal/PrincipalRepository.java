package com.example.ferreteriaapp.principal;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.base.AppExecutors;
import com.example.ferreteriaapp.db.FerreDB;
import com.example.ferreteriaapp.model.Material;
import com.example.ferreteriaapp.principal.service.PrincipalResponse;
import com.example.ferreteriaapp.principal.service.PrincipalService;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class PrincipalRepository {

    private AppExecutors appExecutors;
    private PrincipalService service;
    private FerreDB db;


    public PrincipalRepository(AppExecutors appExecutors, PrincipalService service, FerreDB db) {
        this.appExecutors = appExecutors;
        this.service = service;
        this.db = db;
    }

    void obtenerMaterialLista(MutableLiveData<HashMap<String, Object>> mutableLiveDataListaMaterial) {
        HashMap<String, Object> hashMapPeticion = new HashMap<>();
        validarMaterialList(hashMapPeticion, mutableLiveDataListaMaterial);
    }

    private List<Material> validarMaterialList(HashMap<String, Object> hashMapPeticion,
                                               MutableLiveData<HashMap<String, Object>> mutableLiveDataListaMaterial) {
        List<Material> materialList = new ArrayList<>();
        try {
            appExecutors.networkIO().execute(() -> {
                materialList.addAll(db.materialDao().obtenerListaMaterial());
                hashMapPeticion.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, materialList);
                mutableLiveDataListaMaterial.postValue(hashMapPeticion);
            });
        } catch (Exception e) {
            String estado = "";
            Timber.d("Exeption");
            estado = AppConstants.ERROR_IMPORTACION;
            hashMapPeticion.put(AppConstants.ERROR_IMPORTACION, estado);
            Timber.d("ERROR_IMPORTACION");
            mutableLiveDataListaMaterial.postValue(hashMapPeticion);
        }
        return materialList;
    }

    void buscarProducto(String buscarProducto, MutableLiveData<HashMap<String, Object>> mutableLiveDataResponseLista) {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.obtenerListaProductos(buscarProducto).enqueue(new Callback<PrincipalResponse>() {
            @Override
            public void onResponse(Call<PrincipalResponse> call, Response<PrincipalResponse> response) {
                if (response.isSuccessful()) {
                    PrincipalResponse listProductosResponseList = response.body();
                    if (listProductosResponseList == null) {
                        stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                        mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                        return;
                    }
                    stringObjectHashMap.put("service", listProductosResponseList);
                    mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataResponseLista.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<PrincipalResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataResponseLista.postValue(stringObjectHashMap);
            }
        });
    }
}
