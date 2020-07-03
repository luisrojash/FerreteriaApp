package com.example.ferreteriaapp.splash;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

class SplashViewModel extends ViewModel {

    private SplashRepository repository;

    MutableLiveData<HashMap<String, Object>> hashMapCargarDataPrincipal = new MutableLiveData<>();
    MutableLiveData<String> mutableLiveDataMensaje = new MutableLiveData<>();

    SplashViewModel(SplashRepository repository) {
        this.repository = repository;
    }

    void onCargarDataPrincipal() {
        repository.onCargarDataInicial(hashMapCargarDataPrincipal);
    }

    void responseDataPrincipal(HashMap<String, Object> objectHashMap) {
        if (objectHashMap.get(AppConstants.ERROR_IMPORTACION) != null) {
            String estado = (String) objectHashMap.get(AppConstants.ERROR_IMPORTACION);
            mutableLiveDataMensaje.postValue(estado);
            return;
        }
        if (objectHashMap.get(AppConstants.RESPONSE_RETROFIT_SIN_INTERT) != null) {
            String estado = (String) objectHashMap.get(AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
            mutableLiveDataMensaje.postValue(estado);
            return;
        }
        if (objectHashMap.get(AppConstants.RESPONSE_RETROFIT_ERROR) != null) {
            String estado = "Error Servicio Web";
            mutableLiveDataMensaje.postValue(estado);
        }
    }
}
