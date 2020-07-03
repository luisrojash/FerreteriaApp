package com.example.ferreteriaapp.registroUser;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RegistroUserFactory implements ViewModelProvider.Factory  {

    private RegistroUserRepository repository;

    public RegistroUserFactory(RegistroUserRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegistroUserViewModel.class)) {
            return (T) new RegistroUserViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
