package com.example.ferreteriaapp.registroUser;

import com.example.ferreteriaapp.registroUser.service.RegistroUserResponse;
import com.example.ferreteriaapp.registroUser.service.RegistroUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RegistroUserRepository {

    private RegistroUserService registroUserService;

    public RegistroUserRepository(RegistroUserService registroUserService) {
        this.registroUserService = registroUserService;
    }

    public void onRegistrarUsuario(String usuario, String clave) {
        registroUserService.registrarUser(usuario, clave).enqueue(new Callback<RegistroUserResponse>() {
            @Override
            public void onResponse(Call<RegistroUserResponse> call, Response<RegistroUserResponse> response) {
                if (response.isSuccessful()) {
                    RegistroUserResponse response1 = response.body();
                    Timber.d("response1: %s", response1.getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegistroUserResponse> call, Throwable t) {
                Timber.d("onFailure: ");
            }
        });
    }
}
