package com.example.ferreteriaapp.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

class LoginViewModel extends ViewModel {

    private LoginRepository respository;
    MutableLiveData<String> mutableLiveDataMensaje = new MutableLiveData<>();
    MutableLiveData<HashMap<String, Object>> mutableLiveDataInitLogin = new MutableLiveData<>();

    LoginViewModel(LoginRepository respository) {
        this.respository = respository;
    }


    void initLogin(String usuario, String clave) {
        if (usuario.isEmpty()) {
            mutableLiveDataMensaje.postValue("Ingresar Usuario");
            return;
        }
        if (clave.isEmpty() || clave == null) {
            mutableLiveDataMensaje.postValue("Ingresar Clave");
            return;
        }
        respository.initLogin(usuario, clave, mutableLiveDataInitLogin, mutableLiveDataMensaje);
    }
}
