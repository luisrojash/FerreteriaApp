package com.example.ferreteriaapp.principal;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PrincipalFactory implements ViewModelProvider.Factory {

    private PrincipalRepository repository;

    public PrincipalFactory(PrincipalRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PrincipalViewModel.class)) {
            return (T) new PrincipalViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
