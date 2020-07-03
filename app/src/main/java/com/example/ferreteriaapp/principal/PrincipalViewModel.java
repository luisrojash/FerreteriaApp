package com.example.ferreteriaapp.principal;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

class PrincipalViewModel extends ViewModel {


    MutableLiveData<HashMap<String, Object>> mutableLiveDataListaMaterial = new MutableLiveData<>();

    MutableLiveData<HashMap<String, Object>> mutableLiveDataListaBuscador = new MutableLiveData<>();
    MutableLiveData<String> mutableLiveDataString = new MutableLiveData<>();

    private PrincipalRepository repository;
    private String buscarProducto;

    PrincipalViewModel(PrincipalRepository repository) {
        this.repository = repository;
    }

    void obtenerMaterialLista() {
        repository.obtenerMaterialLista(mutableLiveDataListaMaterial);
    }

    void resultadoListadoMaterial(HashMap<String, Object> stringObjectHashMap) {
        if (stringObjectHashMap.get(AppConstants.ERROR_IMPORTACION) != null) {
            String estado = (String) stringObjectHashMap.get(AppConstants.ERROR_IMPORTACION);
            mutableLiveDataString.postValue(estado);
        }
    }

    void buscarProducto(String buscarProducto) {
        this.buscarProducto = buscarProducto;
        repository.buscarProducto(buscarProducto,mutableLiveDataListaBuscador);

    }
}
