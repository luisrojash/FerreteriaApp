package com.example.ferreteriaapp.registroUser;

import androidx.lifecycle.ViewModel;

public class RegistroUserViewModel extends ViewModel {

    private RegistroUserRepository repository;

    public RegistroUserViewModel(RegistroUserRepository repository) {
        this.repository = repository;
    }

    public void registrarUsuario(String usuario, String clave) {

        repository.onRegistrarUsuario(usuario,clave);
    }
}
