package com.example.ferreteriaapp.login;

import androidx.lifecycle.MutableLiveData;

import com.example.ferreteriaapp.login.service.LoginResponse;
import com.example.ferreteriaapp.login.service.LoginService;
import com.example.ferreteriaapp.util.AppConstants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private LoginService service;

    public LoginRepository(LoginService service) {
        this.service = service;
    }

    void initLogin(String usuario, String clave,
                   MutableLiveData<HashMap<String, Object>> mutableLiveDataInitLogin,
                   MutableLiveData<String> mutableLiveDataMensaje) {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        service.validarSesionUsuario(usuario, clave).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getError()) {
                        mutableLiveDataMensaje.postValue(loginResponse.getMessage());
                        return;
                    }
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_FINALIZADO, loginResponse.getMessage());
                    mutableLiveDataInitLogin.postValue(stringObjectHashMap);
                } else {
                    stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_ERROR, null);
                    mutableLiveDataInitLogin.postValue(stringObjectHashMap);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                stringObjectHashMap.put(AppConstants.RESPONSE_RETROFIT_SIN_INTERT,
                        AppConstants.RESPONSE_RETROFIT_SIN_INTERT);
                mutableLiveDataInitLogin.postValue(stringObjectHashMap);
            }
        });
    }
}
